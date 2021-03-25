
package com.sixthank.gogo.src.main.mypage.models;

import com.google.gson.annotations.SerializedName;

public class MyPageBody {

    @SerializedName("name")
    private String mName;
    @SerializedName("profileUrl")
    private String mProfileUrl;

    public MyPageBody(String mName, String mProfileUrl) {
        this.mName = mName;
        this.mProfileUrl = mProfileUrl;
    }

    public MyPageBody(String mName) {
        this.mName = mName;
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

}
