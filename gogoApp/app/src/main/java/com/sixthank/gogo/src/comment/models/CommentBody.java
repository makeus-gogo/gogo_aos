
package com.sixthank.gogo.src.comment.models;

import com.google.gson.annotations.SerializedName;


public class CommentBody {

    @SerializedName("description")
    private String mDescription;

    public CommentBody(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

}
