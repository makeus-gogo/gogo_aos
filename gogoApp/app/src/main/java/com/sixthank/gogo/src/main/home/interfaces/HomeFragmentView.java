package com.sixthank.gogo.src.main.home.interfaces;

import com.sixthank.gogo.src.main.home.models.BoardTopNResponse;
import com.sixthank.gogo.src.main.home.models.HomeResponse;

import java.util.List;

public interface HomeFragmentView {
    void getTopNBoardListSuccess(List<BoardTopNResponse.Data> data);
    void getTopNBoardListFailure();

    void getBoardListSuccess(List<HomeResponse.Data> data);
    void getBoardListFailure();
}
