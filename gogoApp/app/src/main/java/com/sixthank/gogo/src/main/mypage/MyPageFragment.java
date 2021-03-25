package com.sixthank.gogo.src.main.mypage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.FragmentMyPageBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.common.FirebaseStorageUtil;
import com.sixthank.gogo.src.main.mypage.interfaces.MyPageFragmentView;
import com.sixthank.gogo.src.main.mypage.models.MyPageBody;
import com.sixthank.gogo.src.main.mypage.models.MyPageResponse;
import com.sixthank.gogo.src.main.mypage.service.MyPageService;

public class MyPageFragment extends BaseFragment<FragmentMyPageBinding> implements MyPageFragmentView, View.OnClickListener {

    private MyPageService mMyPageService;
    private MyPageResponse.Data mData;
    private String currentPhotoPath;
    private Uri imgUri;
    private String imageFileName;
    private StorageReference mStorageRef;
    private FirebaseStorage mStorage;

    private String sendUrl;

    private final String mStorageUrl = "gs://gogoapp-29dcf.appspot.com";

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
        mMyPageService = new MyPageService(this);
    }

    private void initListener() {
        binding.myIvSetting.setOnClickListener(this);
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
        String nickname = String.valueOf(binding.myTvNicknameEdit.getText());
        String pictureUrl = FirebaseStorageUtil.getUrl();
        String orgPictureUrl = mData.getProfileUrl();
        MyPageBody myPageBody;
        if(orgPictureUrl != null)
            myPageBody = new MyPageBody(nickname, orgPictureUrl);
        else
            myPageBody = new MyPageBody(nickname);

        mMyPageService.editMember(myPageBody);
    }

    public void getAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1001);
    }

    @Override
    public void getMemberSuccess(MyPageResponse.Data data) {
        if(getContext() != null && data.getProfileUrl() != null && !data.getProfileUrl().isEmpty())
            Glide.with(getContext()).load(Uri.parse(data.getProfileUrl())).into(binding.myIvProfile);
        binding.myTvEmail.setText(data.getEmail());
        binding.myTvNickname.setText(data.getName());
        binding.myTvNicknameEdit.setText(data.getName());

        mData = data;
    }

    @Override
    public void getMemberFailure() {
        showCustomToast("마이페이지 조회 실패");
    }

    @Override
    public void patchMemberSuccess(MyPageResponse.Data data) {
        showCustomToast("변경되었습니다.");
        binding.cardInfo.setVisibility(View.VISIBLE);
        binding.cardInfo.setVisibility(View.GONE);
        binding.myTvNickname.setText(data.getName());
        binding.myTvNicknameEdit.setText(data.getName());
    }

    @Override
    public void patchMemberFailure() {
        showCustomToast("변경실패.");
    }

    private boolean validation() {
        String nickname = String.valueOf(binding.myTvNicknameEdit.getText());
        if(nickname.isEmpty()) {
            showCustomToast("닉네임을 입력해주세요.");
            return false;
        }
        return true;
    }

}