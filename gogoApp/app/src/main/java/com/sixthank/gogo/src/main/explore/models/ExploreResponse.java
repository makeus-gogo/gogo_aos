
package com.sixthank.gogo.src.main.explore.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ExploreResponse {

    @SerializedName("data")
    private List<Data> mData;
    @SerializedName("httpCode")
    private String mErrorCode;
    @SerializedName("message")
    private String mMessage;

    public List<Data> getData() {
        return mData;
    }

    public void setData(List<Data> data) {
        mData = data;
    }

    public String getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(String httpCode) {
        mErrorCode = httpCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public static class Data {

        @SerializedName("description")
        private String mDescription;
        @SerializedName("endDateTime")
        private String mEndDateTime;
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
        @SerializedName("pictureUrl")
        private String mPictureUrl;

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

        public String getPictureUrl() {
            return mPictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            mPictureUrl = pictureUrl;
        }
    }
}
