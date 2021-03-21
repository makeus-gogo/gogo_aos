package com.sixthank.gogo.src.signup.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.FragmentCompletedSignUpBinding;
import com.sixthank.gogo.src.common.BaseFragment;

public class CompletedSignUpFragment extends BaseFragment<FragmentCompletedSignUpBinding> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCompletedSignUpBinding.inflate(inflater);

        initView(); // 사용법 추가
        initListener();

        return binding.getRoot();
    }

    private void initView() {

    }
    private void initListener() {
        binding.completedSignUpIvClose.setOnClickListener(v->{
            if(getActivity() != null) getActivity().finish();
        });
    }
}