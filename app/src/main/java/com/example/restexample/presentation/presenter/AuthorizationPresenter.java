package com.example.restexample.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.restexample.presentation.view.AuthorizationView;

/**
 * Created by grishberg on 05.11.16.
 */
@InjectViewState
public class AuthorizationPresenter extends MvpPresenter<AuthorizationView> {
    private static final String TAG = AuthorizationPresenter.class.getSimpleName();
}
