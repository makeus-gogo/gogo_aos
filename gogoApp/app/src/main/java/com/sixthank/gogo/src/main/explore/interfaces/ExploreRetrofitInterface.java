package com.sixthank.gogo.src.main.explore.interfaces;

import com.sixthank.gogo.src.detail.models.BoardDetailResponse;
import com.sixthank.gogo.src.main.explore.models.ExploreResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExploreRetrofitInterface {
    @GET("/api/v1/board/search")
    Call<ExploreResponse> getSearchList(
            @Query("lastBoardId") int lastBoardId,
            @Query("size") int size
    );

    @GET("/api/v1/board/search")
    Call<ExploreResponse> getSearchKeywordList(
            @Query("keyword") String keyword,
            @Query("lastBoardId") int lastBoardId,
            @Query("size") int size
    );
}
