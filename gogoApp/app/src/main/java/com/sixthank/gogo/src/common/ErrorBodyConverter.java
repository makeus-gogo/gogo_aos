package com.sixthank.gogo.src.common;

import com.sixthank.gogo.src.common.models.ErrorResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;

import static com.sixthank.gogo.config.ApplicationClass.getRetrofit;

public class ErrorBodyConverter {
    private static ErrorResponse errorResponse;

    public static ErrorResponse getErrorResponse(ResponseBody errBody){
        try {
            errorResponse = (ErrorResponse) getRetrofit()
                    .responseBodyConverter(ErrorResponse.class, new Annotation[0])
                    .convert(errBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return errorResponse;
    }
}
