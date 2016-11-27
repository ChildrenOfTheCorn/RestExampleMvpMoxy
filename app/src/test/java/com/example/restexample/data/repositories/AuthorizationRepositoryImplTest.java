package com.example.restexample.data.repositories;

import com.example.restexample.data.ApiService;
import com.example.restexample.data.RestModels.AuthResponse;
import com.example.restexample.data.RestModels.RestResponse;
import com.example.restexample.data.SampleErrorChecker;
import com.example.restexample.data.SoftErrorDelegate;
import com.example.restexample.util.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Response;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.*;

/**
 * Created by grishberg on 14.11.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorizationRepositoryImplTest {

    @Rule
    // Must be added to every test class that targets app code that uses RxJava
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    AuthorizationStorageRepository storageRepository;

    @Mock
    ApiService apiService;

    @Mock
    Call<RestResponse<AuthResponse>> restResponseCall; // замоканный call ретрофита

    @Mock
    RestResponse<AuthResponse> restResponse; // тело респонза, которое будет возвращено

    Response<RestResponse<AuthResponse>> response;// респонз ретрофита
    @Mock
    RestResponse<AuthResponse> responseBody;

    AuthorizationRepository repository;
    SoftErrorDelegate<RestResponse> softErrorDelegate = new SampleErrorChecker();

    @Before
    public void setUp() throws Exception {
        response = Response.success(restResponse);
        when(restResponseCall.clone()).thenReturn(restResponseCall);
        when(restResponseCall.execute()).thenReturn(response);
        //when(response.body()).thenReturn(responseBody);
        repository = new AuthorizationRepositoryImpl(storageRepository, apiService, softErrorDelegate);
    }

    @Test
    public void testValidResponse() {
        //given
        final TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
        when(apiService.authorization(anyString(), anyString())).thenReturn(restResponseCall);
        //when
        repository.authorization("12345", "123").subscribe(testSubscriber);
        //then
        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Arrays.asList(true));
        verify(storageRepository, times(1)).storeToken(anyString());
        verify(storageRepository, times(1)).setCurrentLogin("12345");
    }
}