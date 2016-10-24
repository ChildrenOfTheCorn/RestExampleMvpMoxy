package com.example.restexample.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by grishberg on 20.10.16.
 * Экран логина
 */

public interface AuthorizationView extends MvpView {
    void showProgress();

    void hideProgress();
}
