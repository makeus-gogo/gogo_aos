
package com.sixthank.gogo.src.detail.models;

import com.google.gson.annotations.SerializedName;

public class BoardAnswerResponse {

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

    public static class Data {

        @SerializedName("boardId")
        private int mBoardId;
        @SerializedName("contentId")
        private int mContentId;
        @SerializedName("id")
        private int mId;

        public int getBoardId() {
            return mBoardId;
        }

        public void setBoardId(int boardId) {
            mBoardId = boardId;
        }

        public int getContentId() {
            return mContentId;
        }

        public void setContentId(int contentId) {
            mContentId = contentId;
        }

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            mId = id;
        }

    }
}
