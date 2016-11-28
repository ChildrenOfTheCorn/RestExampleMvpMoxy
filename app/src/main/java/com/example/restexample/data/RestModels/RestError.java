package com.example.restexample.data.RestModels;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by grishberg on 13.11.16.
 */
public class RestError {
    private static final String TAG = RestError.class.getSimpleName();
    @SerializedName("code")
    @Getter
    private int code;

    @SerializedName("message")
    @Getter
    private String message;
}
