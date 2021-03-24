
package com.sixthank.gogo.src.post.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.google.gson.annotations.SerializedName;


public class PostBody {

    @SerializedName("contents")
    private List<String> mContents;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("hashTags")
    private List<String> mHashTags;
    @SerializedName("pictureUrl")
    private String mPictureUrl;
    @SerializedName("type")
    private String mType;


    public List<String> getContents() {
        return mContents;
    }

    public void setContents(List<String> contents) {
        mContents = contents;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public List<String> getHashTags() {
        return mHashTags;
    }

    public void setHashTags(List<String> hashTags) {
        mHashTags = hashTags;
    }

    public String getPictureUrl() {
        return mPictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        mPictureUrl = pictureUrl;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public PostBody toMap(HashMap<String, Object> map) {
        if(map.containsKey("description"))
            this.mDescription = (String) map.get("description");
        if(map.containsKey("pictureUrl"))
            this.mPictureUrl = (String) map.get("pictureUrl");
        else this.mPictureUrl = "";
        if(map.containsKey("type"))
            this.mType = (String) map.get("type");
        if(map.containsKey("contents"))
            this.mContents = (List<String>) map.get("contents");
        else this.mContents = new ArrayList<>();
        if(map.containsKey("hashTags"))
            this.mHashTags = (List<String>) map.get("mHashTags");
        else this.mHashTags = new ArrayList<>();

        return this;
    }
}
