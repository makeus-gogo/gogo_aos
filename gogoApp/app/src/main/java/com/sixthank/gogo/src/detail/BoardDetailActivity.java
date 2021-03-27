
package com.sixthank.gogo.src.detail;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.ActivityBoardDetailBinding;
import com.sixthank.gogo.src.common.BaseActivity;
import com.sixthank.gogo.src.detail.fragment.BoardDetailChoiceFragment;
import com.sixthank.gogo.src.detail.fragment.BoardDetailOXFragment;
import com.sixthank.gogo.src.detail.interfaces.BoardDetailActivityView;
import com.sixthank.gogo.src.detail.models.BoardDetailResponse;
import com.sixthank.gogo.src.detail.service.BoardDetailService;

public class BoardDetailActivity extends BaseActivity<ActivityBoardDetailBinding> implements BoardDetailActivityView {

    private int mBoardId;
    private String mNickname, mProfileUrl;
    private BoardDetailService mBoardDetailService;
    private final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initVariable();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBoardDetailService.getBoardDetail(mBoardId);
    }

    private void initVariable() {
        mBoardDetailService = new BoardDetailService(this);
        mBoardId = getIntent().getIntExtra("boardId", 0);
        mNickname = getIntent().getStringExtra("nickname");
        mProfileUrl = getIntent().getStringExtra("profileUrl");
    }

    @Override
    public void getBoardDetailSuccess(BoardDetailResponse.Data data) {
        if(data.getType().equals("OX")) {
            fm.beginTransaction().replace(R.id.board_detail_container,
                    BoardDetailOXFragment.newInstance(data, mNickname, mProfileUrl)).commit();
        } else {
            fm.beginTransaction().replace(R.id.board_detail_container,
                    BoardDetailChoiceFragment.newInstance(data, mNickname, mProfileUrl)).commit();
        }
    }

    @Override
    public void getBoardDetailFailure() {
        showCustomToast("detail failure");
    }
}