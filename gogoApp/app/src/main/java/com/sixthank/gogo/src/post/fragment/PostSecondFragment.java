package com.sixthank.gogo.src.post.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.FragmentPostSecondBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.post.PostActivity;
import com.sixthank.gogo.src.post.fragment.adapter.ColorListAdapter;
import com.sixthank.gogo.src.post.fragment.adapter.TextListAdapter;


public class PostSecondFragment extends BaseFragment<FragmentPostSecondBinding> implements View.OnClickListener {

    private PostActivity mParentActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mParentActivity = (PostActivity) getActivity();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostSecondBinding.inflate(inflater, container, false);

        initVariable();

        initListener();

        return binding.getRoot();
    }

    private void initVariable() {
        binding.postSecondRvColor.setAdapter(new ColorListAdapter());
        binding.postSecondRvTxt.setAdapter(new TextListAdapter());
    }

    private void initListener() {
        binding.postSecondStyle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.post_second_camera:
                        binding.postSecondLlColor.setVisibility(View.INVISIBLE);
                        binding.postSecondRvTxt.setVisibility(View.INVISIBLE);
//                        mParentActivity.getAlbum();
                        mParentActivity.takePhoto();
                        break;
                    case R.id.post_second_write:
                        binding.postSecondLlColor.setVisibility(View.VISIBLE);
                        binding.postSecondRvTxt.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.post_second_typo:
                        binding.postSecondLlColor.setVisibility(View.INVISIBLE);
                        binding.postSecondRvTxt.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        binding.postSecondNext.setOnClickListener(this);
        binding.postSecondImgClose.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.post_second_next:
                mParentActivity.setValueString("type", "OX");
                mParentActivity.setValueString("description", binding.postSecondEtContent.getText().toString());
                mParentActivity.addBoard();
                break;
            case R.id.post_second_img_close:
                mParentActivity.finish();
                break;
        }
    }

    public FragmentPostSecondBinding getBinding() {
        return binding;
    }

}