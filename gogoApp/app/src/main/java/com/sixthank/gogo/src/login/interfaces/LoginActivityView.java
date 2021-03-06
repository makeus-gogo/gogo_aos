package com.sixthank.gogo.src.login.interfaces;

import com.sixthank.gogo.src.login.models.LoginResponse;

public interface LoginActivityView {
    void getLoginSuccess(LoginResponse.Data data);
    void getLoginFailure(String message);
}
