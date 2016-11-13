package com.example.restexample.data;

/**
 * Created by grishberg on 13.11.16.
 * Константы серверного API
 */
public final class RestConst {
    private static final String TAG = RestConst.class.getSimpleName();
    public static final String END_POINT = "";

    // Имена методов сервера
    public final static class Methods {
        private Methods() {
        }

        public static final String AUTHORIZATION = "authorization";
    }

    // имена полей
    public final static class Fields {
        private Fields() {
        }

        public static final String LOGIN = "ean";
        public static final String PASSWORD = "password";
        public static final String TOKEN = "token";
    }

    // Ошибки
    public final class Errors {
        public static final int WRONG_CREDENTIALS = 1000;
        public static final int TOKEN_INVALID = 1001;
    }
}
