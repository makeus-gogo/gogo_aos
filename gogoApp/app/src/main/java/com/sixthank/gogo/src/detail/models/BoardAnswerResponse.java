
package com.sixthank.gogo.src.detail.models;

import com.google.gson.annotations.SerializedName;
import com.sixthank.gogo.src.common.models.AnswerResultDtoList;

import java.util.List;


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

        @SerializedName("answerId")
        private int mAnswerId;
        @SerializedName("answerResultDtoList")
        private List<AnswerResultDtoList> mAnswerResultDtoList;
        @SerializedName("boardId")
        private int mBoardId;

        public int getAnswerId() {
            return mAnswerId;
        }

        public void setAnswerId(int answerId) {
            mAnswerId = answerId;
        }

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

    }
}
