package com.sixthank.gogo.src.post.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.FragmentPostFirstBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.post.PostActivity;

import java.util.ArrayList;
import java.util.Objects;


public class PostFirstFragment extends BaseFragment<FragmentPostFirstBinding> {

    PostActivity mParentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostFirstBinding.inflate(inflater, container, false);
        mParentActivity = (PostActivity) getActivity();

        binding.postFirstNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mParentActivity.switchFragment(1);
            }
        });

        // viewpager로 수정해야할 듯

        return binding.getRoot();

    }
}