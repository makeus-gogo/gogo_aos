package com.sixthank.gogo.src.main.explore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.FragmentExploreBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.common.OnItemClickListener;
import com.sixthank.gogo.src.detail.BoardDetailActivity;
import com.sixthank.gogo.src.main.MainActivity;
import com.sixthank.gogo.src.main.explore.adpater.ExploreWorryListAdapter;
import com.sixthank.gogo.src.main.explore.interfaces.ExploreFragmentView;
import com.sixthank.gogo.src.main.explore.models.ExploreResponse;
import com.sixthank.gogo.src.main.explore.service.ExploreService;

import java.util.ArrayList;


public class ExploreFragment extends BaseFragment<FragmentExploreBinding>
        implements ExploreFragmentView, OnItemClickListener {

    private MainActivity mParentActivity;
    private ExploreService mExploreService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mParentActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExploreBinding.inflate(inflater);

//        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//        binding.mainRvList.setLayoutManager(manager);
//        OnLoadMoreScrollListener onLoadMoreScrollListener = new OnLoadMoreScrollListener(manager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                Log.d("HomeFragment", "onLoadMore");
//                mWorryListAdapter.addItems(mData);
////                binding.mainRvList.post(new Runnable() {
////                    @Override
////                    public void run() {
////                        mWorryListAdapter.notifyDataSetChanged();
////                    }
////                });
//            }
//        };
//        binding.mainRvList.addOnScrollListener(onLoadMoreScrollListener);
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < 6; i++)
            list.add("a");
        mExploreService = new ExploreService(this);

        ExploreWorryListAdapter adapter = new ExploreWorryListAdapter(list);
        adapter.setListener(this);
        binding.exploreRvWorries.setAdapter(adapter);
        binding.exploreViewSearch.setOnClickListener(v->{
            String keyword = binding.exploreEtKeyword.toString();
            if(keyword.isEmpty()) {
                showCustomToast("검색어를 입력해주세요");
                return;
            }
            mExploreService.getSearchList(keyword);
        });

        return binding.getRoot();
    }

    @Override
    public void getSearchListSuccess() {
        showCustomToast("검색 성공");
    }

    @Override
    public void getSearchListFailure() {
        showCustomToast("검색 실페");
    }

    @Override
    public void onItemClick(View v, Object item) {
//        ExploreResponse.Data board = (ExploreResponse.Data) item;
//        showCustomToast("ok");
        // 볼러온 리스트 아이템으로 보여줄 수도 있고
        // 새롭게 조회해서 보여줄 수도 있움
        Intent intent = new Intent(getActivity(), BoardDetailActivity.class);
//        intent.putExtra("boardId", board.getId());
        intent.putExtra("boardId", 1);
        startActivity(intent);
    }
}