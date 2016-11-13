package com.example.restexample.presentation.presenter.common;

import com.arellomobile.mvp.MvpPresenter;
import com.example.restexample.di.components.AppComponent;

import com.example.restexample.presentation.view.common.BaseAuthRouteView;

/**
 * Created by grishberg on 13.11.16.
 */
public class BaseNetworkInteractionPresenter<V extends BaseAuthRouteView> extends MvpPresenter<V> {
    private static final String TAG = BaseNetworkInteractionPresenter.class.getSimpleName();
    private final NetworkInjectionHelper networkInjectionHelper;

    public BaseNetworkInteractionPresenter() {
        networkInjectionHelper = new NetworkInjectionHelper();
    }

    protected void onAuthException() {
        networkInjectionHelper.getAuthorizationStorage().getCurrentLogin().subscribe(login -> getViewState().showAuthScreen(login));
    }
}
