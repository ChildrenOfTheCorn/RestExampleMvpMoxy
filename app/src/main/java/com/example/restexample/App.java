package com.example.restexample;

import android.app.Application;

import com.example.restexample.data.RestConst;
import com.example.restexample.di.components.AppComponent;
import com.example.restexample.di.components.DaggerAppComponent;
import com.example.restexample.di.components.DaggerDataComponent;
import com.example.restexample.di.components.DaggerRestComponent;
import com.example.restexample.di.components.DataComponent;
import com.example.restexample.di.components.RestComponent;
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
    private DataComponent dataComponent;
    private RestComponent restComponent;

    private static App sInstanse;

    public static App get() {
        return sInstanse;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstanse = this;
        final DataModule dataModule = new DataModule();
        final AppModule appModule = new AppModule(getApplicationContext());
        final RestModule restModule = new RestModule(RestConst.END_POINT);
        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .build();
        dataComponent = DaggerDataComponent.builder()
                .dataModule(dataModule)
                .build();
        restComponent = DaggerRestComponent.builder()
                .restModule(restModule)
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }

    public RestComponent getRestComponent() {
        return restComponent;
    }
}
