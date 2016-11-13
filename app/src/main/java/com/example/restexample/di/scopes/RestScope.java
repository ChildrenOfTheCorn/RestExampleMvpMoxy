package com.example.restexample.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by grishberg on 13.11.16.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface RestScope {
}
