package com.example.restexample;

import android.app.Application;
import android.content.Context;

/**
 * Created by grishberg on 20.10.16.
 */
public class App extends Application {
    private static final String TAG = App.class.getSimpleName();

    private static AppComponent appComponent;
    private static Context context;
    private static App sInstanse;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
