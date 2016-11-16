package com.example.restexample.data.repositories;

import com.example.restexample.data.ApiService;
import com.example.restexample.data.RestModels.AuthResponse;
import com.example.restexample.data.RestModels.RestResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import retrofit2.Call;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by grishberg on 14.11.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorizationRepositoryImplTest {

    @Mock
    AuthorizationStorageRepository storageRepository;

    @Mock
    ApiService apiService;

    @Mock
    Call<RestResponse<AuthResponse>> responseCall;

    AuthorizationRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new AuthorizationRepositoryImpl(storageRepository, apiService);
    }

    @Test
    public void testValidResponse() {
        when(apiService.authorization(anyString(), anyString())).thenReturn(responseCall);
        repository.authorization("12345", "123");
        verify(storageRepository, times(1)).storeToken(anyString());
        verify(storageRepository, times(1)).setCurrentLogin("12345");
    }
}