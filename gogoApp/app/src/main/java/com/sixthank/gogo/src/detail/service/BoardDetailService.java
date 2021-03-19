package com.sixthank.gogo.src.detail.service;

import com.sixthank.gogo.src.detail.interfaces.BoardDetailFragmentView;
import com.sixthank.gogo.src.detail.interfaces.BoardDetailRetrofitInterface;
import com.sixthank.gogo.src.detail.models.BoardDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sixthank.gogo.config.ApplicationClass.getRetrofit;

public class BoardDetailService {
    private final BoardDetailFragmentView boardDetailFragmentView;

    private final BoardDetailRetrofitInterface boardDetailRetrofitInterface
            = getRetrofit().create(BoardDetailRetrofitInterface.class);

    public BoardDetailService(BoardDetailFragmentView boardDetailFragmentView) {
        this.boardDetailFragmentView = boardDetailFragmentView;
    }

    public void getBoardDetail(int boardId) {
        boardDetailRetrofitInterface.getBoardDetail(boardId).enqueue(new Callback<BoardDetailResponse>() {
            @Override
            public void onResponse(Call<BoardDetailResponse> call, Response<BoardDetailResponse> response) {
                BoardDetailResponse boardDetailResponse = response.body();
                if(boardDetailResponse == null) {
                    boardDetailFragmentView.getBoardDetailFailure();
                    return;
                }
                boardDetailFragmentView.getBoardDetailSuccess(boardDetailResponse.getData());
            }

            @Override
            public void onFailure(Call<BoardDetailResponse> call, Throwable t) {
                boardDetailFragmentView.getBoardDetailFailure();
            }
        });
    }
}
