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

import java.util.ArrayList;


public class ExploreFragment extends BaseFragment<FragmentExploreBinding> {

    private MainActivity mParentActivity;
    public ExploreFragment() {
        // Required empty public constructor
    }


    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           
        }

        mParentActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExploreBinding.inflate(inflater);

        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < 6; i++)
            list.add("a");
        binding.exploreRvWorries.setAdapter(new ExploreWorryListAdapter(list));
        return binding.getRoot();
    }
}