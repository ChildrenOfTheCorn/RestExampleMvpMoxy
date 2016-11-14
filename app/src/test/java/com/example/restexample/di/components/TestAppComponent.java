package com.example.restexample.di.components;

import com.example.restexample.di.modules.DataModule;
import com.example.restexample.di.modules.TestRepositoriesModule;
import com.example.restexample.presentation.presenter.AuthorizationPresenterImpl;
import com.example.restexample.presentation.presenter.common.NetworkInjectionHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 13.11.16.
 */
@Singleton
@Component(modules = {DataModule.class, TestRepositoriesModule.class})
public interface TestAppComponent extends AppComponent {

    void inject(NetworkInjectionHelper networkInjectionHelper);

    void inject(AuthorizationPresenterImpl authorizationPresenter);
}
