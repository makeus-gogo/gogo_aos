package com.sixthank.gogo.src.main.explore.interfaces;

import com.sixthank.gogo.src.main.explore.models.ExploreResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExploreRetrofitInterface {
    @GET("/api/v1/board/search")
    Call<ExploreResponse> getSearchList(@Query("keyword") String keyword);
}
