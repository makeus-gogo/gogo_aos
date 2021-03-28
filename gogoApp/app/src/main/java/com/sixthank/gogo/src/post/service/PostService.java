package com.sixthank.gogo.src.post.service;

import com.sixthank.gogo.src.common.ErrorBodyConverter;
import com.sixthank.gogo.src.common.models.ErrorResponse;
import com.sixthank.gogo.src.post.interfaces.PostRetrofitInterface;
import com.sixthank.gogo.src.post.interfaces.PostActivityView;
import com.sixthank.gogo.src.post.models.PostBody;
import com.sixthank.gogo.src.post.models.PostImageResponse;
import com.sixthank.gogo.src.post.models.PostResponse;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sixthank.gogo.config.ApplicationClass.getRetrofit;

public class PostService {

    private final PostActivityView postActivityView;
    public PostService(PostActivityView postActivityView) {
        this.postActivityView = postActivityView;
    }

    private final PostRetrofitInterface postRetrofitInterface = getRetrofit().create(PostRetrofitInterface.class);

    public void addBoard(PostBody body) {
        postRetrofitInterface.addBoard(body).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse postResponse = response.body();
                if(postResponse == null) {
                    ErrorResponse errorResponse = ErrorBodyConverter.getErrorResponse(response.errorBody());
                    postActivityView.addBoardFailure(errorResponse.getMessage());
                    return;
                }
                postActivityView.addBoardSuccess();
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                postActivityView.addBoardFailure(null);
            }
        });
    }
}
