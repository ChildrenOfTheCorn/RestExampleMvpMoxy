package com.example.restexample.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.restexample.App;
import com.example.restexample.data.repositories.AuthorizationRepository;
import com.example.restexample.presentation.presenter.common.BaseMvpPresenter;
import com.example.restexample.presentation.view.AuthorizationView;
import com.example.restexample.utils.StringUtils;

import javax.inject.Inject;

/**
 * Created by grishberg on 05.11.16.
 */
@InjectViewState
public class AuthorizationPresenterImpl extends BaseMvpPresenter<AuthorizationView>
        implements AuthorizationPresenter {

    private static final String TAG = AuthorizationPresenterImpl.class.getSimpleName();
    private static final int MIN_PASSWORD_LENGTH = 4;

    @Inject
    AuthorizationRepository authorizationRepository;

    public AuthorizationPresenterImpl() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void authorize(final String login, final CharSequence password) {


        final boolean isLoginEmpty = StringUtils.isEmpty(login);
        final boolean isPasswordInvalid = StringUtils.isEmpty(password) || password.length() < MIN_PASSWORD_LENGTH;

        if (isLoginEmpty) {
            getViewState().showEmptyLoginError();
        } else {
            getViewState().hideLoginError();
        }
        if (isPasswordInvalid) {
            if (StringUtils.isEmpty(password)) {
                getViewState().showEmptyPasswordError();
            } else {
                getViewState().showBadPasswordError();
            }
        } else {
            getViewState().hidePasswordError();
        }
        if (isLoginEmpty || isPasswordInvalid) {
            return;
        }

        getViewState().showProgress();
        authorizationRepository.authorization(login, password)
                .subscribe(response -> {
                    getViewState().hideProgress();
                    getViewState().showNextScreen();
                }, throwable -> {

                    getViewState().hideProgress();

                    getViewState().showNetworkError(throwable.getMessage());

                });
    }
}
