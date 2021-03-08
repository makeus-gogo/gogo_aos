package com.sixthank.gogo.src.post.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.FragmentPostSecondBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.post.PostActivity;
import com.sixthank.gogo.src.post.fragment.adapter.ColorListAdapter;
import com.sixthank.gogo.src.post.fragment.adapter.TextListAdapter;

import java.util.ArrayList;


public class PostSecondFragment extends BaseFragment<FragmentPostSecondBinding> {

    private PostActivity mParentActivity;

    public static PostSecondFragment newInstance() {
        PostSecondFragment fragment = new PostSecondFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mParentActivity = (PostActivity) getActivity();

        checkCameraPermission();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostSecondBinding.inflate(inflater, container, false);

        initView();

        eventControl();

        return binding.getRoot();
    }

    private void initView() {
        String worryContent = mParentActivity.getMap().get("worryContent").toString();
        binding.postSecondTvWorryCont.setText(worryContent);
        binding.postSecondRvColor.setAdapter(new ColorListAdapter());
        binding.postSecondRvTxt.setAdapter(new TextListAdapter());
    }

    private void eventControl() {
        binding.postSecondStyle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.post_second_camera:
                        binding.postSecondLlColor.setVisibility(View.INVISIBLE);
                        binding.postSecondRvTxt.setVisibility(View.INVISIBLE);
                        getAlbum();
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


        binding.postSecondNext.setOnClickListener(v -> {
            mParentActivity.switchFragment(4);
        });

        binding.postSecondImgClose.setOnClickListener(v->{
            mParentActivity.finish();
        });
    }

    private void takePhoto() {

    }

    private void getAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1001);
    }

    private void checkCameraPermission() {
        TedPermission.with(getContext())
                .setRationaleMessage("카메라 권한이 필요합니다.\n(거부할 경우 진행불가)")
                .setDeniedMessage("카메라 권한을 거부하셨습니다.")
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
//            showCustomToast("accent");
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            showCustomToast("deny");
        }
    };

    public FragmentPostSecondBinding getBinding() {
        return binding;
    }
}