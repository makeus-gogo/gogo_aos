package com.sixthank.gogo.src.login;

import android.util.Log;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;
import com.sixthank.gogo.src.login.models.LoginBody;

public class SessionCallback implements ISessionCallback {
    // 로그인에 성공한 상태
    private String mAccessToken;

    @Override
    public void onSessionOpened() {
        requestMe();
    }

    // 로그인에 실패한 상태
    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());
    }

    // 사용자 정보 요청
    public void requestMe() {
        UserManagement.getInstance()
                .me(new MeV2ResponseCallback() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                    }

                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "사용자 정보 요청 실패: " + errorResult);
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {
                        mAccessToken = Session.getCurrentSession().getTokenInfo().getAccessToken();
                        Log.i("KAKAO_API", "token: " + mAccessToken);
                        LoginActivity activity = new LoginActivity();
                        activity.kakaoLogin();
//                        mLoginService.kakaoLogin(new LoginBody(accessToken));
                    }
                });
    }

    public String getAccessToken() {
        return mAccessToken;
    }
}
