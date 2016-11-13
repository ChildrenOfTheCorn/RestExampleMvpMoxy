package com.example.restexample.presentation.view;

import com.example.restexample.presentation.view.common.BaseMvpView;

/**
 * Created by grishberg on 13.11.16.
 */
public interface AuthorizationView extends BaseMvpView {
    void showProgress();

    void hideProgress();

    void showBadPasswordError();

    void showNetworkError();

    void setLoginButtonVisibility(boolean isVisible);

    void showNextScreen();
}