package com.example.restexample.di.modules;

import com.example.restexample.data.repositories.AuthorizationRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 13.11.16.
 */
@Module
public class TestRepositoriesModule {
    private static final String TAG = TestRepositoriesModule.class.getSimpleName();

    private final AuthorizationRepository authorizationRepository;

    public TestRepositoriesModule(final AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Provides
    @Singleton
    AuthorizationRepository provideAuthorizationRepository() {
        return authorizationRepository;
    }
}
