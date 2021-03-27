
package com.sixthank.gogo.src.common.models;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("data")
    private Object mData;
    @SerializedName("errorCode")
    private String mErrorCode;
    @SerializedName("message")
    private String mMessage;

    public Object getData() {
        return mData;
    }

    public void setData(Object data) {
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
