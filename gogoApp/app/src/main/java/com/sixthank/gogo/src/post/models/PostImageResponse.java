
package com.sixthank.gogo.src.post.models;

import com.google.gson.annotations.SerializedName;

public class PostImageResponse {

    @SerializedName("data")
    private String mData;
    @SerializedName("errorCode")
    private String mErrorCode;
    @SerializedName("message")
    private String mMessage;

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }

    public String getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(String errorCode) {
        mErrorCode = errorCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
