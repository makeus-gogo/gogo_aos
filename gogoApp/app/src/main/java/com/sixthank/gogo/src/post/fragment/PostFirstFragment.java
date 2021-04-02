package com.sixthank.gogo.src.post.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.FragmentPostFirstBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.post.PostActivity;
import com.sixthank.gogo.src.post.fragment.adapter.RandomPagerAdapter;


public class PostFirstFragment extends BaseFragment<FragmentPostFirstBinding> implements View.OnClickListener {

    private PostActivity mParentActivity;
    private String mDescription;

    public static PostFirstFragment newInstance(String worryContent) {

        Bundle args = new Bundle();
        args.putString("description", worryContent);
        PostFirstFragment fragment = new PostFirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostFirstBinding.inflate(inflater, container, false);

        initVariable();
        initView();
        initListener();

        return binding.getRoot();

    }

    private void initVariable() {
        mParentActivity = (PostActivity) getActivity();
        mDescription = getArguments().getString("description");
    }

    private void initView() {
        binding.postFirstViewPager.setAdapter(new RandomPagerAdapter(getContext()));
        binding.postFirstDescription.setText(mDescription);
    }

    private void initListener() {
        binding.postFirstNext.setOnClickListener(this);
        binding.postFirstImgLeft.setOnClickListener(this);
        binding.postFirstImgRight.setOnClickListener(this);
        binding.postFirstImgClose.setOnClickListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int index = binding.postFirstViewPager.getCurrentItem();
        switch (v.getId()) {
            case R.id.post_first_next:
                String description = String.valueOf(binding.postFirstDescription.getText());
                if(description.isEmpty()) {
                    showCustomToast("고민을 입력해주세요");
                    return;
                }
                mParentActivity.setValueString("description", description);
                binding.postFirstDescription.setText("");
                mParentActivity.switchFragment(index + 1);
                break;
            case R.id.post_first_img_left:
                if (index != 0) {
                    binding.postFirstViewPager.setCurrentItem(index - 1);
                    setAnswerKind(index - 1);
                }
                break;
            case R.id.post_first_img_right:
                if (index != 2) {
                    binding.postFirstViewPager.setCurrentItem(index + 1);
                    setAnswerKind(index + 1);
                }
                break;
            case R.id.post_first_img_close:
                getActivity().finish();
                break;

        }
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