package com.example.restexample.di.components;

import com.example.restexample.data.repositories.AuthorizationStorageRepository;
import com.example.restexample.di.modules.DataModule;


import dagger.Component;

/**
 * Created by grishberg on 13.11.16.
 */
@Component(modules = {DataModule.class})

public interface DataComponent {
    AuthorizationStorageRepository provideAuthStorage();
}
