
package com.sixthank.gogo.src.detail.models;

import com.google.gson.annotations.SerializedName;
import com.sixthank.gogo.src.common.models.AnswerResultDtoList;

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

    public static class Data implements Serializable{

        @SerializedName("answerResultDtoList")
        private List<AnswerResultDtoList> mAnswerResultDtoList;
        @SerializedName("boardId")
        private int mBoardId;
        @SerializedName("description")
        private String mDescription;
        @SerializedName("endDateTime")
        private String mEndDateTime;
        @SerializedName("hashTags")
        private List<String> mHashTags;
        @SerializedName("pictureUrl")
        private String mPictureUrl;
        @SerializedName("startDateTime")
        private String mStartDateTime;
        @SerializedName("type")
        private String mType;
        @SerializedName("userCheck")
        private int mUserCheck;

        public List<AnswerResultDtoList> getAnswerResultDtoList() {
            return mAnswerResultDtoList;
        }

        public void setAnswerResultDtoList(List<AnswerResultDtoList> answerResultDtoList) {
            mAnswerResultDtoList = answerResultDtoList;
        }

        public int getBoardId() {
            return mBoardId;
        }

        public void setBoardId(int boardId) {
            mBoardId = boardId;
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

        public int getUserCheck() {
            return mUserCheck;
        }

        public void setUserCheck(int userCheck) {
            mUserCheck = userCheck;
        }

    }

}
