package com.sixthank.gogo.src.detail.interfaces;

import com.sixthank.gogo.src.detail.models.BoardDetailResponse;

public interface BoardDetailActivityView {
    void getBoardDetailSuccess(BoardDetailResponse.Data data);
    void getBoardDetailFailure();
}
