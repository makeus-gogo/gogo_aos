package com.sixthank.gogo.src.common;

import android.graphics.drawable.ColorDrawable;
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
}
