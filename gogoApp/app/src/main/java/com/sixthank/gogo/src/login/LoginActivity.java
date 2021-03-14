package com.sixthank.gogo.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;
import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.ActivityLoginBinding;
import com.sixthank.gogo.src.common.BaseActivity;
import com.sixthank.gogo.src.login.interfaces.LoginActivityView;
import com.sixthank.gogo.src.login.models.LoginResponse;
import com.sixthank.gogo.src.login.service.LoginService;
import com.sixthank.gogo.src.main.MainActivity;
import com.sixthank.gogo.src.signup.SignUpActivity;
import com.sixthank.gogo.src.signup.models.SignUpBody;

import java.io.Serializable;


public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements LoginActivityView {
    private final SessionCallback mSessionCallback = new SessionCallback();
    private Session mSession;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    private LoginService mLoginService;
    private UserAccount mKakaoAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mSession = Session.getCurrentSession();
        mSession.addCallback(mSessionCallback);
        mLoginService = new LoginService(this);
//        showCustomToast("callback");
//        binding.loginBtnKakao.setOnClickListener(v->{
//            Log.d("KAKAO_API", "loginBtnKakaoStart");
//            mSession.open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);
//        });
        binding.logoutBtnKakao.setOnClickListener(v->{
            UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                        @Override
                        public void onCompleteLogout() {
                            Log.d("KAKAO_API", "로그아웃되었습니다."); }
            });
        });

        binding.loginBtnGoogle.setOnClickListener(v -> {
            googleLogin();
        });

        binding.logoutBtnGoogle.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
        });

    }

    private void googleLogin() {
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        Log.d("GOOGLE_API","serverAuthCode: " + acct.getServerAuthCode());
        Log.d("GOOGLE_API","token: " + acct.getIdToken());


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mLoginService.googleLogin(acct.getIdToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showCustomToast("Login Success!");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mAuth.getAccessToken(true).toString();
                            updateUI(user);
                        } else {
                            showCustomToast("Login Failure!");
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) { //update ui code here
        if (user != null) {
            Log.d("GOOGLE_API","display Name: " + user.getDisplayName());
            Log.d("GOOGLE_API","profile url: " + user.getPhotoUrl());
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(mSessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 간편로그인 실행 결과를 받아서 SDK로 전달
//        showCustomToast("onActivityResult: " + mSession.getAccessTokenCallback());
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
            }
        } else {
            if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
                Log.d("KAKAO_API", "onActivityResultEnd");
                return;
            }
        }

    }

    @Override
    public void getLoginSuccess(LoginResponse.Data data) {
        Intent intent;
        if(data.getType().equals("SIGN_UP")) {
            intent = new Intent(LoginActivity.this, SignUpActivity.class);
            Bundle bundle = new Bundle();
//            bundle.putSerializable("userInfo", getUserInfo(data.getProvider()));
            bundle.putSerializable("userInfo", data);
            intent.putExtras(bundle);
        }
        else
            intent = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(intent);
    }

    @Override
    public void getLoginFailure(String message) {

    }

    public class SessionCallback implements ISessionCallback {
        // 로그인에 성공한 상태
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
                            String accessToken = Session.getCurrentSession().getTokenInfo().getAccessToken();
                            Log.i("KAKAO_API", "token: " + accessToken);

                            mKakaoAccount = result.getKakaoAccount();

                            mLoginService.kakaoLogin(accessToken);
                        }
                    });
        }
    }

//    private SignUpBody getUserInfo(String type) {
//        // 카카오 계정 정보
//        SignUpBody userInfo = null;
//        if(type.equals("KAKAO")) {
//            if (mKakaoAccount != null) {
//
//                String email = mKakaoAccount.getEmail();
//                if (email != null) {
//                    Log.i("KAKAO_API", "email: " + email);
//
//                } else if (mKakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {
//                    // 동의 요청 후 이메일 획득 가능
//                    // 단, 선택 동의로 설정되어 있다면 서비스 이용 시나리오 상에서 반드시 필요한 경우에만 요청해야 합니다.
//
//                } else {
//                    // 이메일 획득 불가
//                }
//
//                // 프로필
//                Profile profile = mKakaoAccount.getProfile();
//
//                if (profile != null) {
//                    Log.d("KAKAO_API", "nickname: " + profile.getNickname());
//                    Log.d("KAKAO_API", "profile image: " + profile.getProfileImageUrl());
//                    Log.d("KAKAO_API", "thumbnail image: " + profile.getThumbnailImageUrl());
//
//                } else if (mKakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
//                    // 동의 요청 후 프로필 정보 획득 가능
//
//                } else {
//                    // 프로필 획득 불가
//                }
////                userInfo = new SignUpBody(email, profile.getNickname(), profile.getProfileImageUrl());
////                userInfo.setProvider(type);
//            }
//        } else {
//
//        }
//        return userInfo;
//    }
}