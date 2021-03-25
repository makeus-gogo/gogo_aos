package com.sixthank.gogo.src.detail.service;

import com.sixthank.gogo.src.detail.interfaces.BoardDetailActivityView;
import com.sixthank.gogo.src.detail.interfaces.BoardDetailFragmentView;
import com.sixthank.gogo.src.detail.interfaces.BoardDetailRetrofitInterface;
import com.sixthank.gogo.src.detail.models.BoardAnswerBody;
import com.sixthank.gogo.src.detail.models.BoardAnswerResponse;
import com.sixthank.gogo.src.detail.models.BoardDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sixthank.gogo.config.AppConstants.NOT_FOUND_EXCEPTION;
import static com.sixthank.gogo.config.ApplicationClass.getRetrofit;

public class BoardDetailService {
    private BoardDetailActivityView boardDetailActivityView;
    private BoardDetailFragmentView boardDetailFragmentView;

    private final BoardDetailRetrofitInterface boardDetailRetrofitInterface
            = getRetrofit().create(BoardDetailRetrofitInterface.class);

    public BoardDetailService(BoardDetailActivityView boardDetailActivityView) {
        this.boardDetailActivityView = boardDetailActivityView;
    }

    public BoardDetailService(BoardDetailFragmentView boardDetailFragmentView) {
        this.boardDetailFragmentView = boardDetailFragmentView;
    }

    public void getBoardDetail(int boardId) {
        boardDetailRetrofitInterface.getBoardDetail(boardId).enqueue(new Callback<BoardDetailResponse>() {
            @Override
            public void onResponse(Call<BoardDetailResponse> call, Response<BoardDetailResponse> response) {
                BoardDetailResponse boardDetailResponse = response.body();
                if(boardDetailResponse == null) {
                    boardDetailActivityView.getBoardDetailFailure();
                    return;
                }
                boardDetailActivityView.getBoardDetailSuccess(boardDetailResponse.getData());
            }

            @Override
            public void onFailure(Call<BoardDetailResponse> call, Throwable t) {
                boardDetailActivityView.getBoardDetailFailure();
            }
        });
    }

    public void postBoardAnswer(BoardAnswerBody body, int boardId) {
        boardDetailRetrofitInterface.postBoardAnswer(body, boardId).enqueue(new Callback<BoardAnswerResponse>() {
            @Override
            public void onResponse(Call<BoardAnswerResponse> call, Response<BoardAnswerResponse> response) {
                BoardAnswerResponse boardAnswerResponse = response.body();
                if(boardAnswerResponse == null) {
                    boardDetailFragmentView.postBoardAnswerFailure(null);
                    return;
                }
                boardDetailFragmentView.postBoardAnswerSuccess();
            }

            @Override
            public void onFailure(Call<BoardAnswerResponse> call, Throwable t) {
                boardDetailFragmentView.postBoardAnswerFailure(null);
            }
        });
    }
}
