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
import com.sixthank.gogo.src.main.home.models.WorryResponse;
import com.sixthank.gogo.src.post.PostActivity;

import java.util.ArrayList;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private MainActivity mParentActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);

        ArrayList<WorryResponse> wTopList = new ArrayList<>();
        ArrayList<WorryResponse> wList = new ArrayList<>();

        for(int i = 0; i < 5; i++)
            wTopList.add(new WorryResponse("귀농을 할까? 주말농장을 할까? 귀농을 할까? 주말농장을 할까? 귀농을 할까? 주말농장을 할까? 귀농을..."));

        for(int i = 0; i < 10; i++)
            wList.add(new WorryResponse("귀농을 할까? 주말농장을 할까? 귀농을 할까? 주말농장을 할까? 귀농을 할까? 주말농장을 할까? 귀농을..."));

        WorryRankAdapter wTopAdapter = new WorryRankAdapter(wTopList);
        binding.mainRvTop5.setAdapter(wTopAdapter);

        WorryListAdapter wAdapter = new WorryListAdapter(wList);
        binding.mainRvList.setAdapter(wAdapter);

        binding.textView.setOnClickListener(v->{
            startActivity(new Intent(getActivity(), PostActivity.class));
        });
        return binding.getRoot();
    }
}