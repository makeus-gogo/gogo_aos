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

    private BoardDetailService mBoardDetailService;

    private BoardDetailResponse.Data boardItem;
    private List<BoardDetailResponse.AnswerResultDtoList> answerList;

    private int mContentId;
    private int selectType;

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
        // 내 게시물일 때 -> 답변 확률 바로 보여줌
        if (boardItem.getUserCheck() == 1) {

            setAfterClick();

            binding.boardOxTvOPercent.setBackgroundResource(R.drawable.ic_percent_blue);
            binding.boardOxTvXPercent.setBackgroundResource(R.drawable.ic_percent_blue);

            return;
        }

        // 내 게시물이 아닐 때
        int checkAnswerIdx;
        if (answerList.get(0).getCheck() == 1) checkAnswerIdx = 0;
        else if (answerList.get(1).getCheck() == 1) checkAnswerIdx = 1;
        else checkAnswerIdx = -1;

        if (checkAnswerIdx == -1) {
            showCustomToast("이미 투표하셨습니다.");
            return;
        }

        setOXView(checkAnswerIdx);

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
                        selectType = 0;
                        break;
                    case R.id.board_radio_x:
                        mContentId = answerList.get(1).getContentId();
                        mBoardDetailService.postBoardAnswer(new BoardAnswerBody(mContentId), boardItem.getBoardId());
                        selectType = 1;
                        break;
                }
            }
        });
    }

    private void setAfterClick() {
        binding.boardOxTvOPercent.setVisibility(View.VISIBLE);
        binding.boardOxTvXPercent.setVisibility(View.VISIBLE);
        binding.boardRadioO.setClickable(false);
        binding.boardRadioX.setClickable(false);

        binding.boardOxTvOPercent.setText(answerList.get(0).getPercentage() + "%");
        binding.boardOxTvXPercent.setText(answerList.get(1).getPercentage() + "%");
    }

    private void setOXView(int type) {
        setAfterClick();

        if (type == 0) {
            binding.boardOxTvOPercent.setBackgroundResource(R.drawable.ic_percent_blue);
            binding.boardRadioO.setChecked(true);
        } else {
            binding.boardOxTvXPercent.setBackgroundResource(R.drawable.ic_percent_blue);
            binding.boardRadioX.setChecked(true);
        }

    }

    @Override
    public void postBoardAnswerSuccess() {
        showCustomToast("success");
        setOXView(selectType);
    }

    @Override
    public void postBoardAnswerFailure(String message) {
        showCustomToast("자신의 게시물에 답변할 수 없습니다.");
        binding.boardOxRadioGroup.clearCheck();
    }
}