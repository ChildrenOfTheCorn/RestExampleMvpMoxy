package com.example.restexample.data;

import com.example.restexample.data.RestModels.RestResponse;
import com.example.restexample.domain.exceptions.NetworkException;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by grishberg on 13.11.16.
 *  Базовый класс первичной обработки сетевых запросов
 */
public class RxUtils {
    private static final String TAG = RxUtils.class.getSimpleName();
    public static final int RETRY_COUNT = 3;

    public static <T> Observable<RestResponse<T>> wrapRetrofitCallAsync(final SoftErrorDelegate<RestResponse> errorChecker,
                                                                        final Call<RestResponse<T>> call) {
        return wrapAsync(Observable.create(subscriber -> {
            Response<RestResponse<T>> execute = null;

            RestResponse<T> body = null;
            for (int i = 0; i < RETRY_COUNT; i++) {
                final Call<RestResponse<T>> callClone = call.clone();
                try {
                    execute = callClone.execute();
                    body = execute.body();
                    if (body != null && body.getError() != null) {
                        final Throwable softError = errorChecker.checkSoftError(body);
                        if (softError != null) {
                            subscriber.onError(softError);
                            subscriber.onCompleted();
                            return;
                        }
                        continue;
                    }
                    break;
                } catch (final IOException e) {
                    subscriber.onError(e);
                    return;
                }
            }

            if (execute.isSuccessful() && body != null) {
                subscriber.onNext(new RestResponse<>(body.getData(), false));
                subscriber.onCompleted();
                return;
            }
            subscriber.onError(new NetworkException(execute.code()));
        }));
    }

    public static <T> Observable<T> wrapAsync(final Observable<T> observable) {
        return wrapAsync(observable, Schedulers.io());
    }

    public static <T> Observable<T> wrapAsync(final Observable<T> observable, final Scheduler scheduler) {
        return observable
                .materialize()
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread()).<T>dematerialize();
    }
}
