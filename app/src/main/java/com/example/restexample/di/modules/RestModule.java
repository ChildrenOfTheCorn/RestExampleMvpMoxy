package com.example.restexample.di.modules;

import com.example.restexample.data.ApiService;
import com.example.restexample.data.HttpLogginInterceptor;
import com.example.restexample.data.interceptors.ReceivedCookiesInterceptor;
import com.example.restexample.data.repositories.AuthorizationRepository;
import com.example.restexample.data.repositories.AuthorizationRepositoryImpl;
import com.example.restexample.data.repositories.AuthorizationStorageRepository;
import com.example.restexample.di.scopes.RestScope;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by grishberg on 21.10.16.
 */
@Module
public class RestModule {
    private static final String TAG = RestModule.class.getSimpleName();
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final String baseUrl;

    // Constructor needs one parameter to instantiate.
    public RestModule(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * interceptor для логирования
     *
     * @return
     */
    @Provides
    @RestScope
    HttpLogginInterceptor provideMainInterceptor() {
        final HttpLogginInterceptor interceptor = new HttpLogginInterceptor();
        interceptor.setLevel(HttpLogginInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * interceptor для куки
     *
     * @return
     */
    @Provides
    @RestScope
    ReceivedCookiesInterceptor provideCookieInterceptor(final AuthorizationStorageRepository authorizationStorageRepository) {
        return new ReceivedCookiesInterceptor(authorizationStorageRepository);
    }

    @Provides
    @RestScope
    Gson provideGson() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .setDateFormat(DATE_PATTERN)
                .create();
        return gson;
    }

    @Provides
    @RestScope
    OkHttpClient provideOkHttpClient(final HttpLogginInterceptor logginInterceptor, final ReceivedCookiesInterceptor cookiesInterceptor) {
        final OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logginInterceptor)
                .addInterceptor(cookiesInterceptor)
                .build();
        return defaultHttpClient;
    }

    @Provides
    @RestScope
    Retrofit provideRetrofit(final Gson gson, final OkHttpClient okHttpClient) {
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @RestScope
    ApiService provideRetrofitService(final Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @RestScope
    AuthorizationRepository provideAuthRepository(final AuthorizationStorageRepository storageRepository,
                                                  final ApiService apiService) {
        return new AuthorizationRepositoryImpl(storageRepository, apiService);
    }
}