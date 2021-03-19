package com.sixthank.gogo.src.detail.fragment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.sixthank.gogo.R;

import com.sixthank.gogo.databinding.FragmentBoardOXBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.detail.models.BoardDetailResponse;

public class BoardDetailOXFragment extends BaseFragment<FragmentBoardOXBinding> {

    private BoardDetailResponse.Data boardItem;
    private int mContentId;

    public static BoardDetailOXFragment newInstance(BoardDetailResponse.Data data) {

        Bundle args = new Bundle();
        args.putSerializable("boardItem", data);
        BoardDetailOXFragment fragment = new BoardDetailOXFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            boardItem = (BoardDetailResponse.Data) getArguments().getSerializable("boardItem");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardOXBinding.inflate(inflater);

        initVariable();
        initListener();

        return binding.getRoot();
    }

    private void initVariable() {
        binding.boardOxDescription.setText(boardItem.getDescription());
        if (getContext() != null && !boardItem.getPictureUrl().isEmpty())
            Glide.with(getContext()).load(Uri.parse(boardItem.getPictureUrl())).into(binding.boardOxImg);
    }

    private void initListener() {
        binding.boardOxIvClose.setOnClickListener(v -> {
            getActivity().finish();
        });
        binding.boardOxRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.board_radio_o:
                        mContentId = boardItem.getContents().get(0).getId();
                        break;
                    case R.id.board_radio_x:
                        mContentId = boardItem.getContents().get(1).getId();
                        break;
                }
            }
        });
    }
}