package com.example.restexample.data.interceptors;

import com.example.restexample.data.repositories.AuthorizationStorageRepository;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by grishberg on 13.11.16.
 */
public class ReceivedCookiesInterceptor implements Interceptor {

    public static final String SET_COOKIE = "Set-Cookie";
    private final AuthorizationStorageRepository authorizationStorageRepository;

    public ReceivedCookiesInterceptor(final AuthorizationStorageRepository authorizationStorageRepository) {
        this.authorizationStorageRepository = authorizationStorageRepository;
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers(SET_COOKIE).isEmpty()) {
            final HashSet<String> cookies = new HashSet<>();

            for (final String header : originalResponse.headers(SET_COOKIE)) {
                cookies.add(header);
                if (header.startsWith("token")) {
                    authorizationStorageRepository.storeToken(header);
                }
            }
        }

        return originalResponse;
    }
}
