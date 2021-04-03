package com.sixthank.gogo.src.detail.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.FragmentBoardChoiceBinding;
import com.sixthank.gogo.src.comment.BoardCommentActivity;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.common.CustomDialog;
import com.sixthank.gogo.src.common.CustomDialogCallback;
import com.sixthank.gogo.src.common.OnItemClickListener;
import com.sixthank.gogo.src.common.models.AnswerResultDtoList;
import com.sixthank.gogo.src.detail.interfaces.BoardDetailFragmentView;
import com.sixthank.gogo.src.detail.models.BoardAnswerBody;
import com.sixthank.gogo.src.detail.models.BoardAnswerResponse;
import com.sixthank.gogo.src.detail.models.BoardDetailResponse;

import com.sixthank.gogo.src.detail.service.BoardDetailService;
import com.sixthank.gogo.src.post.fragment.adapter.AnswerListAdapter;

import java.util.ArrayList;
import java.util.List;

public class BoardDetailChoiceFragment extends BaseFragment<FragmentBoardChoiceBinding>
        implements BoardDetailFragmentView, OnItemClickListener, CustomDialogCallback {

    private BoardDetailResponse.Data boardItem;
    private List<AnswerResultDtoList> answerList;

    private AnswerListAdapter mAnswerListAdapter;
    private BoardDetailService mBoardDetailService;

    private int mContentId;
    private String mNickname, mProfileUrl;

    public static BoardDetailChoiceFragment newInstance(BoardDetailResponse.Data data, String nickname, String profileUrl) {

        Bundle args = new Bundle();
        args.putSerializable("boardItem", data);
        args.putString("nickname", nickname);
        args.putString("profileUrl", profileUrl);
        BoardDetailChoiceFragment fragment = new BoardDetailChoiceFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            boardItem = (BoardDetailResponse.Data) getArguments().getSerializable("boardItem");
            answerList = boardItem.getAnswerResultDtoList();
            mNickname = getArguments().getString("nickname");
            mProfileUrl = getArguments().getString("profileUrl");
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
        mBoardDetailService = new BoardDetailService(this);

        mAnswerListAdapter = new AnswerListAdapter((ArrayList<AnswerResultDtoList>) answerList);
        mAnswerListAdapter.setContext(getContext());
    }

    private void initListener() {
        mAnswerListAdapter.setOnItemClickListener(this);
        binding.boardChoiceIvClose.setOnClickListener(v->{ getActivity().finish(); });
        binding.boardChoiceComment.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), BoardCommentActivity.class);
            intent.putExtra("pictureUrl", boardItem.getPictureUrl());
            intent.putExtra("boardId", boardItem.getBoardId());
            intent.putExtra("profileUrl", mProfileUrl);
            intent.putExtra("nickname", mNickname);
            startActivity(intent);
        });
        binding.boardChoiceIvAlert.setOnClickListener(v->{
            CustomDialog customDialog = new CustomDialog(getContext());
            customDialog.setPopupCallback(this);
            customDialog.showPopupDialog("해당 글을 신고하시겠습니까?", "");
        });
    }

    private void initData() {
        binding.boardChoiceTvNickname.setText(mNickname);
        binding.boardChoiceTvContent.setText(boardItem.getDescription());

        if(getContext() != null && !boardItem.getPictureUrl().isEmpty())
            Glide.with(getContext())
                    .load(Uri.parse(boardItem.getPictureUrl()))
                    .into(binding.boardChoiceImg);

        if(getContext() != null && mProfileUrl != null && !mProfileUrl.isEmpty())
            Glide.with(getContext())
                    .load(Uri.parse(mProfileUrl))
                    .circleCrop()
                    .into(binding.boardChoiceProfile);
        else
            Glide.with(getContext())
                    .load(ContextCompat.getDrawable(getContext(), R.drawable.ic_profile))
                    .circleCrop()
                    .into(binding.boardChoiceProfile);

        for(AnswerResultDtoList dto: answerList) {
            if(dto.getCheck() != 0) {
                mContentId = dto.getContentId();
            }
        }

        if(mContentId != 0) mAnswerListAdapter.setSelected(mContentId);
        mAnswerListAdapter.setUserCheck(boardItem.getUserCheck());
        binding.boardChoiceRv.setAdapter(mAnswerListAdapter);
    }

    @Override
    public void onItemClick(View v, Object item) {
        AnswerResultDtoList answer = (AnswerResultDtoList) item;
        mContentId = answer.getContentId();
        mBoardDetailService.postBoardAnswer(new BoardAnswerBody(mContentId), boardItem.getBoardId());
    }

    @Override
    public void postBoardAnswerSuccess(BoardAnswerResponse.Data data) {
        mAnswerListAdapter.setSelected(mContentId);
        mAnswerListAdapter.setAnswerList(data.getAnswerResultDtoList());
        mAnswerListAdapter.notifyDataSetChanged();
    }

    @Override
    public void postBoardAnswerFailure(String message) {
        showCustomToast(message);
    }

    @Override
    public void btnPositive(String type) {
        showCustomToast("신고되었습니다.");
    }

    @Override
    public void btnNegative() {

    }
}