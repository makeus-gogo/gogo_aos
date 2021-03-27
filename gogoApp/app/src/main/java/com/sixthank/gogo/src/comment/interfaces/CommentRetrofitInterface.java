package com.sixthank.gogo.src.comment.interfaces;

import com.sixthank.gogo.src.comment.models.CommentBody;
import com.sixthank.gogo.src.comment.models.CommentPostResponse;
import com.sixthank.gogo.src.comment.models.CommentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentRetrofitInterface {
    @GET("/api/v1/board/{boardId}/comment")
    Call<CommentResponse> getComments(@Path("boardId") int boardId);
    @POST("/api/v1/board/{boardId}/comment")
    Call<CommentPostResponse> postComment(@Body CommentBody body, @Path("boardId") int boardId);
}
