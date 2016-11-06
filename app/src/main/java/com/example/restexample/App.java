package com.example.restexample;

import android.app.Application;

import com.example.restexample.injection.components.AppComponent;
import com.example.restexample.injection.components.AuthorizationComponent;
import com.example.restexample.injection.components.ServicesComponent;
import com.example.restexample.injection.components.WalletsComponent;
import com.example.restexample.injection.components.WalletsEntityComponent;
import com.example.restexample.injection.modules.AppModule;
import com.example.restexample.injection.modules.AuthorizationModule;

/**
 * Created by grishberg on 20.10.16.
 */
public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    // Dagger 2 components
    private AppComponent appComponent;
    private AuthorizationComponent authorizationComponent;
    private WalletsComponent walletsComponent;
    private ServicesComponent servicesComponent;
    private WalletsEntityComponent walletsEntityComponent;

    private static App sInstanse;

    public static App get() {
        return sInstanse;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstanse = this;
        //appComponent = DaggerAppComponent.builder()
        //        .appModule(new AppModule(getApplicationContext()))
        //        .build();
    }

    public AuthorizationComponent getAuthorizationComponent() {
        // always get only one instance
        if (authorizationComponent == null) {
            // start lifecycle of authorizationComponent
            authorizationComponent = appComponent.provideAuthComponent(new AuthorizationModule());
        }
        return authorizationComponent;
    }

    public void clearAuthorizationComponent() {
        // end lifecycle of chatComponent
        authorizationComponent = null;
    }
}
