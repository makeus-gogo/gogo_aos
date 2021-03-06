package com.sixthank.gogo.src.signup;


import android.os.Bundle;

import com.sixthank.gogo.databinding.ActivitySignUpBinding;
import com.sixthank.gogo.src.common.BaseActivity;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}