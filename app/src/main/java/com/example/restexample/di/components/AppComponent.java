package com.example.restexample.di.components;

import com.example.restexample.di.modules.AppModule;
import com.example.restexample.presentation.presenter.AuthorizationPresenterImpl;
import com.example.restexample.presentation.presenter.common.NetworkInjectionHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 20.10.16.
 * Базовый зависимостип о всему приложению
 */
@Singleton
@Component(modules = {AppModule.class})

public interface AppComponent {

}
