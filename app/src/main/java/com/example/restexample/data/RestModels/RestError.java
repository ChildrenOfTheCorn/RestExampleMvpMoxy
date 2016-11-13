package com.example.restexample.data.RestModels;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by grishberg on 13.11.16.
 */
public class RestError {
    private static final String TAG = RestError.class.getSimpleName();
    public static final String CODE = "code";
    public static final String MESSAGE = "message";
    @SerializedName(CODE)
    @Getter
    private int code;
    @SerializedName(MESSAGE)
    @Getter private String message;
}
