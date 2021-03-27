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
        implements BoardDetailFragmentView, OnItemClickListener {

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

        for(AnswerResultDtoList dto: answerList) {
            if(dto.getCheck() != 0) {
                mContentId = dto.getContentId();
            }
        }

        if(mContentId != 0) mAnswerListAdapter.setSelected(mContentId);
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
}