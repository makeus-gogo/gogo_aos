package com.sixthank.gogo.src.detail;

import android.os.Bundle;

import com.sixthank.gogo.databinding.ActivityBoardDetailBinding;
import com.sixthank.gogo.src.common.BaseActivity;

public class BoardDetailActivity extends BaseActivity<ActivityBoardDetailBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}