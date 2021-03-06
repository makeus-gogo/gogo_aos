package com.sixthank.gogo.src.comment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.sixthank.gogo.databinding.ActivityBoardCommentBinding;
import com.sixthank.gogo.src.comment.interfaces.CommentActivityView;
import com.sixthank.gogo.src.comment.models.CommentBody;
import com.sixthank.gogo.src.comment.models.Data;
import com.sixthank.gogo.src.comment.service.CommentService;
import com.sixthank.gogo.src.common.BaseActivity;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;


public class BoardCommentActivity extends BaseActivity<ActivityBoardCommentBinding> implements CommentActivityView {

    private int mBoardId;
    private String mNickname, mProfileUrl, mPictureUrl;

    private CommentService mCommentService;
    private CommentListAdapter mCommentListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initVariable();
        initView();
        initListener();
        initData();
    }

    private void initVariable() {
        if(getIntent() != null) {
            mBoardId = getIntent().getIntExtra("boardId", 0);
            mNickname = getIntent().getStringExtra("nickname");
            mProfileUrl = getIntent().getStringExtra("profileUrl");
            mPictureUrl = getIntent().getStringExtra("pictureUrl");
        }

        mCommentService = new CommentService(this);
        mCommentListAdapter = new CommentListAdapter(getApplicationContext());

    }

    private void initView(){
        // 사진 처리
        if(mPictureUrl != null && !mPictureUrl.isEmpty())
            Glide.with(getApplicationContext())
                    .load(Uri.parse(mPictureUrl))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(new ColorDrawable(Color.TRANSPARENT))
                    .transform(new BlurTransformation(25, 3))
                    .into(binding.commentImage);
    }

    private void initListener() {
        binding.commentViewSearch.setOnClickListener(v->{
            String content = String.valueOf(binding.commentEtContent.getText());
            if(content.isEmpty()) {
                showCustomToast("내용을 입력해주세요.");
            } else {
                hideKeyboard(binding.commentEtContent);
                mCommentService.addComment(new CommentBody(content), mBoardId);
            }
        });

        binding.commentIvClose.setOnClickListener(v->{ finish(); });
    }

    private void initData() {
        mCommentService.getComments(mBoardId);
    }

    @Override
    public void getCommentsSuccess(List<Data> list) {
        mCommentListAdapter.setComments(list);
        binding.commentRvList.setAdapter(mCommentListAdapter);


    }

    @Override
    public void getCommentsFailure(String message) {
        showCustomToast(message);
    }

    @Override
    public void postCommentSuccess(Data data) {
        data.setMemberProfileUrl(data.getMemberProfileUrl());
        mCommentListAdapter.addItem(data);
        binding.commentEtContent.setText("");
        showCustomToast("댓글이 등록되었습니다.");

        binding.commentRvList.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.commentRvList.scrollToPosition(mCommentListAdapter.getItemCount()-1);
            }
        }, 500);
    }

    @Override
    public void postCommentFailure(String message) {
        showCustomToast(message);
    }
}