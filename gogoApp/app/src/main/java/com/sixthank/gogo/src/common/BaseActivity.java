package com.sixthank.gogo.src.common;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.util.HashMap;

public class BaseActivity<B extends ViewBinding> extends AppCompatActivity {
    protected B binding;
    protected final HashMap<String, Object> mChildData = new HashMap<>();
    protected ProgressDialog mProgressDialog;

    private long backBtnTime = 0;

    public void showCustomToast(final String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog() {
        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if(mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 앱이 종료됩니다.",Toast.LENGTH_SHORT).show();
        }

    }

}
