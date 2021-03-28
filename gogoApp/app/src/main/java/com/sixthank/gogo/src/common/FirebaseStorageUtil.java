package com.sixthank.gogo.src.common;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FirebaseStorageUtil {
    private static final String mStorageUrl = "gs://gogoapp-29dcf.appspot.com";
    private static final FirebaseStorage mStorage = FirebaseStorage.getInstance();
    private static StorageReference mStorageRef;
    private static String imageUrl = "";

    private static CallBackListener callBackListener;


    /**
     * 이미지 업로드
     *
     * @param pathUri     저장할 이미지 uri
     */
    public static void uploadImage(Uri pathUri) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "IMAGE_" + timeStamp + ".png";

        mStorageRef = mStorage.getReferenceFromUrl(mStorageUrl).child("images/" + imageName);
        mStorageRef.putFile(pathUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.d("FIREBASE_UPLOAD", "success");
                        getImageUrl();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FIREBASE_UPLOAD", "failure");
                    }
                });
    }

    /**
     * 프로필 이미지 업로드 - 사용자당 1개만 업로드
     *
     * @param pathUri     저장할 이미지 uri
     */
    public static void uploadProfileImage(Uri pathUri, int userId) {
        String imageName = "PROFILE_USER_ID_" + userId + ".png";

        mStorageRef = mStorage.getReferenceFromUrl(mStorageUrl).child("images/" + imageName);
        mStorageRef.putFile(pathUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.d("FIREBASE_PROFILE_UPLOAD", "success");
                        getImageUrl();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FIREBASE_UPLOAD", "failure");
                    }
                });
    }

    /**
     * 업로드된 이미지 url 가져오기
     *
     * @return
     */
    public static void getImageUrl() {
        mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("FIREBASE_URL","success");
                callBackListener.callback(uri.toString());
            }
        });
    }

    public static String getUrl() {
        return imageUrl;
    }

    public interface CallBackListener {
        void callback(String url);
    }

    public static void setCallBackListener(CallBackListener listener) {
        callBackListener = listener;
    }
}

