package com.sixthank.gogo.src.detail.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sixthank.gogo.databinding.FragmentBoardChoiceBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.common.OnItemClickListener;
import com.sixthank.gogo.src.detail.models.BoardDetailResponse;
import com.sixthank.gogo.src.main.home.models.HomeResponse;
import com.sixthank.gogo.src.post.fragment.adapter.AnswerListAdapter;

import java.util.ArrayList;

public class BoardDetailChoiceFragment extends BaseFragment<FragmentBoardChoiceBinding> implements OnItemClickListener {

    private BoardDetailResponse.Data boardItem;
    private AnswerListAdapter mAnswerListAdapter;

    public static BoardDetailChoiceFragment newInstance(BoardDetailResponse.Data data) {

        Bundle args = new Bundle();
        args.putSerializable("boardItem", data);
        BoardDetailChoiceFragment fragment = new BoardDetailChoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            boardItem = (BoardDetailResponse.Data) getArguments().getSerializable("boardItem");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardChoiceBinding.inflate(inflater);

        initVariable();
        initListener();
        initData();

        return binding.getRoot();
    }

    private void initVariable() {
        ArrayList<BoardDetailResponse.Content> contents
                = (ArrayList<BoardDetailResponse.Content>) boardItem.getContents();

        mAnswerListAdapter = new AnswerListAdapter(contents);
    }

    private void initListener() {
        mAnswerListAdapter.setOnItemClickListener(this);
        binding.boardChoiceIvClose.setOnClickListener(v->{ getActivity().finish(); });
    }

    private void initData() {
        binding.boardChoiceRv.setAdapter(mAnswerListAdapter);
        binding.boardChoiceTvContent.setText(boardItem.getDescription());
        Glide.with(getContext()).load(Uri.parse(boardItem.getPictureUrl())).into(binding.boardChoiceImg);
    }

    @Override
    public void onItemClick(View v, Object item) {


    }
}