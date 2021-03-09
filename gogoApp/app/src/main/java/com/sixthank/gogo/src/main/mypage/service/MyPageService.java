package com.sixthank.gogo.src.main.mypage.service;

import com.sixthank.gogo.src.main.mypage.interfaces.MyPageFragmentView;
import com.sixthank.gogo.src.main.mypage.interfaces.MyPageRetrofitInterface;
import com.sixthank.gogo.src.main.mypage.models.MyPageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sixthank.gogo.config.ApplicationClass.getRetrofit;

public class MyPageService {
    private final MyPageFragmentView myPageFragmentView;

    public MyPageService(MyPageFragmentView myPageFragmentView) {
        this.myPageFragmentView = myPageFragmentView;
    }

    public void getMember() {
        MyPageRetrofitInterface myPageRetrofitInterface = getRetrofit().create(MyPageRetrofitInterface.class);
        myPageRetrofitInterface.getMember().enqueue(new Callback<MyPageResponse>() {
            @Override
            public void onResponse(Call<MyPageResponse> call, Response<MyPageResponse> response) {
                MyPageResponse myPageResponse = response.body();
                if(myPageResponse == null) {
                    myPageFragmentView.getMemberFailure();
                    return;
                }
                myPageFragmentView.getMemberSuccess();
            }

            @Override
            public void onFailure(Call<MyPageResponse> call, Throwable t) {
                myPageFragmentView.getMemberFailure();
            }
        });
    }
}
