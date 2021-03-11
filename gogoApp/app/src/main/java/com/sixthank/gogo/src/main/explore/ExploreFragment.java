package com.sixthank.gogo.src.main.explore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.FragmentExploreBinding;
import com.sixthank.gogo.src.common.BaseFragment;
import com.sixthank.gogo.src.main.MainActivity;
import com.sixthank.gogo.src.main.explore.adpater.ExploreWorryListAdapter;
import com.sixthank.gogo.src.main.explore.interfaces.ExploreFragmentView;
import com.sixthank.gogo.src.main.explore.service.ExploreService;

import java.util.ArrayList;


public class ExploreFragment extends BaseFragment<FragmentExploreBinding> implements ExploreFragmentView {

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

        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < 6; i++)
            list.add("a");
        mExploreService = new ExploreService(this);

        binding.exploreRvWorries.setAdapter(new ExploreWorryListAdapter(list));
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
}