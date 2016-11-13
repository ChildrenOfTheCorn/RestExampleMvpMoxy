package com.example.restexample.data.repositories;

import android.util.Log;

import rx.Observable;

/**
 * Created by grishberg on 13.11.16.
 */
public class AuthorizationStorageRepositoryImpl implements AuthorizationStorageRepository {
    private static final String TAG = AuthorizationStorageRepositoryImpl.class.getSimpleName();
    private volatile CharSequence token;
    private volatile String currentLogin;

    public AuthorizationStorageRepositoryImpl() {
        Log.d(TAG, "AuthorizationStorageRepositoryImpl: ");
    }

    @Override
    public Observable<CharSequence> getToken() {
        return Observable.just(token);
    }

    @Override
    public Observable<String> getCurrentLogin() {
        return Observable.just(currentLogin);
    }

    @Override
    public void storeToken(final CharSequence token) {
        this.token = token;
    }

    @Override
    public void setCurrentLogin(final String login) {
        this.currentLogin = login;
    }
}
