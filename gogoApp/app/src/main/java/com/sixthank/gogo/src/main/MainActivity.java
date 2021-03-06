package com.sixthank.gogo.src.main;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sixthank.gogo.R;

import com.sixthank.gogo.databinding.ActivityMainBinding;
import com.sixthank.gogo.src.common.BaseActivity;
import com.sixthank.gogo.src.main.explore.ExploreFragment;
import com.sixthank.gogo.src.main.home.HomeFragment;
import com.sixthank.gogo.src.main.mypage.MyPageFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements BottomNavigationView.OnNavigationItemSelectedListener{

    private final Fragment homeFragment = new HomeFragment();
    private final Fragment exploreFragment = new ExploreFragment();
    private final Fragment myPageFragment = new MyPageFragment();
    private final FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;

    private boolean isImageChange;
    private Uri mImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
        binding.bottomNav.setItemIconTintList(null);

        fm.beginTransaction().add(R.id.main_container, myPageFragment, "3").hide(myPageFragment).commit();
        fm.beginTransaction().add(R.id.main_container, exploreFragment, "2").hide(exploreFragment).commit();
        fm.beginTransaction().add(R.id.main_container, homeFragment, "1").commit();

        initVariable();
        initView();
    }

    void initVariable() {

    }

    void initView() {



        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(0x00000000);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                fm.beginTransaction().hide(active).show(homeFragment).commit();
                active = homeFragment;
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                return true;
            case R.id.menu_explore:
                fm.beginTransaction().hide(active).show(exploreFragment).commit();
                active = exploreFragment;
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                return true;
            case R.id.menu_my_page:
                fm.beginTransaction().hide(active).show(myPageFragment).commit();
                active = myPageFragment;
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                return true;
        }
        return false;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 개인 프로필 변경
        MyPageFragment fragment = (MyPageFragment) myPageFragment;
        if(resultCode == RESULT_OK) {
            if(data != null) {
                isImageChange = true;
                mImageUri = data.getData();
                fragment.setProfileImage(data.getData());
            }
        }
    }

    public Uri getImageUrl() {
        return mImageUri;
    }


    public boolean isImageChange() {
        return isImageChange;
    }
}