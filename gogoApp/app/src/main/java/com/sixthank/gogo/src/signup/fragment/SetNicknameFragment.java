package com.sixthank.gogo.src.signup.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.databinding.FragmentSetNicknameBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.signup.SignUpActivity;

import java.util.regex.Pattern;

public class SetNicknameFragment extends BaseFragment<FragmentSetNicknameBinding> {

    SignUpActivity mParentActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSetNicknameBinding.inflate(inflater);

        initListener();

        return binding.getRoot();
    }

    private void initListener() {
        mParentActivity = (SignUpActivity) getActivity();

        binding.setTvNext.setOnClickListener(v->{
            String nickname = String.valueOf(binding.setEtNickname.getText());
            if(validation(nickname))
                if(mParentActivity != null) {
                    mParentActivity.setNickname(nickname);
                    mParentActivity.signUp();
                }
        });
    }


    private boolean validation(String input) {
        if(input.isEmpty()) {
            showCustomToast("닉네임을 입력해주세요.");
            return false;
        }

        // 숫자,영문자,한글만 가능
        boolean flag = Pattern.matches("^[a-zA-Z0-9가-힣]*$", input);
        if(!flag) {
            showCustomToast("특수문자 및 이모티콘은 불가능합니다.");
            return false;
        }

        return true;
    }
}