package com.openweather.entity.error;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ignacio Rojas González and Carlos Ramírez Lizán on 16/01/2015
 * Narami Solutions Inc.
 */
public class ApiError {

    public String message;
    @SerializedName("cod")
    private int code;

    public ApiError(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ApiError() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ApiError(String message) {
        this.message = message;
    }

    public boolean hasErrorMsg() {
        return !message.isEmpty();
    }
}
