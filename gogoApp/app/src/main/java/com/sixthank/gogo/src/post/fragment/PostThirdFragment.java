package com.sixthank.gogo.src.post.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.databinding.FragmentPostThirdBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.main.MainActivity;
import com.sixthank.gogo.src.main.home.models.WorryResponse;
import com.sixthank.gogo.src.post.fragment.adapter.WorryListAdapter2;

import java.util.ArrayList;

public class PostThirdFragment extends BaseFragment<FragmentPostThirdBinding> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostThirdBinding.inflate(getLayoutInflater());

//        ArrayList<WorryResponse> wTopList = new ArrayList<>();
//
//        for(int i = 0; i < 5; i++)
//            wTopList.add(new WorryResponse("고민있어요 고민있어요 고민있어요"));

//        WorryListAdapter2 wTopAdapter = new WorryListAdapter2(wTopList);
//        binding.postThirdRvList.setAdapter(wTopAdapter);

        binding.postThirdImgClose.setOnClickListener(v->{
            getActivity().finish();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
        return binding.getRoot();
    }
}