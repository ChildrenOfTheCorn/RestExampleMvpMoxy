package com.example.restexample.di.modules;

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

    private final AuthorizationStorageRepository repository;

    public DataModule(final AuthorizationStorageRepository repository) {
        this.repository = repository;
    }

    @Provides
    @Singleton
    AuthorizationStorageRepository provideAuthStorage() {
        return repository;
    }
}
