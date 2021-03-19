
package com.sixthank.gogo.src.detail.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BoardDetailResponse implements Serializable {

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

    public static class Content implements Serializable{

        @SerializedName("content")
        private String mContent;
        @SerializedName("id")
        private int mId;

        public String getContent() {
            return mContent;
        }

        public void setContent(String content) {
            mContent = content;
        }

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            mId = id;
        }

    }

    public static class Data implements Serializable{

        @SerializedName("contents")
        private List<Content> mContents;
        @SerializedName("description")
        private String mDescription;
        @SerializedName("endDateTime")
        private String mEndDateTime;
        @SerializedName("hashTags")
        private List<String> mHashTags;
        @SerializedName("id")
        private int mId;
        @SerializedName("memberId")
        private Long mMemberId;
        @SerializedName("startDateTime")
        private String mStartDateTime;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("type")
        private String mType;
        @SerializedName("pictureUrl")
        private String mPictureUrl;

        public List<Content> getContents() {
            return mContents;
        }

        public void setContents(List<Content> contents) {
            mContents = contents;
        }

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public String getEndDateTime() {
            return mEndDateTime;
        }

        public void setEndDateTime(String endDateTime) {
            mEndDateTime = endDateTime;
        }

        public List<String> getHashTags() {
            return mHashTags;
        }

        public void setHashTags(List<String> hashTags) {
            mHashTags = hashTags;
        }

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            mId = id;
        }

        public Long getMemberId() {
            return mMemberId;
        }

        public void setMemberId(Long memberId) {
            mMemberId = memberId;
        }

        public String getStartDateTime() {
            return mStartDateTime;
        }

        public void setStartDateTime(String startDateTime) {
            mStartDateTime = startDateTime;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getType() {
            return mType;
        }

        public void setType(String type) {
            mType = type;
        }

        public String getPictureUrl() {
            return mPictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            mPictureUrl = pictureUrl;
        }

    }
}
