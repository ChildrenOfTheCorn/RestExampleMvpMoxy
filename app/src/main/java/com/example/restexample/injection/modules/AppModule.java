package com.example.restexample.injection.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.NonNull;

/**
 * Created by grishberg on 06.11.16.
 */
@Module
public class AppModule {
    private static final String TAG = AppModule.class.getSimpleName();
    private final Context appContext;

    public AppModule(@NonNull final Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return appContext;
    }
}
