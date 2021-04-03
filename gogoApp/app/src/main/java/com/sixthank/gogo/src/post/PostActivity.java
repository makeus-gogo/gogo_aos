package com.sixthank.gogo.src.post;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.ActivityPostBinding;
import com.sixthank.gogo.src.common.BaseActivity;
import com.sixthank.gogo.src.common.FirebaseStorageUtil;
import com.sixthank.gogo.src.post.fragment.PostFirstFragment;
import com.sixthank.gogo.src.post.fragment.PostRandomFragment;
import com.sixthank.gogo.src.post.fragment.PostSecondFragment;
import com.sixthank.gogo.src.post.fragment.PostSelectFragment;
import com.sixthank.gogo.src.post.fragment.PostThirdFragment;
import com.sixthank.gogo.src.post.models.PostBody;
import com.sixthank.gogo.src.post.models.PostImageResponse;
import com.sixthank.gogo.src.post.service.PostService;
import com.sixthank.gogo.src.post.interfaces.PostActivityView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PostActivity extends BaseActivity<ActivityPostBinding> implements PostActivityView, FirebaseStorageUtil.CallBackListener {

    private Fragment fr;
    private PostService mPostService;
    private String type;

    private String mCurrentPhotoPath;
    private Uri mTakePhotoUri;
    private Uri mAlbumPhotoUri;
    private boolean isTakePhoto;
    private boolean isOXFragment;

    private String mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkCameraPermission();

        initVariable();
        initView();
        initListener();
    }

    private void initVariable() {
        mPostService = new PostService(this);
        mDescription = getIntent().getStringExtra("description");
    }

    private void initView() {
        switchFragment(0);
    }

    private void initListener() {
        FirebaseStorageUtil.setCallBackListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        PostSecondFragment secondFragment = null;
        PostSelectFragment selectFragment = null;

        if(isOXFragment) secondFragment = (PostSecondFragment) fr;
        else selectFragment = (PostSelectFragment) fr;

        if (resultCode == RESULT_OK) {
            if(data == null) { // 사진촬영
                if(isOXFragment) secondFragment.setBindingImage(mTakePhotoUri);
                else selectFragment.setBindingImage(mTakePhotoUri);
                isTakePhoto = true;
            } else { // 앨범
                if(isOXFragment) secondFragment.setBindingImage(data.getData());
                else selectFragment.setBindingImage(data.getData());
                isTakePhoto = false;
                mAlbumPhotoUri = data.getData();
            }
        }
    }

    public void addBoard() {
        showProgressDialog();
        if(mCurrentPhotoPath == null && mAlbumPhotoUri == null) {
            PostBody body = new PostBody().toMap(mChildData);
            mPostService.addBoard(body);
            return;
        }
        if(isTakePhoto) {
            Uri uri = Uri.fromFile(new File(mCurrentPhotoPath));
            FirebaseStorageUtil.uploadImage(uri);
        } else {
            FirebaseStorageUtil.uploadImage(mAlbumPhotoUri);
        }
    }

    @Override
    public void callback(String url) {
        PostBody body = new PostBody().toMap(mChildData);
        body.setPictureUrl(url);

        mPostService.addBoard(body);
    }

    @Override
    public void addBoardSuccess() {
        hideProgressDialog();
        showCustomToast("고민이 등록되었습니다.");
        switchFragment(4);
    }

    @Override
    public void addBoardFailure(String message) {
        showCustomToast(message);
    }


    public void switchFragment(int index) {
        fr = new PostFirstFragment();
        switch (index) {
            case 0:
                fr = PostFirstFragment.newInstance(mDescription);
                break;
            case 1:
                fr = PostSecondFragment.newInstance(mChildData.get("description").toString());
                isOXFragment = true;
                break;
            case 2:
                fr = PostSelectFragment.newInstance(mChildData.get("description").toString());
                isOXFragment = false;
                break;
            case 3:
                fr = new PostRandomFragment();
                break;
            case 4:
                fr = new PostThirdFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.post_container, fr).commit();
    }

    private void checkCameraPermission() {
        TedPermission.with(getApplicationContext())
                .setRationaleMessage("카메라 권한이 필요합니다.\n(거부할 경우 진행불가)")
                .setDeniedMessage("카메라 권한을 거부하셨습니다.")
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
//            showCustomToast("accent");
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            showCustomToast("deny");
        }
    };

    public void getAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1001);
    }

    // firebase
    public void takePhoto() {
        // 촬영 후 이미지 가져옴
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (photoFile != null) {

                    Uri providerURI = FileProvider.getUriForFile(this, getPackageName(), photoFile);
                    mTakePhotoUri = providerURI;
                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, providerURI);
                    startActivityForResult(intent, 102);
                }
            }
        } else {

            Log.d("PostActivity","저장공간에 접근 불가능");
            return;
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PNG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void setValueString(String key, String value) {
        mChildData.put(key, value);
    }
    public void setValueObject(String key, ArrayList<String> value) {
        mChildData.put(key, value);
    }

    @Override
    public void onBackPressed() {

    }
}