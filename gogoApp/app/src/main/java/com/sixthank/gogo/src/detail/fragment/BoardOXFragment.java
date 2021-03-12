package com.sixthank.gogo.src.detail.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.FragmentBoardOXBinding;
import com.sixthank.gogo.src.common.BaseFragment;

public class BoardOXFragment extends BaseFragment<FragmentBoardOXBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardOXBinding.inflate(inflater);


        return binding.getRoot();
    }
}