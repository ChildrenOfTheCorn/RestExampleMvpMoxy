package com.example.restexample.di.components;

import com.example.restexample.di.modules.AppModule;
import com.example.restexample.di.modules.RestModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 20.10.16.
 * Базовый зависимостип о всему приложению
 */
@Singleton
@Component(modules = {AppModule.class, RestModule.class})

public interface AppComponent {
}
