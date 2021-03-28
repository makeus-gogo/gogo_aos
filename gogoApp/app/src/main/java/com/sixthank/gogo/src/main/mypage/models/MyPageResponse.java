
package com.sixthank.gogo.src.main.mypage.models;

import com.google.gson.annotations.SerializedName;


public class MyPageResponse {

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


    public static class Data {

        @SerializedName("email")
        private String mEmail;
        @SerializedName("id")
        private int mId;
        @SerializedName("name")
        private String mName;
        @SerializedName("profileUrl")
        private String mProfileUrl;
        @SerializedName("provider")
        private String mProvider;

        public String getEmail() {
            return mEmail;
        }

        public void setEmail(String email) {
            mEmail = email;
        }

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            mId = id;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public String getProfileUrl() {
            return mProfileUrl;
        }

        public void setProfileUrl(String profileUrl) {
            mProfileUrl = profileUrl;
        }

        public String getProvider() {
            return mProvider;
        }

        public void setProvider(String provider) {
            mProvider = provider;
        }

    }
}
