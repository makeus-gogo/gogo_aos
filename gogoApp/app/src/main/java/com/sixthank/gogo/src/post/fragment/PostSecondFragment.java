package com.sixthank.gogo.src.post.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sixthank.gogo.databinding.FragmentPostSecondBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.post.PostActivity;
import com.sixthank.gogo.src.post.fragment.adapter.ColorListAdapter;
import com.sixthank.gogo.src.post.fragment.adapter.TextListAdapter;


public class PostSecondFragment extends BaseFragment<FragmentPostSecondBinding> {

    private PostActivity mParentActivity;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public PostSecondFragment() {

    }

    public static PostSecondFragment newInstance(String param1, String param2) {
        PostSecondFragment fragment = new PostSecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mParentActivity = (PostActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostSecondBinding.inflate(inflater, container, false);

        binding.postSecondRvColor.setAdapter(new ColorListAdapter());
        binding.postSecondRvTxt.setAdapter(new TextListAdapter());

        binding.postSecondImgPicture.setOnClickListener(v->{
            binding.postSecondLlColor.setVisibility(View.GONE);
            binding.postSecondRvTxt.setVisibility(View.GONE);
            v.setSelected(true);
        });

        binding.postSecondImgDraw.setOnClickListener(v->{
            binding.postSecondLlColor.setVisibility(View.VISIBLE);
            binding.postSecondRvTxt.setVisibility(View.GONE);
        });

        binding.postSecondImgTxt.setOnClickListener(v -> {
            binding.postSecondLlColor.setVisibility(View.GONE);
            binding.postSecondRvTxt.setVisibility(View.VISIBLE);
        });

        binding.postSecondNext.setOnClickListener(v -> {
           mParentActivity.switchFragment(2);
        });

        return binding.getRoot();
    }
}