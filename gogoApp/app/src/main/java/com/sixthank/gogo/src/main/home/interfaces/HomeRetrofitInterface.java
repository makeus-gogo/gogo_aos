package com.sixthank.gogo.src.main.home.interfaces;

import com.sixthank.gogo.src.main.home.models.HomeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface HomeRetrofitInterface {
    @GET("/api/v1/board/list")
    Call<HomeResponse> getTopNBoardList(@Query("lastBoardId") int lastBoardId, @Query("size") int size);

    @GET("/api/v1/board/list")
    Call<HomeResponse> getBoardList(@Query("lastBoardId") int lastBoardId, @Query("size") int size);
}
