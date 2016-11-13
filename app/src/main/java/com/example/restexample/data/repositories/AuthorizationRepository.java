package com.example.restexample.data.repositories;

import rx.Observable;

/**
 * Created by grishberg on 12.11.16.
 * Репозиторий авторизации
 */

public interface AuthorizationRepository {
    Observable<Boolean> authorization(String login, CharSequence password);
}
