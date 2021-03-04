package com.sixthank.gogo.src.post;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.ActivityPostBinding;
import com.sixthank.gogo.src.common.BaseActivity;
import com.sixthank.gogo.src.post.fragment.PostFirstFragment;
import com.sixthank.gogo.src.post.fragment.PostSecondFragment;
import com.sixthank.gogo.src.post.fragment.PostThirdFragment;

public class PostActivity extends BaseActivity<ActivityPostBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        switchFragment(0);
    }

    public void switchFragment(int index) {
        Fragment fr = new PostFirstFragment();
        switch (index) {
            case 0:
                fr = new PostFirstFragment();
                break;
            case 1:
                fr = new PostSecondFragment();
                break;
            case 2:
                fr = new PostThirdFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.post_container, fr).commit();
    }
}