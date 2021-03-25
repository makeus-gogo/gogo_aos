
package com.sixthank.gogo.src.detail.models;

import com.google.gson.annotations.SerializedName;

public class BoardAnswerBody {

    @SerializedName("contentId")
    private int mContentId;

    public BoardAnswerBody(int mContentId) {
        this.mContentId = mContentId;
    }

    public int getContentId() {
        return mContentId;
    }

    public void setContentId(int contentId) {
        mContentId = contentId;
    }

}
