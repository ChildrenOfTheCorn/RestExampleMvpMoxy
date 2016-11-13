package com.example.restexample.data;

import android.util.Log;

import com.example.restexample.data.RestModels.RestResponse;
import com.example.restexample.domain.exceptions.InvalidTokenException;
import com.example.restexample.domain.exceptions.NetworkException;
import com.example.restexample.domain.exceptions.WrongCredentialsException;

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

    public static <T> Observable<RestResponse<T>> wrapRetrofitCall(final Call<RestResponse<T>> call) {
        return wrapRetrofitCall(call, null);
    }

    public static <T> Observable<RestResponse<T>> wrapRetrofitCall(final Call<RestResponse<T>> call, final CacheListener<T> cacheListener) {
        Log.d(TAG, "wrapRetrofitCall: ");
        return Observable.create(subscriber -> {
            Response<RestResponse<T>> execute = null;
            Log.d(TAG, "wrapRetrofitCall: execute " + Thread.currentThread());
            final T data = cacheListener != null ? cacheListener.getCache(0) : null;
            if (data != null) {
                Log.d(TAG, "wrapRetrofitCall: data from cache");
                subscriber.onNext(new RestResponse<>(data, true));
            }

            RestResponse<T> body = null;
            for (int i = 0; i < RETRY_COUNT; i++) {
                final Call<RestResponse<T>> callClone = call.clone();
                try {
                    Log.d(TAG, "wrapRetrofitCall: call.execute");
                    execute = callClone.execute();
                    body = execute.body();
                    if (body != null && body.getError() != null) {
                        switch (body.getError().getCode()) {
                            case RestConst.Errors.WRONG_CREDENTIALS:
                                subscriber.onError(new WrongCredentialsException(body.getError()));
                                subscriber.onCompleted();
                                return;
                            case RestConst.Errors.TOKEN_INVALID:
                                subscriber.onError(new InvalidTokenException(body.getError()));
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
        });
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

    public interface CacheListener<T> {
        T getCache(int id);
    }
}
