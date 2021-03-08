package com.sixthank.gogo.src.post.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.FragmentPostRandomBinding;
import com.sixthank.gogo.src.common.BaseFragment;

import java.util.Random;


public class PostRandomFragment extends BaseFragment<FragmentPostRandomBinding> {

    private RandomTimer mTimer;
    private RandomTimer mTimer2;
    private Random mRandom;
    int num = 0;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public PostRandomFragment() {
        // Required empty public constructor
    }


    public static PostRandomFragment newInstance(String param1, String param2) {
        PostRandomFragment fragment = new PostRandomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostRandomBinding.inflate(inflater);

        binding.randomImgClose.setOnClickListener(v->{
            getActivity().finish();
        });
        mRandom = new Random();
        mTimer = new RandomTimer(10000, 1000); //60sec, 1sec

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTimer.start();
                binding.randomRlOx.setVisibility(View.GONE);
            }
        }, 1000);

        return binding.getRoot();
    }

    @Override
    public void onStop() {
        super.onStop();
        mTimer.cancel();
    }

    class RandomTimer extends CountDownTimer
    {
        public RandomTimer(long millisInFuture, long countDownInterval)
        {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long sec = millisUntilFinished/1000 ;

            if(sec % 2 == 0) {
                binding.randomImgO.setVisibility(View.VISIBLE);
                binding.randomImgX.setVisibility(View.GONE);
            } else {
                binding.randomImgO.setVisibility(View.GONE);
                binding.randomImgX.setVisibility(View.VISIBLE);
            }
            if(sec == 0) {
                binding.randomImgO.setVisibility(View.GONE);
                binding.randomImgX.setVisibility(View.GONE);
            }
            if(sec == 5) binding.postRandomTxt.setText("심호흡 한 번 하시고~");
            else binding.postRandomTxt.setText(sec + " 초");
        }

        @Override
        public void onFinish() {
            binding.postRandomTxt.setText("결과가 나왔어요!");
            int randNum = mRandom.nextInt(100) + 1;
            if(randNum % 2 == 0) {
                binding.randomImgX.setVisibility(View.VISIBLE);
            } else {
                binding.randomImgO.setVisibility(View.VISIBLE);
            }
        }
    }

}