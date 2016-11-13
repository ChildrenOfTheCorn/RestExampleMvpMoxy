package com.example.restexample.presentation.view.common;

import com.arellomobile.mvp.MvpView;

/**
 * Created by grishberg on 13.11.16.
 */

public interface BaseAuthRouteView extends MvpView {
    void showAuthScreen(String login);
}
