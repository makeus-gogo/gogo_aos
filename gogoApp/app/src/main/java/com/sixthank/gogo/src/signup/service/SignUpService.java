package com.sixthank.gogo.src.signup.service;

import com.sixthank.gogo.src.signup.interfaces.SignUpActivityView;
import com.sixthank.gogo.src.signup.interfaces.SignUpRetrofitInterface;
import com.sixthank.gogo.src.signup.models.SignUpBody;
import com.sixthank.gogo.src.signup.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sixthank.gogo.config.ApplicationClass.getRetrofit;

public class SignUpService  {
    private final SignUpActivityView signUpActivityView;

    public SignUpService(SignUpActivityView signUpActivityView) {
        this.signUpActivityView = signUpActivityView;
    }

    public void signUp(SignUpBody body) {
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.signUp(body).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse signUpResponse = response.body();
                if(signUpResponse == null) {
                    signUpActivityView.signUpFailure();
                    return;
                }
                signUpActivityView.signUpSuccess();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                signUpActivityView.signUpFailure();
            }
        });
    }
}
