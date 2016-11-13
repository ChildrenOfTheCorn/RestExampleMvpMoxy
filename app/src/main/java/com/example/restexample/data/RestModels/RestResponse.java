package com.example.restexample.data.RestModels;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by grishberg on 13.11.16.
 */
public class RestResponse<T> {
    private static final String TAG = RestResponse.class.getSimpleName();
    private static final String DATA = "data";
    private static final String ERROR = "error";
    @Getter
    private final boolean isCached;
    @SerializedName(DATA)
    @Getter
    private final T data;
    @SerializedName(ERROR)
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
