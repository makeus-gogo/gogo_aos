package com.sixthank.gogo.src.post.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.sixthank.gogo.R;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.databinding.FragmentPostSelectBinding;
import com.sixthank.gogo.src.post.PostActivity;
import com.sixthank.gogo.src.post.fragment.adapter.AnswerListAdapter;
import com.sixthank.gogo.src.post.fragment.adapter.ChoiceListAdapter;

import java.util.ArrayList;

public class PostSelectFragment extends BaseFragment<FragmentPostSelectBinding> implements View.OnClickListener {

    private PostActivity mParentActivity;
    private ChoiceListAdapter mChoiceListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostSelectBinding.inflate(inflater);

        initVariable();
        initListener();

        return binding.getRoot();
    }

    private void initVariable() {
        mParentActivity = (PostActivity) getActivity();
        mChoiceListAdapter = new ChoiceListAdapter(getContext());
        binding.postSelectRvChoice.setAdapter(mChoiceListAdapter);
    }

    private void initListener() {
        binding.postSelectClose.setOnClickListener(this);
        binding.postSelectNext.setOnClickListener(this);
        binding.postSelectPlus.setOnClickListener(this);
        binding.postSelectStyle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.post_select_camera:
                        binding.postSelectLlColor.setVisibility(View.INVISIBLE);
                        binding.postSelectRvTxt.setVisibility(View.INVISIBLE);
                        mParentActivity.getAlbum();
                        break;
                    case R.id.post_select_write:
                        binding.postSelectLlColor.setVisibility(View.VISIBLE);
                        binding.postSelectRvTxt.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.post_select_typo:
                        binding.postSelectLlColor.setVisibility(View.INVISIBLE);
                        binding.postSelectRvTxt.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.post_select_close:
                mParentActivity.finish();
                break;
            case R.id.post_select_next:
                mParentActivity.setValueString("type", "MULTI_CHOICE");
                mParentActivity.setValueString("description", binding.postSelectEtContent.getText().toString());
                mParentActivity.setValueObject("contents", mChoiceListAdapter.getItems());
                mParentActivity.addBoard();
                break;
            case R.id.post_select_plus:
                mChoiceListAdapter.addItem();
                break;
        }
    }

    public FragmentPostSelectBinding getBinding() {
        return binding;
    }
}