package com.sixthank.gogo.src.post.fragment.interfaces;

import com.sixthank.gogo.src.login.models.LoginResponse;

import java.util.List;

public interface PostFragmentView {
    void postImageSuccess(int code, LoginResponse results);
    void postImageFailure(String message);


}
