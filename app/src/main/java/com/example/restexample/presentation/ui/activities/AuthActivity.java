package com.example.restexample.presentation.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.restexample.R;
import com.example.restexample.presentation.presenter.AuthorizationPresenterImpl;
import com.example.restexample.presentation.ui.common.BaseMvpActivity;
import com.example.restexample.presentation.view.AuthorizationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthActivity extends BaseMvpActivity implements AuthorizationView {

    @InjectPresenter
    AuthorizationPresenterImpl presenter;

    @BindView(R.id.auth_screen_input_layout_phone)
    TextInputLayout inputLayoutPhone;

    @BindView(R.id.auth_screen_phone)
    EditText authScreenPhone;

    @BindView(R.id.auth_screen_input_layout_password)
    TextInputLayout inputLayoutPassword;

    @BindView(R.id.auth_screen_password)
    EditText authScreenPassword;

    @BindView(R.id.auth_screen_button_enter)
    Button authScreenButtonEnter;


    @BindView(R.id.auth_screen_progress)
    ProgressBar progress;

    AlertDialog dialogBadNetwork;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        authScreenButtonEnter.setOnClickListener(view ->
                presenter.authorize(authScreenPhone.getText().toString(), authScreenPassword.getText()));

        authScreenPassword.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE) {
                presenter.authorize(authScreenPhone.getText().toString(), authScreenPassword.getText());
                return true;
            }
            return false;
        });

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Error");

        dialogBadNetwork = adb.create();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showBadPasswordError() {
        inputLayoutPassword.setError(getString(R.string.auth_password_too_short));
        inputLayoutPassword.setErrorEnabled(true);
    }

    @Override
    public void showNetworkError(String message) {
        dialogBadNetwork.setMessage(message);
        dialogBadNetwork.show();
    }

    @Override
    public void enableButton(final boolean isEnabled) {
        authScreenButtonEnter.setEnabled(isEnabled);
    }

    @Override
    public void showNextScreen() {
        //TODO перейти на нужный экран
        MainActivity.start(this);
        finish();
    }

    @Override
    public void showEmptyLoginError() {
        inputLayoutPhone.setError(getString(R.string.auth_empty_login));
        inputLayoutPhone.setErrorEnabled(true);
    }

    @Override
    public void showEmptyPasswordError() {
        inputLayoutPassword.setError(getString(R.string.auth_empty_password));
        inputLayoutPassword.setErrorEnabled(true);
    }

    @Override
    public void hidePasswordError() {
        inputLayoutPassword.setError(null);
        inputLayoutPassword.setErrorEnabled(false);
    }

    @Override
    public void hideLoginError() {
        inputLayoutPhone.setError(null);
        inputLayoutPhone.setErrorEnabled(false);
    }
}
