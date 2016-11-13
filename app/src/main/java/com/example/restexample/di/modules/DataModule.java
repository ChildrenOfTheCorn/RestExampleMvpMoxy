package com.example.restexample.di.modules;

import com.example.restexample.data.ApiService;
import com.example.restexample.data.repositories.AuthorizationRepository;
import com.example.restexample.data.repositories.AuthorizationRepositoryImpl;
import com.example.restexample.data.repositories.AuthorizationStorageRepository;
import com.example.restexample.data.repositories.AuthorizationStorageRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 13.11.16.
 */
@Module
public class DataModule {
    private static final String TAG = DataModule.class.getSimpleName();

    @Provides
    AuthorizationStorageRepository provideAuthStorage() {
        return new AuthorizationStorageRepositoryImpl();
    }
}
