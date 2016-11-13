package com.example.restexample.presentation.presenter.common;

import com.example.restexample.data.repositories.AuthorizationStorageRepository;
import com.example.restexample.di.components.AppComponent;
import com.example.restexample.di.components.RestComponent;

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

    public NetworkInjectionHelper(final RestComponent restComponent) {
        restComponent.inject(this);
    }
}
