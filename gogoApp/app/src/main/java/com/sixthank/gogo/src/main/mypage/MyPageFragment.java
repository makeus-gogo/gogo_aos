package com.sixthank.gogo.src.main.mypage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.FragmentMyPageBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.main.mypage.interfaces.MyPageFragmentView;
import com.sixthank.gogo.src.main.mypage.service.MyPageService;

public class MyPageFragment extends BaseFragment<FragmentMyPageBinding> implements MyPageFragmentView {

    private MyPageService mMyPageService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyPageBinding.inflate(inflater);
        initVariable();

        mMyPageService.getMember();

        return binding.getRoot();
    }

    private void initVariable() {
        mMyPageService = new MyPageService(this);
    }

    @Override
    public void getMemberSuccess() {

//        showCustomToast("마이페이지 조회 성공");
    }

    @Override
    public void getMemberFailure() {
        showCustomToast("마이페이지 조회 실패");
    }
}