package com.sixthank.gogo.src.main.home;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;


import androidx.core.widget.NestedScrollView;

import com.sixthank.gogo.databinding.FragmentHomeBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.main.home.adapter.WorryListAdapter;
import com.sixthank.gogo.src.main.home.adapter.WorryRankAdapter;
import com.sixthank.gogo.src.main.home.interfaces.HomeFragmentView;
import com.sixthank.gogo.src.main.home.models.BoardTopNResponse;
import com.sixthank.gogo.src.main.home.models.HomeResponse;
import com.sixthank.gogo.src.main.home.service.HomeService;
import com.sixthank.gogo.src.post.PostActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements HomeFragmentView {

    private HomeService mHomeService;
    private WorryListAdapter mWorryListAdapter;
    private int mNotifyStartRange = 0;


    public static HomeFragment newInstance(String nickname) {

        Bundle args = new Bundle();
        args.putString("nickname", nickname);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);

        showProgressDialog();

        initVariable();
        initView();
        initListener();
        initData();

        return binding.getRoot();
    }

    private void initVariable() {
        mHomeService = new HomeService(this);
        mWorryListAdapter = new WorryListAdapter(getContext());
        binding.mainRvList.setAdapter(mWorryListAdapter);
    }

    private void initView() {

    }

    private void initListener() {

        binding.homeIvBtn.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), PostActivity.class);
            intent.putExtra("description", String.valueOf(binding.homeEtWorryContent.getText()));
            startActivity(intent);

            binding.homeEtWorryContent.setText("");
        });

        binding.scrollableContent.getViewTreeObserver()
                .addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        if (binding.scrollableContent.getChildAt(0).getBottom()
                                <= (binding.scrollableContent.getHeight() + binding.scrollableContent.getScrollY())) {
                            Log.d("HomeFragment", "bottom");

                            // 스크롤뷰가 전체화면이지 않을 때 문제 발생. size <= 2
                            mNotifyStartRange = mWorryListAdapter.getItemCount();
                            mHomeService.getListBoardList(mWorryListAdapter.getLastId(), 10);

                        } else {
                            //scroll view is not at bottom
                            Log.d("HomeFragment", "no bottom");

                        }
                    }
                });
    }

    private void initData() {
        showProgressDialog();
        mHomeService.getTopNListBoardList();
    }

    @Override
    public void getTopNBoardListSuccess(List<BoardTopNResponse.Data> data) {
        hideProgressDialog();
        if (data.size() == 0) {
            binding.mainTvTop3.setVisibility(View.VISIBLE);
            binding.mainTvTop3.setText("조회 목록이 없습니다.");
        }
        binding.mainRvTop3.setAdapter(new WorryRankAdapter(getContext(), data));
    }

    @Override
    public void getTopNBoardListFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    @Override
    public void getBoardListSuccess(List<HomeResponse.Data> data) {
        hideProgressDialog();
        if(data.size() != 0) {
            mWorryListAdapter.addItems((ArrayList<HomeResponse.Data>) data);
            binding.mainRvList.post(new Runnable() {
                @Override
                public void run() {
                    mWorryListAdapter.notifyItemRangeInserted(mNotifyStartRange, mWorryListAdapter.getItemCount());
                }
            });
        }
        if (mWorryListAdapter.getItemCount() == 0) {
            binding.mainTvList.setVisibility(View.VISIBLE);
            binding.mainTvList.setText("조회 목록이 없습니다.");
        }
    }

    @Override
    public void getBoardListFailure(String message) {
        showCustomToast(message);
    }

    public void getBoardList() {
        mHomeService.getListBoardList(mWorryListAdapter.getLastId(), 10);
    }
}