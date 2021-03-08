package com.sixthank.gogo.src.signup.interfaces;

import com.sixthank.gogo.src.signup.models.SignUpBody;
import com.sixthank.gogo.src.signup.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {
    @POST("/api/v1/signup")
    Call<SignUpResponse> signUp(@Body SignUpBody body);
}
