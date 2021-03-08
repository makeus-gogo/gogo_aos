package com.sixthank.gogo.src.post.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.databinding.FragmentPostThirdBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.main.home.models.WorryResponse;
import com.sixthank.gogo.src.post.fragment.adapter.WorryListAdapter2;

import java.util.ArrayList;

public class PostThirdFragment extends BaseFragment<FragmentPostThirdBinding> {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PostThirdFragment() {
        // Required empty public constructor
    }


    public static PostThirdFragment newInstance(String param1, String param2) {
        PostThirdFragment fragment = new PostThirdFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostThirdBinding.inflate(getLayoutInflater());

        ArrayList<WorryResponse> wTopList = new ArrayList<>();

        for(int i = 0; i < 5; i++)
            wTopList.add(new WorryResponse("고민있어요 고민있어요 고민있어요"));

        WorryListAdapter2 wTopAdapter = new WorryListAdapter2(wTopList);
        binding.postThirdRvList.setAdapter(wTopAdapter);

        binding.postThirdImgClose.setOnClickListener(v->{
            getActivity().finish();
        });
        return binding.getRoot();
    }
}