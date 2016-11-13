package com.example.restexample.di.modules;

import com.example.restexample.data.ApiService;
import com.example.restexample.data.repositories.AuthorizationRepository;
import com.example.restexample.data.repositories.AuthorizationRepositoryImpl;
import com.example.restexample.data.repositories.AuthorizationStorageRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 13.11.16.
 */
@Module
public class RepositoriesModule {
    private static final String TAG = RepositoriesModule.class.getSimpleName();

    @Provides
    @Singleton
    AuthorizationRepository provideAuthRepository(final AuthorizationStorageRepository storageRepository,
                                                  final ApiService apiService) {

        return new AuthorizationRepositoryImpl(storageRepository, apiService);
    }
}
