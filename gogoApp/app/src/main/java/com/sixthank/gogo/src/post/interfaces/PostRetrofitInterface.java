package com.sixthank.gogo.src.post.interfaces;

import com.sixthank.gogo.src.post.models.PostBody;
import com.sixthank.gogo.src.post.models.PostResponse;
import com.sixthank.gogo.src.post.models.PostImageResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PostRetrofitInterface {
    @Multipart
    @POST("/api/v1/upload/image")
    Call<PostImageResponse> uploadFile(@Part MultipartBody.Part file);

    @POST("/api/v1/board")
    Call<PostResponse> addBoard(@Body PostBody postBody);
}
