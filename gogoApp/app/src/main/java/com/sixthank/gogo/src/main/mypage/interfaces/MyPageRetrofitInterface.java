package com.sixthank.gogo.src.main.mypage.interfaces;

import com.sixthank.gogo.src.main.mypage.models.MyPageBody;
import com.sixthank.gogo.src.main.mypage.models.MyPageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;

public interface MyPageRetrofitInterface {
    @GET("/api/v1/member")
    Call<MyPageResponse> getMember();

    @PATCH("/api/v1/member")
    Call<MyPageResponse> patchMember(@Body MyPageBody body);
}
