package com.sixthank.gogo.src.comment.interfaces;

import com.sixthank.gogo.src.comment.models.Data;

import java.util.List;

public interface CommentActivityView {
    void getCommentsSuccess(List<Data> data);
    void getCommentsFailure(String message);

    void postCommentSuccess(Data data);
    void postCommentFailure(String message);
}
