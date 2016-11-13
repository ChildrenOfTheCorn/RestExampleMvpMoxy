package com.example.restexample.presentation.presenter;

/**
 * Created by grishberg on 13.11.16.
 */
public interface AuthorizationPresenter {
    void authorize(String login, CharSequence password);
}