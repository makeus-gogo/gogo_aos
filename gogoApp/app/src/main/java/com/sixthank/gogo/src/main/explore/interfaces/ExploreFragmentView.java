package com.sixthank.gogo.src.main.explore.interfaces;

import com.sixthank.gogo.src.detail.models.BoardDetailResponse;
import com.sixthank.gogo.src.main.explore.models.ExploreResponse;

import java.util.List;

public interface ExploreFragmentView {
    void getSearchListSuccess(List<ExploreResponse.Data> data);
    void getSearchListFailure(String message);
}
