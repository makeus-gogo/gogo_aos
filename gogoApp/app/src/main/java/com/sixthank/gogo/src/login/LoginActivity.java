package com.sixthank.gogo.src.login;

import android.app.Activity;
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
    private final SessionCallback mSessionCallback = new SessionCallback();
    private LoginService mLoginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initVariable();

//        binding.loginBtnKakao.setOnClickListener(v->{
//            Log.d("KAKAO_API", "loginBtnKakaoStart");
//            mSession.open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);
//        });
//        binding.logoutBtnKakao.setOnClickListener(v->{
//            UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
//                        @Override
//                        public void onCompleteLogout() {
//                            Log.d("KAKAO_API", "로그아웃되었습니다."); }
//            });
//        });

    }

    private void initVariable() {
        Session session = Session.getCurrentSession();
        session.addCallback(mSessionCallback);
        mLoginService = new LoginService(this);
    }

    public void kakaoLogin() {
        mLoginService.kakaoLogin(new LoginBody(mSessionCallback.getAccessToken()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            Log.d("KAKAO_API", "onActivityResult");
        }
    }

    @Override
    public void getLoginSuccess(LoginResponse.Data data) {
        Intent intent;
        if (data.getType().equals("SIGN_UP")) {
            intent = new Intent(LoginActivity.this, SignUpActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("userInfo", data);
            intent.putExtras(bundle);
        } else
            intent = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(intent);
    }

    @Override
    public void getLoginFailure(String message) {
        showCustomToast("로그인을 다시 해주세요.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(mSessionCallback);
    }

//    public class SessionCallback implements ISessionCallback {
//        // 로그인에 성공한 상태
//        @Override
//        public void onSessionOpened() {
//            requestMe();
//        }
//
//        // 로그인에 실패한 상태
//        @Override
//        public void onSessionOpenFailed(KakaoException exception) {
//            Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());
//        }
//
//        // 사용자 정보 요청
//        public void requestMe() {
//            UserManagement.getInstance()
//                    .me(new MeV2ResponseCallback() {
//                        @Override
//                        public void onSessionClosed(ErrorResult errorResult) {
//                            Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
//                        }
//
//                        @Override
//                        public void onFailure(ErrorResult errorResult) {
//                            Log.e("KAKAO_API", "사용자 정보 요청 실패: " + errorResult);
//                        }
//
//                        @Override
//                        public void onSuccess(MeV2Response result) {
//                            String accessToken = Session.getCurrentSession().getTokenInfo().getAccessToken();
//                            Log.i("KAKAO_API", "token: " + accessToken);
//
//                            mLoginService.kakaoLogin(new LoginBody(accessToken));
//                        }
//                    });
//        }
//    }
}