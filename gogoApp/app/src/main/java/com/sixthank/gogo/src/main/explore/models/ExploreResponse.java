
package com.sixthank.gogo.src.main.explore.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sixthank.gogo.src.main.home.models.HomeResponse;

public class ExploreResponse {

    @SerializedName("data")
    private List<Data> mData;
    @SerializedName("errorCode")
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

    public void setErrorCode(String errorCode) {
        mErrorCode = errorCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public static class Board {

        @SerializedName("description")
        private String mDescription;
        @SerializedName("endDateTime")
        private String mEndDateTime;
        @SerializedName("id")
        private int mId;
        @SerializedName("memberId")
        private int mMemberId;
        @SerializedName("pictureUrl")
        private String mPictureUrl;
        @SerializedName("startDateTime")
        private String mStartDateTime;
        @SerializedName("type")
        private String mType;

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

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            mId = id;
        }

        public int getMemberId() {
            return mMemberId;
        }

        public void setMemberId(int memberId) {
            mMemberId = memberId;
        }

        public String getPictureUrl() {
            return mPictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            mPictureUrl = pictureUrl;
        }

        public String getStartDateTime() {
            return mStartDateTime;
        }

        public void setStartDateTime(String startDateTime) {
            mStartDateTime = startDateTime;
        }

        public String getType() {
            return mType;
        }

        public void setType(String type) {
            mType = type;
        }

    }

    public static class Creator {

        @SerializedName("email")
        private String mEmail;
        @SerializedName("id")
        private Long mId;
        @SerializedName("name")
        private String mName;
        @SerializedName("profileUrl")
        private String mProfileUrl;
        @SerializedName("provider")
        private String mProvider;

        public String getEmail() {
            return mEmail;
        }

        public void setEmail(String email) {
            mEmail = email;
        }

        public Long getId() {
            return mId;
        }

        public void setId(Long id) {
            mId = id;
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

    }

    public static class Data {

        @SerializedName("board")
        private Board mBoard;
        @SerializedName("creator")
        private Creator mCreator;

        public Board getBoard() {
            return mBoard;
        }

        public void setBoard(Board board) {
            mBoard = board;
        }

        public Creator getCreator() {
            return mCreator;
        }

        public void setCreator(Creator creator) {
            mCreator = creator;
        }

    }
}
