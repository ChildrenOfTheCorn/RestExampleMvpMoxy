package com.example.restexample.injection.modules;

import com.example.restexample.injection.scopes.AuthorizationScope;
import com.example.restexample.presentation.presenter.AuthorizationPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 06.11.16.
 */
@Module
public class AuthorizationModule {
    private static final String TAG = AuthorizationModule.class.getSimpleName();

    @Provides
    @AuthorizationScope
    public AuthorizationPresenter provideAuthPresenter(){
        return new AuthorizationPresenter();
    }
}
