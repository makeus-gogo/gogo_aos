package com.sixthank.gogo.src.post.fragment;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.databinding.FragmentPostFirstBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.post.PostActivity;
import com.sixthank.gogo.src.post.fragment.adapter.RandomPagerAdapter;


public class PostFirstFragment extends BaseFragment<FragmentPostFirstBinding> {

    private PostActivity mParentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostFirstBinding.inflate(inflater, container, false);
        mParentActivity = (PostActivity) getActivity();

        binding.postFirstViewPager.setAdapter(new RandomPagerAdapter(getContext()));

        binding.postFirstNext.setOnClickListener(v->{
            int index = binding.postFirstViewPager.getCurrentItem();
            mParentActivity.setValueString("worryContent", binding.postFirstWorryContent.getText().toString());
            mParentActivity.switchFragment(index+1);
        });

        binding.postFirstImgLeft.setOnClickListener(v->{
            int index = binding.postFirstViewPager.getCurrentItem();
            if(index != 0) {
                binding.postFirstViewPager.setCurrentItem(index-1);
                setAnswerKind(index-1);
            }
        });

        binding.postFirstImgRight.setOnClickListener(v->{
            int index = binding.postFirstViewPager.getCurrentItem();
            if(index != 2) {
                binding.postFirstViewPager.setCurrentItem(index+1);
                setAnswerKind(index+1);
            }
        });

        binding.postFirstImgClose.setOnClickListener(v->{
            getActivity().finish();
        });
        return binding.getRoot();

    }

    private void setAnswerKind(int index) {
        switch (index) {
            case 0:
                binding.postFirstLlOx.setVisibility(View.VISIBLE);
                binding.postFirstLlSelect.setVisibility(View.INVISIBLE);
                binding.postFirstLlRandom.setVisibility(View.INVISIBLE);
                binding.postFirstAnswerTxt.setText("O/X 답변용으로 추천드려요");
                break;
            case 1:
                binding.postFirstLlOx.setVisibility(View.INVISIBLE);
                binding.postFirstLlSelect.setVisibility(View.VISIBLE);
                binding.postFirstLlRandom.setVisibility(View.INVISIBLE);
                binding.postFirstAnswerTxt.setText("콕 찝어진 대답을 듣소 싶다면 추천해용!");
                break;
            case 2:
                binding.postFirstLlOx.setVisibility(View.INVISIBLE);
                binding.postFirstLlSelect.setVisibility(View.INVISIBLE);
                binding.postFirstLlRandom.setVisibility(View.VISIBLE);
                binding.postFirstAnswerTxt.setText("제가 도와드릴께요!");
                break;
        }
    }
}