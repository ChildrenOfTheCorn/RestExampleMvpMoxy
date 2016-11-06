package com.example.restexample.injection.components;

import com.example.restexample.injection.modules.AppModule;
import com.example.restexample.injection.modules.AuthorizationModule;
import com.example.restexample.injection.modules.RestModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 20.10.16.
 * Базовый зависимостип о всему приложению
 */
@Singleton
@Component(modules = {AppModule.class, RestModule.class})

public interface AppComponent {
    AuthorizationComponent provideAuthComponent(AuthorizationModule authorizationModule);
}
