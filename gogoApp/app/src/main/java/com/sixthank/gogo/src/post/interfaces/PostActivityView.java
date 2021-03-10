package com.sixthank.gogo.src.post.interfaces;

import com.sixthank.gogo.src.post.models.PostImageResponse;
import com.sixthank.gogo.src.post.models.PostResponse;

public interface PostActivityView {
    void uploadFileSuccess(PostImageResponse response);
    void uploadFileFailure();

    void addBoardSuccess();
    void addBoardFailure();
}
