package com.sixthank.gogo.src.main.mypage.interfaces;

import com.sixthank.gogo.src.main.mypage.models.MyPageResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyPageRetrofitInterface {
    @GET("/api/v1/member")
    Call<MyPageResponse> getMember();
}
