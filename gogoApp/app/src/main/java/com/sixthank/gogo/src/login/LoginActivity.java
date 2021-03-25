package com.sixthank.gogo.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.kakao.auth.Session;

import com.sixthank.gogo.databinding.ActivityLoginBinding;
import com.sixthank.gogo.src.common.BaseActivity;
import com.sixthank.gogo.src.login.interfaces.LoginActivityView;
import com.sixthank.gogo.src.login.models.LoginBody;
import com.sixthank.gogo.src.login.models.LoginResponse;
import com.sixthank.gogo.src.login.service.LoginService;
import com.sixthank.gogo.src.main.MainActivity;
import com.sixthank.gogo.src.signup.SignUpActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements LoginActivityView {

    private final SessionCallback mSessionCallback = new SessionCallback(this);
    private LoginService mLoginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initVariable();
    }

    private void initVariable() {
        Session session = Session.getCurrentSession();
        session.addCallback(mSessionCallback);

        mLoginService = new LoginService(this);
    }

    public void kakaoLogin(String token) {
        mLoginService.kakaoLogin(new LoginBody(token));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 간편로그인
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            Log.d("KAKAO_API", "onActivityResult");
        }
    }

    @Override
    public void getLoginSuccess(LoginResponse.Data data) {
        Intent intent;
        if (data.getType().equals("SIGN_UP")) {
            intent = new Intent(getApplicationContext(), SignUpActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("userInfo", data);
            intent.putExtras(bundle);
        } else {
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }

        startActivity(intent);
    }

    @Override
    public void getLoginFailure(String message) {
        showCustomToast("로그인을 다시 해주세요.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Session.getCurrentSession().removeCallback(mSessionCallback);
    }
}