package com.example.restexample.data.repositories;

import com.example.restexample.data.ApiService;
import com.example.restexample.data.RestModels.RestResponse;
import com.example.restexample.data.SoftErrorDelegate;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by grishberg on 13.11.16.
 */
public class AuthorizationRepositoryImpl implements AuthorizationRepository {
    private static final String TAG = AuthorizationRepositoryImpl.class.getSimpleName();

    private final AuthorizationStorageRepository authorizationStorageRepository;
    private final ApiService apiService;
    // делегат для определения софтверной ошибки
    private final SoftErrorDelegate<RestResponse> softErrorDelegate;

    public AuthorizationRepositoryImpl(final AuthorizationStorageRepository authorizationStorageRepository,
                                       final ApiService apiService,
                                       final SoftErrorDelegate<RestResponse> softErrorDelegate) {
        this.authorizationStorageRepository = authorizationStorageRepository;
        this.apiService = apiService;
        this.softErrorDelegate = softErrorDelegate;
    }

    @Override
    public Observable<Boolean> authorization(final String login, final CharSequence password) {
        // обзервер запроса к ретрофиту
        // новый обзервер, который создастся после обработки авторизации
        /*
        final Observable<Boolean> observable = RxUtils.wrapRetrofitCallAsync(softErrorDelegate,
                apiService.authorization(login, password.toString()))
                .flatMap(response -> {
                    // сохранить в хранилище токен авторизации
                    authorizationStorageRepository.setCurrentLogin(login);
                    authorizationStorageRepository.storeToken(response.toString());
                    return Observable.just(true);
                });
        return observable;
        */
        final Observable<Boolean> observable = apiService.authorization(login, password.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(response -> {
                    // сохранить в хранилище токен авторизации
                    authorizationStorageRepository.setCurrentLogin(login);
                    authorizationStorageRepository.storeToken(response.toString());
                    return Observable.just(true);
                });
        return observable;
    }
}
