package com.sixthank.gogo.src.main.explore.service;

import com.sixthank.gogo.src.common.ErrorBodyConverter;
import com.sixthank.gogo.src.common.models.ErrorResponse;
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

    public void getExploreList(String keyword, int lastBoardId, int size) {
        if(keyword != null && !keyword.isEmpty()) {
            getSearchList(keyword, lastBoardId, size);
        } else {
            getSearchList(lastBoardId, size);
        }
    }

    public void getSearchList(int lastBoardId, int size) {
        exploreRetrofitInterface.getSearchList(lastBoardId, size).enqueue(new Callback<ExploreResponse>() {
            @Override
            public void onResponse(Call<ExploreResponse> call, Response<ExploreResponse> response) {
                ExploreResponse exploreResponse = response.body();
                if(exploreResponse == null) {
                    ErrorResponse errorResponse = ErrorBodyConverter.getErrorResponse(response.errorBody());
                    exploreFragmentView.getSearchListFailure(errorResponse.getMessage());
                    return;
                }
                exploreFragmentView.getSearchListSuccess(exploreResponse.getData());
            }

            @Override
            public void onFailure(Call<ExploreResponse> call, Throwable t) {
                exploreFragmentView.getSearchListFailure(null);
            }
        });
    }

    public void getSearchList(String keyword, int lastBoardId, int size) {
        exploreRetrofitInterface.getSearchKeywordList(keyword, lastBoardId, size).enqueue(new Callback<ExploreResponse>() {
            @Override
            public void onResponse(Call<ExploreResponse> call, Response<ExploreResponse> response) {
                ExploreResponse exploreResponse = response.body();
                if(exploreResponse == null) {
                    ErrorResponse errorResponse = ErrorBodyConverter.getErrorResponse(response.errorBody());
                    exploreFragmentView.getSearchListFailure(errorResponse.getMessage());
                    return;
                }
                exploreFragmentView.getSearchListSuccess(exploreResponse.getData());
            }

            @Override
            public void onFailure(Call<ExploreResponse> call, Throwable t) {
                exploreFragmentView.getSearchListFailure(null);
            }
        });
    }
}
