package com.sixthank.gogo.src.main;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.sixthank.gogo.databinding.ActivityMainBinding;
import com.sixthank.gogo.src.common.BaseActivity;
import com.sixthank.gogo.src.main.adapter.WorryListAdapter;
import com.sixthank.gogo.src.main.models.WorryResponse;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<WorryResponse> wTopList = new ArrayList<>();
        ArrayList<WorryResponse> wList = new ArrayList<>();

        for(int i = 0; i < 5; i++)
            wTopList.add(new WorryResponse("고민있어요 고민있어요 고민있어요"));

        for(int i = 0; i < 10; i++)
            wList.add(new WorryResponse("고민있어요 고민있어요 고민있어요"));

        WorryListAdapter wTopAdapter = new WorryListAdapter(wTopList);
        binding.mainRvTop5.setAdapter(wTopAdapter);

        WorryListAdapter wAdapter = new WorryListAdapter(wList);
        binding.mainRvList.setAdapter(wAdapter);

    }
}