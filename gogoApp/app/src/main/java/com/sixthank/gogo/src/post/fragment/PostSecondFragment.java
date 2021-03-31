package com.sixthank.gogo.src.post.fragment;

import android.annotation.SuppressLint;
import android.net.Uri;
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
    private String mDescription;

    public static PostSecondFragment newInstance(String description) {

        Bundle args = new Bundle();
        args.putString("description", description);
        PostSecondFragment fragment = new PostSecondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mParentActivity = (PostActivity) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostSecondBinding.inflate(inflater, container, false);

        intiVariable();
        initView();
        initListener();

        return binding.getRoot();
    }

    private void intiVariable() {
        mDescription = getArguments().getString("description");
    }

    private void initView() {
        binding.postSecondRvColor.setAdapter(new ColorListAdapter(getContext()));
        binding.postSecondRvTxt.setAdapter(new TextListAdapter(getContext()));
        binding.postSecondEtContent.setText(mDescription);
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
                        binding.postSecondClPhoto.setVisibility(View.VISIBLE);
                        break;
                    case R.id.post_second_write:
                        binding.postSecondLlColor.setVisibility(View.VISIBLE);
                        binding.postSecondRvTxt.setVisibility(View.INVISIBLE);
                        binding.postSecondClPhoto.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.post_second_typo:
                        binding.postSecondLlColor.setVisibility(View.INVISIBLE);
                        binding.postSecondRvTxt.setVisibility(View.VISIBLE);
                        binding.postSecondClPhoto.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });

        binding.postSecondNext.setOnClickListener(this);
        binding.postSecondImgClose.setOnClickListener(this);
        binding.postSecondTvTakePhoto.setOnClickListener(this);
        binding.postSecondTvAlbum.setOnClickListener(this);
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
            case R.id.post_second_tv_take_photo:
                mParentActivity.takePhoto();
                binding.postSecondCamera.setChecked(false);
                binding.postSecondClPhoto.setVisibility(View.INVISIBLE);
                break;
            case R.id.post_second_tv_album:
                mParentActivity.getAlbum();
                binding.postSecondCamera.setChecked(false);
                binding.postSecondClPhoto.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void setBindingImage(Uri uri) {
        binding.postSecondImg.setImageURI(uri);
    }
}