package com.example.restexample;

import android.util.Log;

import com.example.restexample.data.repositories.AuthorizationRepository;
import com.example.restexample.data.repositories.AuthorizationStorageRepository;
import com.example.restexample.di.components.DaggerTestAppComponent;
import com.example.restexample.di.modules.DataModule;
import com.example.restexample.di.modules.TestRepositoriesModule;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.mockito.Mockito.mock;

/**
 * Created by grishberg on 13.11.16.
 * базовый тестовый класс, который инитит даггер тестовыми зависимостями
 */
@PrepareForTest(android.util.Log.class)
public class BaseTestCase {
    private static final String TAG = BaseTestCase.class.getSimpleName();

    public AuthorizationRepository repository;
    AuthorizationStorageRepository authStorage;

    public BaseTestCase() {
        authStorage = mock(AuthorizationStorageRepository.class);
        repository = mock(AuthorizationRepository.class);
        PowerMockito.mockStatic(Log.class);
        App.initAppComponent(
                DaggerTestAppComponent.builder()
                        .dataModule(new DataModule(authStorage))
                        .testRepositoriesModule(new TestRepositoriesModule(repository))
                        .build());
    }
}
