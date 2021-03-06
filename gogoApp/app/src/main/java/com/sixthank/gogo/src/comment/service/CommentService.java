package com.sixthank.gogo.src.comment.service;

import com.sixthank.gogo.src.comment.interfaces.CommentActivityView;
import com.sixthank.gogo.src.comment.interfaces.CommentRetrofitInterface;
import com.sixthank.gogo.src.comment.models.CommentBody;
import com.sixthank.gogo.src.comment.models.CommentPostResponse;

import com.sixthank.gogo.src.comment.models.CommentResponse;
import com.sixthank.gogo.src.common.ErrorBodyConverter;
import com.sixthank.gogo.src.common.models.ErrorResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sixthank.gogo.config.ApplicationClass.getRetrofit;

public class CommentService {
    private final CommentActivityView commentActivityView;
    private final CommentRetrofitInterface commentRetrofitInterface = getRetrofit().create(CommentRetrofitInterface.class);

    public CommentService(CommentActivityView commentActivityView) {
        this.commentActivityView = commentActivityView;
    }

    public void getComments(int boardId) {
        commentRetrofitInterface.getComments(boardId).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                CommentResponse commentResponse = response.body();
                if(commentResponse == null) {
                    ErrorResponse errorResponse = ErrorBodyConverter.getErrorResponse(response.errorBody());
                    commentActivityView.getCommentsFailure(errorResponse.getMessage());
                    return;
                }
                commentActivityView.getCommentsSuccess(commentResponse.getData());
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                commentActivityView.getCommentsFailure(null);
            }
        });
    }

    public void addComment(CommentBody body, int boardId) {
        commentRetrofitInterface.postComment(body, boardId).enqueue(new Callback<CommentPostResponse>() {
            @Override
            public void onResponse(Call<CommentPostResponse> call, Response<CommentPostResponse> response) {
                CommentPostResponse commentResponse = response.body();
                if(commentResponse == null) {
                    ErrorResponse errorResponse = ErrorBodyConverter.getErrorResponse(response.errorBody());
                    commentActivityView.postCommentFailure(errorResponse.getMessage());
                    return;
                }
                commentActivityView.postCommentSuccess(commentResponse.getData());
            }

            @Override
            public void onFailure(Call<CommentPostResponse> call, Throwable t) {
                commentActivityView.postCommentFailure(null);
            }
        });
    }
}
