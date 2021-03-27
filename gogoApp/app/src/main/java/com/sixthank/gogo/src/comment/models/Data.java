
package com.sixthank.gogo.src.comment.models;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("boardId")
    private Long mBoardId;
    @SerializedName("commentId")
    private Long mCommentId;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("memberId")
    private Long mMemberId;
    @SerializedName("memberName")
    private String mMemberName;
    @SerializedName("memberProfileUrl")
    private String mMemberProfileUrl;

    public Long getBoardId() {
        return mBoardId;
    }

    public void setBoardId(Long boardId) {
        mBoardId = boardId;
    }

    public Long getCommentId() {
        return mCommentId;
    }

    public void setCommentId(Long commentId) {
        mCommentId = commentId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getMemberId() {
        return mMemberId;
    }

    public void setMemberId(Long memberId) {
        mMemberId = memberId;
    }

    public String getMemberName() {
        return mMemberName;
    }

    public void setMemberName(String memberName) {
        mMemberName = memberName;
    }

    public String getMemberProfileUrl() {
        return mMemberProfileUrl;
    }

    public void setMemberProfileUrl(String memberProfileUrl) {
        mMemberProfileUrl = memberProfileUrl;
    }

}
