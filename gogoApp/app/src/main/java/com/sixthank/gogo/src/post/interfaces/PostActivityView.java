package com.sixthank.gogo.src.post.interfaces;

import com.sixthank.gogo.src.post.models.PostImageResponse;
import com.sixthank.gogo.src.post.models.PostResponse;

public interface PostActivityView {

    void addBoardSuccess();
    void addBoardFailure(String message);
}
