package com.example.restexample.domain.exceptions;

/**
 * Created by grishberg on 13.11.16.
 * Какая то сетевая ошибка
 */
public class NetworkException extends Throwable {
    private static final String TAG = NetworkException.class.getSimpleName();

    public NetworkException(final int code) {
        super(getMessage(code));
    }

    private static String getMessage(final int code) {
        switch (code) {
            case 404:
                return "HTTP/1.1 404 Not Found";
            case 400:
                return "400 Bad request, parameter not found";
        }
        return "Unknown exception";
    }
}
