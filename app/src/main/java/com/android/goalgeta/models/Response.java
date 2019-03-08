package com.android.goalgeta.models;

import com.google.gson.annotations.SerializedName;

public class Response {
    //    Use the required data type
    @SerializedName("error")
    public boolean error;

    @SerializedName("message")
    private String msg;

    public Response(boolean error, String msg) {
        this.error = error;
        this.msg = msg;
    }

    public boolean isError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }
}
