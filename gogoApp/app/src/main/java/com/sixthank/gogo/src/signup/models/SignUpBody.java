
package com.sixthank.gogo.src.signup.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class SignUpBody implements Serializable {

    @SerializedName("deviceToken")
    private String mDeviceToken;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("name")
    private String mName;
    @SerializedName("profileUrl")
    private String mProfileUrl;
    @SerializedName("provider")
    private String mProvider;

    public String getDeviceToken() {
        return mDeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        mDeviceToken = deviceToken;
    }

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

    public SignUpBody(String mEmail, String mName, String mProfileUrl) {
        this.mEmail = mEmail;
        this.mName = mName;
        this.mProfileUrl = mProfileUrl;
    }
}
