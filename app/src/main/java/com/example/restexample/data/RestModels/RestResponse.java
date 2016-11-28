package com.example.restexample.data.RestModels;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by grishberg on 13.11.16.
 */
public class RestResponse<T> {
    private static final String TAG = RestResponse.class.getSimpleName();

    @Getter
    @SerializedName("success")
    private String success;

    @Getter
    private final boolean isCached;

    @SerializedName("data")
    @Getter
    private final T data;

    @SerializedName("error")
    @Getter
    private RestError error;

    public RestResponse(final T data) {
        this.data = data;
        this.isCached = false;
    }

    public RestResponse(final T data, final boolean isCached) {
        this.data = data;
        this.isCached = isCached;
    }
}
