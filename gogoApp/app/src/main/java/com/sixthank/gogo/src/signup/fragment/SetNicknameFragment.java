package com.sixthank.gogo.src.signup.fragment;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

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
            completed();
        });
        binding.setEtNickname.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    completed();
                }
                return false;
            }
        });
    }

    private void completed() {
        String nickname = String.valueOf(binding.setEtNickname.getText());
        if(validation(nickname))
            if(mParentActivity != null) {
                mParentActivity.setNickname(nickname);
                mParentActivity.signUp();
            }
    }


    private boolean validation(String input) {
        if(input.isEmpty()) {
            showCustomToast("별명을 입력해주세요.");
            return false;
        }

        return true;
    }
}