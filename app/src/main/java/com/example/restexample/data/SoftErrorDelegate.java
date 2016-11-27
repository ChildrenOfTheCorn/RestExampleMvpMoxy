package com.example.restexample.data;

/**
 * Created by grishberg on 20.11.16.
 */

public interface SoftErrorDelegate<T> {
    Throwable checkSoftError(T body);
}
