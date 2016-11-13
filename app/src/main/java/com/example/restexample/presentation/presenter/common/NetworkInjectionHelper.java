package com.example.restexample.presentation.presenter.common;

import com.example.restexample.App;
import com.example.restexample.data.repositories.AuthorizationStorageRepository;

import javax.inject.Inject;

import lombok.Getter;

/**
 * Created by grishberg on 13.11.16.
 * хэлпер для инжекта зависимостей для сетевой работы
 */
public class NetworkInjectionHelper {
    private static final String TAG = NetworkInjectionHelper.class.getSimpleName();

    @Inject
    @Getter AuthorizationStorageRepository authorizationStorage;

    public NetworkInjectionHelper() {
        App.getAppComponent().inject(this);
    }
}
