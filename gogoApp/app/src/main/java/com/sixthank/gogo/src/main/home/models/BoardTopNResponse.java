
package com.sixthank.gogo.src.main.home.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class BoardTopNResponse {

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


    public static class Data {

        @SerializedName("boardId")
        private int mBoardId;
        @SerializedName("boardImageUrl")
        private String mBoardImageUrl;
        @SerializedName("description")
        private String mDescription;
        @SerializedName("memberId")
        private int mMemberId;
        @SerializedName("nickname")
        private String mNickname;
        @SerializedName("profileImageUrl")
        private String mProfileImageUrl;

        public int getBoardId() {
            return mBoardId;
        }

        public void setBoardId(int boardId) {
            mBoardId = boardId;
        }

        public String getBoardImageUrl() {
            return mBoardImageUrl;
        }

        public void setBoardImageUrl(String boardImageUrl) {
            mBoardImageUrl = boardImageUrl;
        }

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public int getMemberId() {
            return mMemberId;
        }

        public void setMemberId(int memberId) {
            mMemberId = memberId;
        }

        public String getNickname() {
            return mNickname;
        }

        public void setNickname(String nickname) {
            mNickname = nickname;
        }

        public String getProfileImageUrl() {
            return mProfileImageUrl;
        }

        public void setProfileImageUrl(String profileImageUrl) {
            mProfileImageUrl = profileImageUrl;
        }

    }
}
