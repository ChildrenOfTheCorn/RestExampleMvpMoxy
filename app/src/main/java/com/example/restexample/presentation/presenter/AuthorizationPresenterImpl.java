package com.example.restexample.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.restexample.App;
import com.example.restexample.data.repositories.AuthorizationRepository;
import com.example.restexample.domain.exceptions.WrongCredentialsException;
import com.example.restexample.presentation.presenter.common.BaseMvpPresenter;
import com.example.restexample.presentation.view.AuthorizationView;

import javax.inject.Inject;

/**
 * Created by grishberg on 05.11.16.
 */
@InjectViewState
public class AuthorizationPresenterImpl extends BaseMvpPresenter<AuthorizationView>
        implements AuthorizationPresenter {
    private static final String TAG = AuthorizationPresenterImpl.class.getSimpleName();

    @Inject
    AuthorizationRepository authorizationRepository;

    public AuthorizationPresenterImpl() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void authorize(final String login, final CharSequence password) {
        authorizationRepository.authorization(login, password)
                .subscribe(response -> {
                    getViewState().showNextScreen();
                }, throwable -> {
                    if (throwable instanceof WrongCredentialsException) {
                        getViewState().showBadPasswordError();
                    }
                });
    }
}
