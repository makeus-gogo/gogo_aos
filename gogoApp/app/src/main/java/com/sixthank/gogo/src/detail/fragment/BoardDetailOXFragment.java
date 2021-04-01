package com.sixthank.gogo.src.detail.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.sixthank.gogo.R;

import com.sixthank.gogo.databinding.FragmentBoardOXBinding;
import com.sixthank.gogo.src.comment.BoardCommentActivity;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.common.CustomDialog;
import com.sixthank.gogo.src.common.CustomDialogCallback;
import com.sixthank.gogo.src.common.models.AnswerResultDtoList;
import com.sixthank.gogo.src.detail.interfaces.BoardDetailFragmentView;
import com.sixthank.gogo.src.detail.models.BoardAnswerBody;
import com.sixthank.gogo.src.detail.models.BoardAnswerResponse;
import com.sixthank.gogo.src.detail.models.BoardDetailResponse;
import com.sixthank.gogo.src.detail.service.BoardDetailService;

import java.util.List;

public class BoardDetailOXFragment extends BaseFragment<FragmentBoardOXBinding> implements BoardDetailFragmentView, CustomDialogCallback {

    private BoardDetailService mBoardDetailService;

    private BoardDetailResponse.Data boardItem;
    private List<AnswerResultDtoList> answerList;

    private int mContentId, mSelectType;
    private String mNickname, mProfileUrl;

    private CustomDialog mPopupDialog;

    public static BoardDetailOXFragment newInstance(BoardDetailResponse.Data data, String nickname, String profileUrl) {

        Bundle args = new Bundle();
        args.putSerializable("boardItem", data);
        args.putString("nickname", nickname);
        args.putString("profileUrl", profileUrl);
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
            mNickname = String.valueOf(getArguments().get("nickname"));
            mProfileUrl = String.valueOf(getArguments().get("profileUrl"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardOXBinding.inflate(inflater);

        initVariable();
        initView();
        initListener();

        return binding.getRoot();
    }

    private void initView() {
        // 프로필 및 닉네임
        if(boardItem.getPictureUrl() != null && !mProfileUrl.isEmpty())
            Glide.with(getContext())
                    .load(Uri.parse(mProfileUrl))
                    .circleCrop()
                    .into(binding.boardOxProfile);

        binding.boardOxTvNickname.setText(mNickname);

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

        setOXView(checkAnswerIdx);
    }

    private void initVariable() {
        mPopupDialog = new CustomDialog(getContext());
        mBoardDetailService = new BoardDetailService(this);
        binding.boardOxDescription.setText(boardItem.getDescription());
        if (getContext() != null && !boardItem.getPictureUrl().isEmpty())
            Glide.with(getContext()).load(Uri.parse(boardItem.getPictureUrl())).into(binding.boardOxImg);
    }

    private void initListener() {
        mPopupDialog.setPopupCallback(this);

        binding.boardOxIvClose.setOnClickListener(v -> {
            getActivity().finish();
        });

        binding.boardRadioO.setOnClickListener(v->{
            mContentId = answerList.get(0).getContentId();
            mBoardDetailService.postBoardAnswer(new BoardAnswerBody(mContentId), boardItem.getBoardId());
            mSelectType = 0;
        });

        binding.boardRadioX.setOnClickListener(v->{
            mContentId = answerList.get(1).getContentId();
            mBoardDetailService.postBoardAnswer(new BoardAnswerBody(mContentId), boardItem.getBoardId());
            mSelectType = 1;
        });

        binding.boardOxComment.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), BoardCommentActivity.class);
            intent.putExtra("pictureUrl", boardItem.getPictureUrl());
            intent.putExtra("boardId", boardItem.getBoardId());
            intent.putExtra("profileUrl", mProfileUrl);
            intent.putExtra("nickname", mNickname);
            startActivity(intent);
        });


        binding.boardOxIvAlert.setOnClickListener(v->{
            mPopupDialog.showPopupDialog("신고하시겠습니까?", "report");
        });
    }

    private void setAfterClick() {
        binding.boardOxTvOPercent.setVisibility(View.VISIBLE);
        binding.boardOxTvXPercent.setVisibility(View.VISIBLE);

        binding.boardOxTvOPercent.setText(answerList.get(0).getPercentage() + "%");
        binding.boardOxTvXPercent.setText(answerList.get(1).getPercentage() + "%");
    }

    private void setOXView(int type) {
        setAfterClick();

        if (type == 0) {
            binding.boardOxTvOPercent.setBackgroundResource(R.drawable.ic_percent_blue);
            binding.boardRadioO.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_bg_white_o_blue));
        } else {
            binding.boardOxTvXPercent.setBackgroundResource(R.drawable.ic_percent_blue);
            binding.boardRadioO.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_bg_white_x_blue));
        }

    }

    @Override
    public void postBoardAnswerSuccess(BoardAnswerResponse.Data data) {
        setOXView(mSelectType);
    }

    @Override
    public void postBoardAnswerFailure(String message) {
        showCustomToast(message);
    }

    @Override
    public void btnPositive(String type) {
        showCustomToast("해당 게시글이 신고되었습니다.");
    }

    @Override
    public void btnNegative() { }
}