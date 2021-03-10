
package com.sixthank.gogo.src.post.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PostResponse {

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

    public static class Content {

        @SerializedName("content")
        private String mContent;
        @SerializedName("id")
        private Long mId;

        public String getContent() {
            return mContent;
        }

        public void setContent(String content) {
            mContent = content;
        }

        public Long getId() {
            return mId;
        }

        public void setId(Long id) {
            mId = id;
        }

    }

    public static class Data {

        @SerializedName("contents")
        private List<Content> mContents;
        @SerializedName("description")
        private String mDescription;
        @SerializedName("endDateTime")
        private String mEndDateTime;
        @SerializedName("hashTags")
        private List<String> mHashTags;
        @SerializedName("id")
        private Long mId;
        @SerializedName("memberId")
        private Long mMemberId;
        @SerializedName("startDateTime")
        private String mStartDateTime;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("type")
        private String mType;

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

        public Long getId() {
            return mId;
        }

        public void setId(Long id) {
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

    }
}
