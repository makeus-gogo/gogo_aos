package com.sixthank.gogo.src.main.home;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sixthank.gogo.databinding.FragmentHomeBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.common.OnItemClickListener;
import com.sixthank.gogo.src.common.OnLoadMoreScrollListener;
import com.sixthank.gogo.src.main.MainActivity;
import com.sixthank.gogo.src.main.home.adapter.WorryListAdapter;
import com.sixthank.gogo.src.main.home.adapter.WorryRankAdapter;
import com.sixthank.gogo.src.main.home.interfaces.HomeFragmentView;
import com.sixthank.gogo.src.main.home.models.BoardTopNResponse;
import com.sixthank.gogo.src.main.home.models.HomeResponse;
import com.sixthank.gogo.src.main.home.service.HomeService;
import com.sixthank.gogo.src.post.PostActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements HomeFragmentView{

    private MainActivity mParentActivity;
    private HomeService mHomeService;
    private WorryListAdapter mWorryListAdapter;
    private OnItemClickListener mOnItemClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);

        initVariable();
        initListener();

        return binding.getRoot();
    }

    private void initVariable() {
        mParentActivity = (MainActivity) getActivity();
        mHomeService = new HomeService(this);
    }

    private void initListener() {
        binding.textView.setOnClickListener(v->{
            startActivity(new Intent(getActivity(), PostActivity.class));
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.mainRvList.setLayoutManager(linearLayoutManager);
        OnLoadMoreScrollListener onLoadMoreScrollListener = new OnLoadMoreScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                mWorryListAdapter.addItems();
                binding.mainRvList.post(new Runnable() {
                    @Override
                    public void run() {
                        mWorryListAdapter.notifyDataSetChanged();
                    }
                });
            }
        };
    }

    private ArrayList<HomeResponse.Data> getData() {
        ArrayList<HomeResponse.Data> wList = new ArrayList<>();
//        for(int i = 0; i < 10; i++)
//            wList.add(new WorryResponse("귀농을 할까? 주말농장을 할까? 귀농을 할까? 주말농장을 할까? 귀농을 할까? 주말농장을 할까? 귀농을..."));

        return wList;
    }

    @Override
    public void onStart() {
        super.onStart();
        mHomeService.getTopNListBoardList();
    }

    @Override
    public void getTopNBoardListSuccess(List<BoardTopNResponse.Data> data) {
        if(data.size() == 0) {
            binding.mainTvTop3.setVisibility(View.VISIBLE);
            binding.mainTvTop3.setText("조회 목록이 없습니다.");
        }
        WorryRankAdapter worryRankAdapter = new WorryRankAdapter(getContext(), data);
        binding.mainRvTop3.setAdapter(worryRankAdapter);
    }

    @Override
    public void getTopNBoardListFailure() {
        showCustomToast("TopN Failure");
    }

    @Override
    public void getBoardListSuccess(List<HomeResponse.Data> data) {
        if(data.size() == 0) {
            binding.mainTvList.setVisibility(View.VISIBLE);
            binding.mainTvList.setText("조회 목록이 없습니다.");
        }

        WorryListAdapter wAdapter = new WorryListAdapter(getData());
        binding.mainRvList.setAdapter(wAdapter);
    }

    @Override
    public void getBoardListFailure() {

    }


}