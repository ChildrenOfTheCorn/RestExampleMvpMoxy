package com.example.restexample.injection;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 21.10.16.
 */
@Module
public class RestModule {
    private static final String TAG = RestModule.class.getSimpleName();
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private String mBaseUrl;

    // Constructor needs one parameter to instantiate.
    public RestModule(String baseUrl) {
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
        Interceptor interceptor = provideInterceptor();

        OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
        return defaultHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    Api provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}