package com.sixthank.gogo.src.main.explore.service;

import com.sixthank.gogo.src.main.explore.interfaces.ExploreFragmentView;
import com.sixthank.gogo.src.main.explore.interfaces.ExploreRetrofitInterface;
import com.sixthank.gogo.src.main.explore.models.ExploreResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sixthank.gogo.config.ApplicationClass.getRetrofit;

public class ExploreService {
    private final ExploreRetrofitInterface exploreRetrofitInterface
            = getRetrofit().create(ExploreRetrofitInterface.class);
    private final ExploreFragmentView exploreFragmentView;

    public ExploreService(ExploreFragmentView exploreFragmentView) {
        this.exploreFragmentView = exploreFragmentView;
    }

    public void getSearchList(String keyword) {
        exploreRetrofitInterface.getSearchList(keyword).enqueue(new Callback<ExploreResponse>() {
            @Override
            public void onResponse(Call<ExploreResponse> call, Response<ExploreResponse> response) {
                ExploreResponse exploreResponse = response.body();
                if(exploreResponse == null) {
                    exploreFragmentView.getSearchListFailure();
                    return;
                }
                exploreFragmentView.getSearchListSuccess();
            }

            @Override
            public void onFailure(Call<ExploreResponse> call, Throwable t) {
                exploreFragmentView.getSearchListFailure();
            }
        });
    }
}
