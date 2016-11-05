package com.example.restexample.injection;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 20.10.16.
 */
@Singleton
@Component(modules = {RestModule.class})

public interface AppComponent {
}
