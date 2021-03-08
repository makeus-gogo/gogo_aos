package com.sixthank.gogo.src.post.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.R;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.databinding.FragmentPostSelectBinding;
import com.sixthank.gogo.src.post.PostActivity;

public class PostSelectFragment extends BaseFragment<FragmentPostSelectBinding> {

    PostActivity mParentActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentActivity = (PostActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostSelectBinding.inflate(inflater);
        binding.postSelectTvWorryCont.setText(mParentActivity.getMap().get("worryContent").toString());
        binding.postSelectClose.setOnClickListener(v->{
            getActivity().finish();
        });
        binding.postSelectNext.setOnClickListener(v -> {
            mParentActivity.switchFragment(4);
        });
        binding.postSelectRlBg.setOnClickListener(v->{

        });
        return binding.getRoot();
    }
}