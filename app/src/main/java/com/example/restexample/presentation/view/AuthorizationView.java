package com.example.restexample.presentation.view;

import com.example.mvpframework.view.MvpView;

/**
 * Created by grishberg on 20.10.16.
 * Экран логина
 */

public interface AuthorizationView extends MvpView {
    void showProgress();

    void hideProgress();

    void showBadPasswordError();

    void showNetworkError();

    void setLoginButtonVisibility(boolean isVisible);
}
