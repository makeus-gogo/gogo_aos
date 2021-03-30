package com.sixthank.gogo.src.main.mypage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sixthank.gogo.R;

import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.common.FirebaseStorageUtil;
import com.sixthank.gogo.src.main.MainActivity;
import com.sixthank.gogo.src.main.mypage.interfaces.MyPageFragmentView;
import com.sixthank.gogo.src.main.mypage.models.MyPageBody;
import com.sixthank.gogo.src.main.mypage.models.MyPageResponse;
import com.sixthank.gogo.src.main.mypage.service.MyPageService;
import com.sixthank.gogo.databinding.FragmentMyPageBinding;

public class MyPageFragment extends BaseFragment<FragmentMyPageBinding> implements
        MyPageFragmentView, View.OnClickListener, FirebaseStorageUtil.CallBackListener {

    private MyPageService mMyPageService;
    private MyPageResponse.Data mData;
    private MainActivity mParentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyPageBinding.inflate(inflater);

        initVariable();
        initListener();

        mMyPageService.getMember();

        return binding.getRoot();
    }

    private void initVariable() {
        mParentActivity = (MainActivity) getActivity();
        mMyPageService = new MyPageService(this);
    }

    private void initListener() {
        binding.myIvSetting.setOnClickListener(this);
        binding.myTvEditCompleted.setOnClickListener(this);
        binding.myIvProfileEdit.setOnClickListener(this);
        FirebaseStorageUtil.setCallBackListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_iv_setting: // 프로필 변경
                binding.cardInfo.setVisibility(View.GONE);
                binding.cardInfoEdit.setVisibility(View.VISIBLE);
                break;
            case R.id.my_tv_edit_completed: // 프로필 변경 완료
                if(validation()) editMyInfo();
                break;
            case R.id.my_iv_profile_edit:
                getAlbum();
                break;

        }
    }

    private void editMyInfo() {
        showProgressDialog();
        if(mParentActivity.isImageChange()) {
            FirebaseStorageUtil.uploadProfileImage(mParentActivity.getImageUrl(), mData.getId());
        } else {
            String nickname = String.valueOf(binding.myTvNicknameEdit.getText());
            String orgPictureUrl = mData.getProfileUrl();

            MyPageBody myPageBody = new MyPageBody(nickname, orgPictureUrl);

            mMyPageService.editMember(myPageBody);
        }
    }

    public void getAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1001);
    }

    @Override
    public void getMemberSuccess(MyPageResponse.Data data) {
        binding.cardInfo.setVisibility(View.VISIBLE);
        binding.cardInfoEdit.setVisibility(View.GONE);
        if(getContext() != null && data.getProfileUrl() != null && !data.getProfileUrl().isEmpty())
            Glide.with(getContext())
                    .load(Uri.parse(data.getProfileUrl()))
                    .circleCrop()
                    .into(binding.myIvProfile);
        if(getContext() != null && data.getProfileUrl() != null && !data.getProfileUrl().isEmpty())
            Glide.with(getContext())
                    .load(Uri.parse(data.getProfileUrl()))
                    .circleCrop()
                    .into(binding.myIvProfileEdit);
        binding.myTvEmail.setText(data.getEmail());
        binding.myTvNickname.setText(data.getName());
        binding.myTvEmailEdit.setText(data.getEmail());
        binding.myTvNicknameEdit.setText(data.getName());

        mData = data;
    }

    @Override
    public void getMemberFailure(String message) {
        showCustomToast(message);
    }

    @Override
    public void patchMemberSuccess(MyPageResponse.Data data) {
        showCustomToast("변경되었습니다.");
        hideProgressDialog();

        if(getContext() != null && data.getProfileUrl() != null && !data.getProfileUrl().isEmpty())
        Glide.with(getContext())
                .load(Uri.parse(data.getProfileUrl()))
                .circleCrop()
                .into(binding.myIvProfile);
        if(getContext() != null && data.getProfileUrl() != null && !data.getProfileUrl().isEmpty())
            Glide.with(getContext())
                    .load(Uri.parse(data.getProfileUrl()))
                    .circleCrop()
                    .into(binding.myIvProfileEdit);

        binding.myTvNickname.setText(data.getName());
        binding.myTvNicknameEdit.setText(data.getName());

        binding.cardInfo.setVisibility(View.VISIBLE);
        binding.cardInfoEdit.setVisibility(View.GONE);
    }

    @Override
    public void patchMemberFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    private boolean validation() {
        String nickname = String.valueOf(binding.myTvNicknameEdit.getText());
        if(nickname.isEmpty()) {
            showCustomToast("닉네임을 입력해주세요.");
            return false;
        }
        return true;
    }

    public void setProfileImage(Uri uri) {
        Glide.with(getContext())
                .load(uri)
                .circleCrop()
                .into(binding.myIvProfileEdit);
    }

    @Override
    public void callback(String url) {
        String nickname = String.valueOf(binding.myTvNicknameEdit.getText());
        String pictureUrl = url;
        String orgPictureUrl = mData.getProfileUrl();

        MyPageBody myPageBody;
        if(mParentActivity.isImageChange()) {
            myPageBody = new MyPageBody(nickname, pictureUrl);
        } else {
            myPageBody = new MyPageBody(nickname, orgPictureUrl);
        }

        mMyPageService.editMember(myPageBody);
    }
}