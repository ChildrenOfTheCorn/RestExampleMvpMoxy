package com.example.restexample.domain.exceptions;

import com.example.restexample.data.RestModels.RestError;

/**
 * Created by grishberg on 13.11.16.
 * Ошибка авторизации - неверный логин или пароль
 */
public class WrongCredentialsException extends Throwable{
    private static final String TAG = WrongCredentialsException.class.getSimpleName();

    public WrongCredentialsException(final RestError message) {
        super(message != null ? message.getMessage() : "");
    }
}
