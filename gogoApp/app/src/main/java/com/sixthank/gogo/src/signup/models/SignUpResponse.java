
package com.sixthank.gogo.src.signup.models;


import com.google.gson.annotations.SerializedName;


public class SignUpResponse {

    @SerializedName("data")
    private String mData;
    @SerializedName("httpCode")
    private String mHttpCode;
    @SerializedName("message")
    private String mMessage;

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }

    public String getHttpCode() {
        return mHttpCode;
    }

    public void setHttpCode(String httpCode) {
        mHttpCode = httpCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
