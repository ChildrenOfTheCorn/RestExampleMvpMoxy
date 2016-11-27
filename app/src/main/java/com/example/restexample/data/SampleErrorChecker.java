package com.example.restexample.data;

import com.example.restexample.data.RestModels.RestResponse;
import com.example.restexample.domain.exceptions.InvalidTokenException;
import com.example.restexample.domain.exceptions.WrongCredentialsException;

import lombok.NonNull;

/**
 * Created by grishberg on 20.11.16.
 */
public class SampleErrorChecker implements SoftErrorDelegate<RestResponse> {
    private static final String TAG = SampleErrorChecker.class.getSimpleName();

    @Override
    public Throwable checkSoftError(@NonNull final RestResponse body) {
        switch (body.getError().getCode()) {
            case RestConst.Errors.WRONG_CREDENTIALS:
                return new WrongCredentialsException(body.getError());
            case RestConst.Errors.TOKEN_INVALID:
                return new InvalidTokenException(body.getError());
            default:
                return null;
        }
    }
}
