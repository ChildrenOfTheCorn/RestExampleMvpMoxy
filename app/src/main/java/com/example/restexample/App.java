package com.example.restexample;

import android.app.Application;

import com.example.restexample.data.RestConst;
import com.example.restexample.di.components.AppComponent;
import com.example.restexample.di.components.DaggerAppComponent;
import com.example.restexample.di.modules.AppModule;
import com.example.restexample.di.modules.DataModule;
import com.example.restexample.di.modules.RestModule;

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
                .appModule(new AppModule(this))
                .restModule(new RestModule(RestConst.END_POINT))
                .build();
    }

    public static AppComponent getAppComponent() {
        return sInstanse.appComponent;
    }
}
