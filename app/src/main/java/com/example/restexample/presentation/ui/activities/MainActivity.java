package com.example.restexample.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.restexample.R;
import com.example.restexample.presentation.presenter.AuthorizationPresenter;
import com.example.restexample.presentation.view.AuthorizationView;

public class MainActivity extends AppCompatActivity implements AuthorizationView {

    //@InjectPresenter
    AuthorizationPresenter presenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
