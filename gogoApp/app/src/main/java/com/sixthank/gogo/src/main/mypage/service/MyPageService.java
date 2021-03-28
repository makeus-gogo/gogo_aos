package com.sixthank.gogo.src.main.mypage.service;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;
import com.sixthank.gogo.src.common.ErrorBodyConverter;
import com.sixthank.gogo.src.common.models.ErrorResponse;
import com.sixthank.gogo.src.main.mypage.interfaces.MyPageFragmentView;
import com.sixthank.gogo.src.main.mypage.interfaces.MyPageRetrofitInterface;
import com.sixthank.gogo.src.main.mypage.models.MyPageBody;
import com.sixthank.gogo.src.main.mypage.models.MyPageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sixthank.gogo.config.ApplicationClass.getRetrofit;

public class MyPageService {
    private final MyPageRetrofitInterface myPageRetrofitInterface = getRetrofit().create(MyPageRetrofitInterface.class);
    private final MyPageFragmentView myPageFragmentView;
    public MyPageService(MyPageFragmentView myPageFragmentView) {
        this.myPageFragmentView = myPageFragmentView;
    }

    public void getMember() {
        myPageRetrofitInterface.getMember().enqueue(new Callback<MyPageResponse>() {
            @Override
            public void onResponse(Call<MyPageResponse> call, Response<MyPageResponse> response) {
                MyPageResponse myPageResponse = response.body();
                if(myPageResponse == null) {
                    ErrorResponse errorResponse = ErrorBodyConverter.getErrorResponse(response.errorBody());
                    myPageFragmentView.getMemberFailure(errorResponse.getMessage());
                    return;
                }
                myPageFragmentView.getMemberSuccess(myPageResponse.getData());
            }

            @Override
            public void onFailure(Call<MyPageResponse> call, Throwable t) {
                myPageFragmentView.getMemberFailure(null);
            }
        });
    }

    public void editMember(MyPageBody body) {
        myPageRetrofitInterface.patchMember(body).enqueue(new Callback<MyPageResponse>() {
            @Override
            public void onResponse(Call<MyPageResponse> call, Response<MyPageResponse> response) {
                MyPageResponse myPageResponse = response.body();
                if(myPageResponse == null) {
                    ErrorResponse errorResponse = ErrorBodyConverter.getErrorResponse(response.errorBody());
                    myPageFragmentView.patchMemberFailure(errorResponse.getMessage());
                }
                myPageFragmentView.patchMemberSuccess(myPageResponse.getData());
            }

            @Override
            public void onFailure(Call<MyPageResponse> call, Throwable t) {
                myPageFragmentView.patchMemberFailure(null);
            }
        });
    }


}
