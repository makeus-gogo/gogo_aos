package com.sixthank.gogo.src.common;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import java.util.HashMap;


public class BaseFragment<B extends ViewBinding> extends Fragment {
    protected B binding;

    public void showCustomToast(final String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
