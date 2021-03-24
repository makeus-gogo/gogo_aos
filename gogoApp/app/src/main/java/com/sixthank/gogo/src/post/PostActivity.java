package com.sixthank.gogo.src.post;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.ActivityPostBinding;
import com.sixthank.gogo.databinding.FragmentPostSecondBinding;
import com.sixthank.gogo.src.common.BaseActivity;
import com.sixthank.gogo.src.post.fragment.PostFirstFragment;
import com.sixthank.gogo.src.post.fragment.PostRandomFragment;
import com.sixthank.gogo.src.post.fragment.PostSecondFragment;
import com.sixthank.gogo.src.post.fragment.PostSelectFragment;
import com.sixthank.gogo.src.post.fragment.PostThirdFragment;
import com.sixthank.gogo.src.post.models.PostBody;
import com.sixthank.gogo.src.post.models.PostImageResponse;
import com.sixthank.gogo.src.post.models.PostResponse;
import com.sixthank.gogo.src.post.service.PostService;
import com.sixthank.gogo.src.post.interfaces.PostActivityView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PostActivity extends BaseActivity<ActivityPostBinding> implements PostActivityView {

    private Uri uri;
    private Fragment fr;
    private PostService mPostService;
    private String type;

    String currentPhotoPath;
    Uri imgUri;
    String imageFileName;
    private StorageReference mStorageRef;
    private FirebaseStorage mStorage;

    private String sendUrl;

    private final String mStorageUrl = "gs://gogoapp-29dcf.appspot.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkCameraPermission();
        mPostService = new PostService(this);
        mStorage = FirebaseStorage.getInstance();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(0x00000000);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        switchFragment(0);
    }

    public void switchFragment(int index) {
        fr = new PostFirstFragment();
        switch (index) {
            case 0:
                fr = new PostFirstFragment();
                break;
            case 1:
                fr = new PostSecondFragment();
                type = "OX";
                break;
            case 2:
                fr = new PostSelectFragment();
                type = "MULTI_CHOICE";
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        String uri = "";
//        Uri filePath = null;

        if (resultCode == RESULT_OK) {
            Uri filePath = Uri.fromFile(new File(currentPhotoPath));
            uploadImage(filePath);
//            if(type.equals("OX")) {
//                ((PostSecondFragment) fr).getBinding().postSecondImg.setImageURI(data.getData());
//            } else {
//                ((PostSelectFragment) fr).getBinding().postSelectImg.setImageURI(data.getData());
//            }

//            uri = data.getData();
//            uploadImage(uri);

//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//            Cursor returnCursor = getContentResolver().query(uri, filePathColumn, null, null, null);
//            returnCursor.moveToFirst();
//            int columnIndex = returnCursor.getColumnIndex(filePathColumn[0]);
//            String filePath = returnCursor.getString(columnIndex);
//
//            File file = new File(filePath);
//            mPostService.uploadFile(file);
        } else {

//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");

        }
    }

    public void addBoard() {
        PostBody body = new PostBody().toMap(mChildData);
        mPostService.addBoard(body);
    }

    public void setValueString(String key, String value) {
        mChildData.put(key, value);
    }
    public void setValueObject(String key, ArrayList<String> value) {
        mChildData.put(key, value);
    }

    @Override
    public void uploadFileSuccess(PostImageResponse response) {
        showCustomToast("이미지 업로드 성공");
        setValueString("pictureUrl", response.getData());
    }

    @Override
    public void uploadFileFailure() {
        showCustomToast("이미지 업로드 실페");
    }

    @Override
    public void addBoardSuccess() {
        showCustomToast("글 업로드 성공");
        switchFragment(4);
    }

    @Override
    public void addBoardFailure() {
        showCustomToast("글 업로드 실페");
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
                    imgUri = providerURI;
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
        imageFileName = "PNG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void uploadImage(Uri filePath) {
        String filename = "TEST.png";

        mStorageRef = mStorage.getReferenceFromUrl(mStorageUrl).child("images/" + filename);
        mStorageRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        showCustomToast("업로드 성공");
                        mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.d("FIREBASE2", uri.toString());
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showCustomToast("업로드 실패");
                    }
                });
    }
}