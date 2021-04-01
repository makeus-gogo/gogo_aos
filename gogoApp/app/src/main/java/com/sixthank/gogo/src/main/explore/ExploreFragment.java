package com.sixthank.gogo.src.main.explore;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sixthank.gogo.databinding.FragmentExploreBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.common.OnLoadMoreScrollListener;
import com.sixthank.gogo.src.detail.models.BoardDetailResponse;
import com.sixthank.gogo.src.main.MainActivity;
import com.sixthank.gogo.src.main.explore.adpater.ExploreWorryListAdapter;
import com.sixthank.gogo.src.main.explore.interfaces.ExploreFragmentView;
import com.sixthank.gogo.src.main.explore.models.ExploreResponse;
import com.sixthank.gogo.src.main.explore.service.ExploreService;

import java.util.List;


public class ExploreFragment extends BaseFragment<FragmentExploreBinding> implements ExploreFragmentView{

    private ExploreService mExploreService;
    private ExploreWorryListAdapter mExploreWorryListAdapter;
    private GridLayoutManager mLoadManager;
    private String mKeyword = "";
    private boolean isSearch = false;
    private int insertBeforeIdx = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExploreBinding.inflate(inflater);

        initVariable();
        initView();
        initListener();

        mExploreService.getExploreList(mKeyword, 0, 4);

         return binding.getRoot();

    }

    private void initVariable(){
        mExploreService = new ExploreService(this);
        mExploreWorryListAdapter = new ExploreWorryListAdapter(getContext());
    }

    private void initView(){
        mLoadManager = new GridLayoutManager(getContext(), 2);
        binding.exploreRvWorries.setLayoutManager(mLoadManager);
    }

    private void initListener(){
        binding.exploreViewSearch.setOnClickListener(v->{
            mKeyword = String.valueOf(binding.exploreEtKeyword.getText());
            binding.exploreEtKeyword.setText("");
            isSearch = true;
            mExploreService.getExploreList(mKeyword, 0, 6);
        });

        OnLoadMoreScrollListener onLoadMoreScrollListener = new OnLoadMoreScrollListener(mLoadManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.d("ExploreFragment", "onLoadMore");
                isSearch = false;
                insertBeforeIdx += 9;
                mExploreService.getExploreList(mKeyword, mExploreWorryListAdapter.getLastId(), 4);
            }
        };

        binding.exploreRvWorries.addOnScrollListener(onLoadMoreScrollListener);
        binding.exploreRvWorries.setAdapter(mExploreWorryListAdapter);

    }

    @Override
    public void getSearchListSuccess(List<ExploreResponse.Data> data) {

        if(isSearch)
            mExploreWorryListAdapter.setList(data);
        else
            mExploreWorryListAdapter.addItems(data);
        binding.exploreRvWorries.post(new Runnable() {
            @Override
            public void run() {
//                mExploreWorryListAdapter.notifyDataSetChanged();
                mExploreWorryListAdapter.notifyItemRangeInserted(insertBeforeIdx, mExploreWorryListAdapter.getItemCount());
            }
        });
    }

    @Override
    public void getSearchListFailure(String message) {
        showCustomToast(message);
    }

    public void getSearchList() {
        mExploreService.getExploreList(mKeyword, 0, 4);
    }
}