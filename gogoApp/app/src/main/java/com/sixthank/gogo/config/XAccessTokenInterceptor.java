package com.sixthank.gogo.config;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


import static com.sixthank.gogo.config.ApplicationClass.X_ACCESS_TOKEN;
import static com.sixthank.gogo.config.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterceptor implements Interceptor {
    @Override
    @NonNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        final String token = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
        if (token != null) {
            builder.addHeader("Authorization", "Bearer " + token);
        }
        return chain.proceed(builder.build());
    }
}
