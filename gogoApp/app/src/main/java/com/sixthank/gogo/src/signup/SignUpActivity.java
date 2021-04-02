package com.sixthank.gogo.src.signup;


import android.os.Bundle;

import androidx.fragment.app.FragmentManager;


import com.sixthank.gogo.R;

import com.sixthank.gogo.databinding.ActivitySignUpBinding;
import com.sixthank.gogo.src.common.BaseActivity;
import com.sixthank.gogo.src.login.models.LoginResponse;
import com.sixthank.gogo.src.signup.fragment.CompletedSignUpFragment;
import com.sixthank.gogo.src.signup.fragment.SetNicknameFragment;
import com.sixthank.gogo.src.signup.interfaces.SignUpActivityView;
import com.sixthank.gogo.src.signup.models.SignUpBody;
import com.sixthank.gogo.src.signup.service.SignUpService;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding> implements SignUpActivityView {

    private SignUpService mSignUpService;
    private SignUpBody mSignUpBody;
    private final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initVariable();

        fm.beginTransaction().replace(R.id.sign_up_container, new SetNicknameFragment()).commit();

    }

    private void initVariable() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            LoginResponse.Data data = (LoginResponse.Data) bundle.getSerializable("userInfo");
            mSignUpBody =  new SignUpBody(data.getEmail(), data.getName(),data.getProfileUrl(), data.getProvider());
        }

        mSignUpService = new SignUpService(this);
    }

    @Override
    public void signUpSuccess() {
        showCustomToast(getString(R.string.sign_up_success));
        fm.beginTransaction().replace(R.id.sign_up_container, new CompletedSignUpFragment()).commit();
    }

    @Override
    public void signUpFailure() {
        showCustomToast(getString(R.string.sign_up_fail));
    }

    public void signUp() {
        mSignUpService.signUp(mSignUpBody);
    }

    public void setNickname(String nickname) { mSignUpBody.setName(nickname); }
}