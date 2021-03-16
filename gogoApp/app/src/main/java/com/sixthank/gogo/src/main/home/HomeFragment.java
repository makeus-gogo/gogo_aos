package com.sixthank.gogo.src.main.home;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.sixthank.gogo.databinding.FragmentHomeBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.main.MainActivity;
import com.sixthank.gogo.src.main.home.adapter.WorryListAdapter;
import com.sixthank.gogo.src.main.home.adapter.WorryRankAdapter;
import com.sixthank.gogo.src.main.home.interfaces.HomeFragmentView;
import com.sixthank.gogo.src.main.home.models.HomeResponse;
import com.sixthank.gogo.src.main.home.models.WorryResponse;
import com.sixthank.gogo.src.main.home.service.HomeService;
import com.sixthank.gogo.src.post.PostActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements HomeFragmentView {

    private MainActivity mParentActivity;
    private HomeService mHomeService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentActivity = (MainActivity) getActivity();

        initVariable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);

        ArrayList<WorryResponse> wList = new ArrayList<>();


        for(int i = 0; i < 10; i++)
            wList.add(new WorryResponse("귀농을 할까? 주말농장을 할까? 귀농을 할까? 주말농장을 할까? 귀농을 할까? 주말농장을 할까? 귀농을..."));


        WorryListAdapter wAdapter = new WorryListAdapter(wList);
        binding.mainRvList.setAdapter(wAdapter);

        binding.textView.setOnClickListener(v->{
            startActivity(new Intent(getActivity(), PostActivity.class));
        });
        return binding.getRoot();
    }

    private void initVariable() {
        mHomeService = new HomeService(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mHomeService.getTopNListBoardList();
    }

    @Override
    public void getTopNBoardListSuccess(List<HomeResponse.Data> data) {
        if(data.size() == 0) {
            binding.mainTvTop3.setVisibility(View.VISIBLE);
            binding.mainTvTop3.setText("조회 목록이 없습니다.");
        }
        WorryRankAdapter worryRankAdapter = new WorryRankAdapter(data);
        binding.mainRvTop3.setAdapter(worryRankAdapter);
    }

    @Override
    public void getTopNBoardListFailure() {
        showCustomToast("TopN Failure");
    }

    @Override
    public void getBoardListSuccess(List<HomeResponse.Data> data) {

    }

    @Override
    public void getBoardListFailure() {

    }
}