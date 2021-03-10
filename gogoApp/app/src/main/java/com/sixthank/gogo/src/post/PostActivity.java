package com.sixthank.gogo.src.post;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.ActivityPostBinding;
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
import java.util.HashMap;

public class PostActivity extends BaseActivity<ActivityPostBinding> implements PostActivityView {

    private Uri uri;
    private Fragment fr;
    private PostService mPostService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mPostService = new PostService(this);

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
                fr = PostSecondFragment.newInstance();
                break;
            case 2:
                fr = new PostSelectFragment();
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
            ((PostSecondFragment) fr).getBinding().postSecondImg.setImageURI(data.getData());

//            filePath = data.getData();
//            String path = filePath.getPath();

            uri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor returnCursor = getContentResolver().query(uri, filePathColumn, null, null, null);
            returnCursor.moveToFirst();
            int columnIndex = returnCursor.getColumnIndex(filePathColumn[0]);
            String filePath = returnCursor.getString(columnIndex);

            File file = new File(filePath);
            mPostService.uploadFile(file);
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

    public HashMap<String, Object> getMap() {
        return mChildData;
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
}