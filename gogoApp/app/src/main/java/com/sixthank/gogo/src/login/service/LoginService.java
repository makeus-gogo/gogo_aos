package com.sixthank.gogo.src.login.service;

import com.sixthank.gogo.src.login.interfaces.LoginActivityView;
import com.sixthank.gogo.src.login.interfaces.LoginRetrofitInterface;
import com.sixthank.gogo.src.login.models.LoginBody;
import com.sixthank.gogo.src.login.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sixthank.gogo.config.ApplicationClass.X_ACCESS_TOKEN;
import static com.sixthank.gogo.config.ApplicationClass.getRetrofit;
import static com.sixthank.gogo.config.ApplicationClass.putSharedPreferenceString;

public class LoginService {

    private final LoginActivityView loginActivityView;

    final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);

    public LoginService(LoginActivityView loginActivityView) {
        this.loginActivityView = loginActivityView;
    }

    public void kakaoLogin(LoginBody body) {
        loginRetrofitInterface.kakaoLogin(body).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if(loginResponse == null) {
                    loginActivityView.getLoginFailure(null);
                    return;
                }
                putSharedPreferenceString(X_ACCESS_TOKEN,  loginResponse.getData().getToken());

                loginActivityView.getLoginSuccess(loginResponse.getData());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginActivityView.getLoginFailure(null);
            }
        });
    }

    public void googleLogin(LoginBody body) {
        loginRetrofitInterface.googleLogin(body).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(loginResponse == null) {
                    loginActivityView.getLoginFailure(null);
                    return;
                }
                putSharedPreferenceString(X_ACCESS_TOKEN,  loginResponse.getData().getToken());

                loginActivityView.getLoginSuccess(loginResponse.getData());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginActivityView.getLoginFailure(null);
            }
        });
    }
}
