package com.example.restexample.presentation.ui.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.restexample.R;
import com.example.restexample.presentation.presenter.AuthorizationPresenterImpl;
import com.example.restexample.presentation.ui.common.BaseMvpActivity;
import com.example.restexample.presentation.view.AuthorizationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMvpActivity implements AuthorizationView {

    @InjectPresenter
    AuthorizationPresenterImpl presenter;
    @BindView(R.id.auth_screen_phone)
    EditText authScreenPhone;
    @BindView(R.id.auth_screen_password)
    EditText authScreenPassword;
    @BindView(R.id.auth_screen_button_enter)
    Button authScreenButtonEnter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        authScreenButtonEnter.setOnClickListener(view ->
                presenter.authorize(authScreenPhone.getText().toString(), authScreenPassword.getText()));
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
    public void enableButton(final boolean isEnabled) {
        authScreenButtonEnter.setEnabled(isEnabled);
    }

    @Override
    public void showNextScreen() {
        //TODO перейти на нужный экран
    }

    @Override
    public void showEmptyLoginError() {
        authScreenPhone.setError(getString(R.string.auth_empty_login));
    }

    @Override
    public void showEmptyPasswordError() {
        authScreenPassword.setError(getString(R.string.auth_empty_password));
    }
}
