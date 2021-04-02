package com.sixthank.gogo.src.common;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import java.util.HashMap;


public class BaseFragment<B extends ViewBinding> extends Fragment {
    protected B binding;
    protected ProgressDialog mProgressDialog;

    public void showCustomToast(final String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog() {
        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if(mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void hideKeyboard(View view, Context context) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
