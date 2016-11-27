package com.example.restexample.presentation.presenter;

import com.example.restexample.BaseTestCase;
import com.example.restexample.domain.exceptions.WrongCredentialsException;
import com.example.restexample.presentation.view.AuthorizationView;
import com.example.restexample.util.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.Observable;
import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by grishberg on 13.11.16.
 */
@RunWith(PowerMockRunner.class)
public class AuthorizationPresenterImplTest extends BaseTestCase {

    AuthorizationPresenterImpl presenter;

    @Mock
    AuthorizationView view;

    @Before
    public void setUp() throws Exception {
        presenter = new AuthorizationPresenterImpl();
        presenter.attachView(view);
    }

    @Test
    public void testAuthWithEmptyLoginAndPassword() {
        presenter.authorize("", "");
        verify(repository, never()).authorization(anyString(), any(CharSequence.class));
        verify(view, times(1)).showEmptyLoginError();
        verify(view, times(1)).showEmptyPasswordError();
    }

    @Test
    public void testAuthWithEmptyLogin() {
        presenter.authorize("", "12345");
        verify(repository, never()).authorization(anyString(), any(CharSequence.class));
        verify(view, times(1)).showEmptyLoginError();
        verify(view, never()).showEmptyPasswordError();
    }

    @Test
    public void testAuthWithEmptyPassword() {
        presenter.authorize("12345", "");
        verify(repository, never()).authorization(anyString(), any(CharSequence.class));
        verify(view, never()).showEmptyLoginError();
        verify(view, times(1)).showEmptyPasswordError();
    }

    @Test
    public void testAuth() {
        final Observable<Boolean> observable = Observable.just(true);
        when(repository.authorization(anyString(), any(CharSequence.class))).thenReturn(observable);
        presenter.authorize("111", "222");
        verify(repository, times(1)).authorization("111", "222");
        verify(view, never()).showEmptyLoginError();
        verify(view, never()).showEmptyPasswordError();
        verify(view, times(1)).showNextScreen();
    }

    @Test
    public void testAuthWithWrongPassword() {
        final Observable<Boolean> observable = Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                subscriber.onError(new WrongCredentialsException(null));
                subscriber.onCompleted();
            }
        });
        when(repository.authorization(anyString(), any(CharSequence.class))).thenReturn(observable);
        presenter.authorize("111", "222");
        verify(repository, times(1)).authorization("111", "222");
        verify(view, never()).showEmptyLoginError();
        verify(view, never()).showEmptyPasswordError();
        verify(view, never()).showNextScreen();
        verify(view, times(1)).showBadPasswordError();
    }
}