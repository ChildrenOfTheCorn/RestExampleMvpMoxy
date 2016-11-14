package com.example.restexample.data.repositories;

import com.example.restexample.data.ApiService;
import com.example.restexample.data.RestModels.AuthResponse;
import com.example.restexample.data.RestModels.RestResponse;
import com.example.restexample.data.RxUtils;

import rx.Observable;

/**
 * Created by grishberg on 13.11.16.
 */
public class AuthorizationRepositoryImpl implements AuthorizationRepository {
    private static final String TAG = AuthorizationRepositoryImpl.class.getSimpleName();

    private final AuthorizationStorageRepository authorizationStorageRepository;
    private final ApiService apiService;

    public AuthorizationRepositoryImpl(final AuthorizationStorageRepository authorizationStorageRepository, final ApiService apiService) {
        this.authorizationStorageRepository = authorizationStorageRepository;
        this.apiService = apiService;
    }

    @Override
    public Observable<Boolean> authorization(final String login, final CharSequence password) {
        // обзервер запроса к ретрофиту
        // новый обзервер, который создастся после обработки авторизации
        final Observable<Boolean> observable = RxUtils.wrapRetrofitCallAsync(apiService.authorization(login, password.toString()))
                .flatMap(response -> {
                    // сохранить в хранилище токен авторизации
                    authorizationStorageRepository.setCurrentLogin(login);
                    authorizationStorageRepository.storeToken(response.toString());
                    return Observable.just(true);
                });
        return observable;
    }
}
