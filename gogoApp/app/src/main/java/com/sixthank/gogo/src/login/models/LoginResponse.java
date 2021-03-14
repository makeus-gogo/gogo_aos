
package com.sixthank.gogo.src.login.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    @SerializedName("data")
    private Data mData;
    @SerializedName("errorCode")
    private String mErrorCode;
    @SerializedName("message")
    private String mMessage;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
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

    public static class Data implements Serializable{

        @SerializedName("email")
        private String mEmail;
        @SerializedName("name")
        private String mName;
        @SerializedName("provider")
        private String mProvider;
        @SerializedName("token")
        private String mToken;
        @SerializedName("type")
        private String mType;
        @SerializedName("profileUrl")
        private String mProfileUrl;

        public String getEmail() {
            return mEmail;
        }

        public void setEmail(String email) {
            mEmail = email;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public String getProvider() {
            return mProvider;
        }

        public void setProvider(String provider) {
            mProvider = provider;
        }

        public String getToken() {
            return mToken;
        }

        public void setToken(String token) {
            mToken = token;
        }

        public String getType() {
            return mType;
        }

        public void setType(String type) {
            mType = type;
        }

        public String getProfileUrl() {
            return mProfileUrl;
        }

        public void setProfileUrl(String profileUrl) {
            mProfileUrl = profileUrl;
        }

    }
}
