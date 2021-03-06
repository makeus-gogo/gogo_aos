package com.sixthank.gogo.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.ActivitySplashBinding;
import com.sixthank.gogo.src.common.BaseActivity;
import com.sixthank.gogo.src.login.LoginActivity;
import com.sixthank.gogo.src.main.MainActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        binding.splashLogoTxt.startAnimation(aniFade);
        binding.splashLogo.startAnimation(aniFade);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_splash, R.anim.fade_out);
                finish();
            }
        }, 2000);
    }
}