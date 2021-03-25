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
import com.sixthank.gogo.src.detail.interfaces.BoardDetailFragmentView;
import com.sixthank.gogo.src.detail.models.BoardAnswerBody;
import com.sixthank.gogo.src.detail.models.BoardDetailResponse;
import com.sixthank.gogo.src.detail.service.BoardDetailService;

import java.util.List;

public class BoardDetailOXFragment extends BaseFragment<FragmentBoardOXBinding> implements BoardDetailFragmentView {

    private BoardDetailResponse.Data boardItem;
    private List<BoardDetailResponse.AnswerResultDtoList> answerList;
    private BoardDetailService mBoardDetailService;
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
            answerList = boardItem.getAnswerResultDtoList();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardOXBinding.inflate(inflater);

        initView();
        initVariable();
        initListener();

        return binding.getRoot();
    }

    private void initView() {

    }

    private void initVariable() {
        mBoardDetailService = new BoardDetailService(this);
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
                        mContentId = answerList.get(0).getContentId();
                        mBoardDetailService.postBoardAnswer(new BoardAnswerBody(mContentId), boardItem.getBoardId());
                        break;
                    case R.id.board_radio_x:
                        mContentId = answerList.get(1).getContentId();
                        mBoardDetailService.postBoardAnswer(new BoardAnswerBody(mContentId), boardItem.getBoardId());
                        break;
                }
            }
        });
    }

    @Override
    public void postBoardAnswerSuccess() {
        showCustomToast("success");
    }

    @Override
    public void postBoardAnswerFailure(String message) {
        showCustomToast(message);
        binding.boardOxRadioGroup.clearCheck();
//        binding.boardRadioO.setFocusable(false);
//        binding.boardRadioX.setFocusable(false);
    }
}