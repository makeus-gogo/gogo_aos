package com.sixthank.gogo.src.login.interfaces;

import com.sixthank.gogo.src.login.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginRetrofitInterface {
    @GET("/api/v1/auth/kakao")
    Call<LoginResponse> kakaoLogin(@Query("accessToken") String accessToken);
}
