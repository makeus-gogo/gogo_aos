package com.sixthank.gogo.src.post.fragment;

import android.os.Bundle;



import android.os.CountDownTimer;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sixthank.gogo.databinding.FragmentPostRandomBinding;
import com.sixthank.gogo.src.common.BaseFragment;

import java.util.Random;


public class PostRandomFragment extends BaseFragment<FragmentPostRandomBinding> {

    private RandomTimer mTimer;
    private RandomTimer mTimer2;
    private Random mRandom;
    int num = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostRandomBinding.inflate(inflater);

        binding.randomImgClose.setOnClickListener(v->{
            getActivity().finish();
        });
        mRandom = new Random();
        mTimer = new RandomTimer(6000, 1000); //5sec, 1sec

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
//            if(sec == 5) binding.postRandomTxt.setText("심호흡 한 번 하시고~");
            binding.postRandomTxt.setText(sec + " 초");
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