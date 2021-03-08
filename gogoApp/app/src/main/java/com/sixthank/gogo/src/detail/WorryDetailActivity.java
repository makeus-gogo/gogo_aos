package com.sixthank.gogo.src.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sixthank.gogo.databinding.ActivityWorryDetailBinding;
import com.sixthank.gogo.src.common.BaseActivity;

public class WorryDetailActivity extends BaseActivity<ActivityWorryDetailBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorryDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}