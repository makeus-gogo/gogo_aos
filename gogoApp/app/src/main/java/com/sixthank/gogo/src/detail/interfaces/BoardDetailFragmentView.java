package com.sixthank.gogo.src.detail.interfaces;

import com.sixthank.gogo.src.detail.models.BoardAnswerResponse;

public interface BoardDetailFragmentView {
    void postBoardAnswerSuccess(BoardAnswerResponse.Data data);
    void postBoardAnswerFailure(String message);
}
