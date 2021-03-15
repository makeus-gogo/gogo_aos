package com.sixthank.gogo.src.login.interfaces;

import com.sixthank.gogo.src.login.models.LoginBody;
import com.sixthank.gogo.src.login.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginRetrofitInterface {
    @POST("/api/v1/auth/kakao")
    Call<LoginResponse> kakaoLogin(@Body LoginBody body);

    @POST("/api/v1/auth/google")
    Call<LoginResponse> googleLogin(@Body LoginBody body);
}
