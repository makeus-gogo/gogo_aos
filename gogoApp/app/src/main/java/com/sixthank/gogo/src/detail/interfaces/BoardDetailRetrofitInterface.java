package com.sixthank.gogo.src.detail.interfaces;

import com.sixthank.gogo.src.detail.models.BoardDetailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BoardDetailRetrofitInterface {
    @GET("/api/v1/board/{boardId}")
    Call<BoardDetailResponse> getBoardDetail(@Path("boardId") int boardId);
//    @GET("/api/v1/board/{boardId}/answer")
//    Call<BoardDetailResponse> getBoardDetail(@Path("boardId") int boardId);
//
//    @POST("/api/v1/board/{boardId}/answer")
//    Call<BoardDetailResponse> postBoardDetail(@Path("boardId") int boardId);
//
//    @PATCH("/api/v1/board/{boardId}/answer")
//    Call<BoardDetailResponse> patchBoardDetail(@Path("boardId") int boardId);
}
