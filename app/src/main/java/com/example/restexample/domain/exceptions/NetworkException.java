package com.example.restexample.domain.exceptions;

/**
 * Created by grishberg on 13.11.16.
 * Какая то сетевая ошибка
 */
public class NetworkException extends Throwable {
    private static final String TAG = NetworkException.class.getSimpleName();

    public static final String ERROR_404 = "HTTP/1.1 404 Not Found";
    public static final String ERROR_400 = "400 Bad request, parameter not found";

    public static final String ERROR_UNKNOWN = "Unknown exception";

    public NetworkException(final int code) {
        super(getMessage(code));
    }

    private static String getMessage(final int code) {
        switch (code) {
            case 404:
                return ERROR_404;
            case 400:
                return ERROR_400;
        }
        return ERROR_UNKNOWN;
    }
}
