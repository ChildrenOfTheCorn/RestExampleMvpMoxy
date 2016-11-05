package com.example.restexample.injection;

import com.example.restexample.domain.ApiService;
import com.example.restexample.domain.HttpLogginInterceptor;
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

    private String mBaseUrl;

    // Constructor needs one parameter to instantiate.
    public RestModule(final String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    /**
     * interceptor для логирования
     *
     * @return
     */

    Interceptor provideInterceptor() {
        HttpLogginInterceptor interceptor = new HttpLogginInterceptor();
        interceptor.setLevel(HttpLogginInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .setDateFormat(DATE_PATTERN)
                .create();
        return gson;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        final Interceptor interceptor = provideInterceptor();

        final OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
        return defaultHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(final Gson gson, final OkHttpClient okHttpClient) {
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    ApiService provideRetrofitService(final Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}