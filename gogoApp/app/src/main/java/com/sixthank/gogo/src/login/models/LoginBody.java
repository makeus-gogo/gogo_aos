package com.sixthank.gogo.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("accessToken")
    private String mAccessToken;

    public LoginBody(String accessToken) {
        this.mAccessToken = accessToken;
    }
}
