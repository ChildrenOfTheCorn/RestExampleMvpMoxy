package com.example.restexample.data.repositories;

import rx.Observable;

/**
 * Created by grishberg on 13.11.16.
 * Хранилище токенов для сессии
 */
public interface AuthorizationStorageRepository {
    Observable<CharSequence> getToken();

    Observable<String> getCurrentLogin();

    void storeToken(CharSequence token);

    void setCurrentLogin(String login);
}
