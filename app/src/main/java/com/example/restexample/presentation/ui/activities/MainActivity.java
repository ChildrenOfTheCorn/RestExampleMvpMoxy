package com.example.restexample.presentation.ui.activities;

import android.os.Bundle;

import com.example.mvpframework.view.MvpActivity;
import com.example.restexample.R;
import com.example.restexample.presentation.presenter.AuthorizationPresenter;
import com.example.restexample.presentation.view.AuthorizationView;

public class MainActivity extends MvpActivity<AuthorizationPresenter> implements AuthorizationView{

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected AuthorizationPresenter injectPresenter() {
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showBadPasswordError() {

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void setLoginButtonVisibility(final boolean isVisible) {

    }
}
