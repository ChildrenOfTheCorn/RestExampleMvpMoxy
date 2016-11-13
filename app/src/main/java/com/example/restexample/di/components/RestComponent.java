package com.example.restexample.di.components;

import com.example.restexample.di.modules.RestModule;
import com.example.restexample.di.scopes.RestScope;
import com.example.restexample.presentation.presenter.AuthorizationPresenterImpl;
import com.example.restexample.presentation.presenter.common.NetworkInjectionHelper;


import dagger.Component;

/**
 * Created by grishberg on 13.11.16.
 */
@RestScope
@Component(modules = RestModule.class, dependencies = DataComponent.class)

public interface RestComponent {
    void inject(NetworkInjectionHelper networkInjectionHelper);

    void inject(AuthorizationPresenterImpl authorizationPresenter);
}
