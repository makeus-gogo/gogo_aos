package com.sixthank.gogo.src.main.home.service;

import com.sixthank.gogo.src.common.ErrorBodyConverter;
import com.sixthank.gogo.src.common.models.ErrorResponse;
import com.sixthank.gogo.src.main.home.interfaces.HomeFragmentView;
import com.sixthank.gogo.src.main.home.interfaces.HomeRetrofitInterface;
import com.sixthank.gogo.src.main.home.models.BoardTopNResponse;
import com.sixthank.gogo.src.main.home.models.HomeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sixthank.gogo.config.ApplicationClass.getRetrofit;

public class HomeService {
    private HomeRetrofitInterface homeRetrofitInterface = getRetrofit().create(HomeRetrofitInterface.class);

    private final HomeFragmentView homeFragmentView;

    public HomeService(HomeFragmentView homeFragmentView) {
        this.homeFragmentView = homeFragmentView;
    }

    public void getTopNListBoardList() {
        homeRetrofitInterface.getTopNBoardList().enqueue(new Callback<BoardTopNResponse>() {
            @Override
            public void onResponse(Call<BoardTopNResponse> call, Response<BoardTopNResponse> response) {
                BoardTopNResponse boardTopNResponse = response.body();
                if (boardTopNResponse == null) {
                    ErrorResponse errorResponse = ErrorBodyConverter.getErrorResponse(response.errorBody());
                    homeFragmentView.getTopNBoardListFailure(errorResponse.getMessage());
                    return;
                }
                homeFragmentView.getTopNBoardListSuccess(boardTopNResponse.getData());
            }

            @Override
            public void onFailure(Call<BoardTopNResponse> call, Throwable t) {
                homeFragmentView.getTopNBoardListFailure(null);
            }
        });
    }

    public void getListBoardList(int lastBoardId, int size) {
        homeRetrofitInterface.getBoardList(lastBoardId, size).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                HomeResponse homeResponse = response.body();
                if(homeResponse == null) {
                    ErrorResponse errorResponse = ErrorBodyConverter.getErrorResponse(response.errorBody());
                    homeFragmentView.getBoardListFailure(errorResponse.getMessage());
                    return;
                }
                homeFragmentView.getBoardListSuccess(homeResponse.getData());
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                homeFragmentView.getBoardListFailure(null);
            }
        });
    }
}
