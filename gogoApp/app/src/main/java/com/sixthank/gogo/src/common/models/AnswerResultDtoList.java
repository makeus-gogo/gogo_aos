package com.sixthank.gogo.src.common.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class AnswerResultDtoList implements Serializable {

    @SerializedName("check")
    private int mCheck;
    @SerializedName("content")
    private String mContent;
    @SerializedName("contentId")
    private int mContentId;
    @SerializedName("percentage")
    private int mPercentage;

    public int getCheck() {
        return mCheck;
    }

    public void setCheck(int check) {
        mCheck = check;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getContentId() {
        return mContentId;
    }

    public void setContentId(int contentId) {
        mContentId = contentId;
    }

    public int getPercentage() {
        return mPercentage;
    }

    public void setPercentage(int percentage) {
        mPercentage = percentage;
    }


}
