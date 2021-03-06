package com.example.restexample;

import android.app.Application;

import com.example.restexample.data.RestConst;
import com.example.restexample.data.repositories.AuthorizationStorageRepository;
import com.example.restexample.data.repositories.AuthorizationStorageRepositoryImpl;
import com.example.restexample.di.components.AppComponent;
import com.example.restexample.di.components.DaggerAppComponent;
import com.example.restexample.di.modules.DataModule;
import com.example.restexample.di.modules.RepositoriesModule;
import com.example.restexample.di.modules.RestModule;


/**
 * Created by grishberg on 20.10.16.
 */
public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    // Dagger 2 components
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        final AuthorizationStorageRepository authStorage = new AuthorizationStorageRepositoryImpl();

        initAppComponent(DaggerAppComponent.builder()
                .dataModule(new DataModule(authStorage))
                .restModule(new RestModule(RestConst.END_POINT))
                .repositoriesModule(new RepositoriesModule())
                .build());
    }

    public static void initAppComponent(final AppComponent component) {
        appComponent = component;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
