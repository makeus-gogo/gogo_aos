package com.sixthank.gogo.src.post;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sixthank.gogo.R;
import com.sixthank.gogo.databinding.ActivityPostBinding;
import com.sixthank.gogo.src.common.BaseActivity;
import com.sixthank.gogo.src.post.fragment.PostFirstFragment;
import com.sixthank.gogo.src.post.fragment.PostSecondFragment;
import com.sixthank.gogo.src.post.fragment.PostThirdFragment;
import com.sixthank.gogo.src.post.fragment.service.PostService;
import com.sixthank.gogo.src.post.interfaces.PostActivityView;

import java.io.File;
import java.io.InputStream;

public class PostActivity extends BaseActivity<ActivityPostBinding> implements PostActivityView {

    private Uri mImageUri;
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
                fr = new PostThirdFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.post_container, fr).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String uri = "";
        Uri filePath = null;
//        if (resultCode == RESULT_OK) {
        if (resultCode == RESULT_OK) {
            ((PostSecondFragment) fr).getBinding().postSecondImg.setImageURI(data.getData());
            filePath = data.getData();
            String path = filePath.getPath();

            File file = new File(path);
//            mPostService.uploadFile(file);
        } else {

//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");

        }
    }

    public Uri getImageUri() {
        return mImageUri;
    }
}