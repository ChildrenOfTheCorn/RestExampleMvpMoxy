package com.example.restexample;

import android.app.Application;

import com.example.restexample.di.components.AppComponent;
import com.example.restexample.di.components.DaggerAppComponent;
import com.example.restexample.di.modules.AppModule;

/**
 * Created by grishberg on 20.10.16.
 */
public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    // Dagger 2 components
    private AppComponent appComponent;

    private static App sInstanse;

    public static App get() {
        return sInstanse;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstanse = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
