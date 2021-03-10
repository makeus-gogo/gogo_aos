package com.sixthank.gogo.src.common;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.util.HashMap;

public class BaseActivity<B extends ViewBinding> extends AppCompatActivity {
    protected B binding;
    protected final HashMap<String, Object> mChildData = new HashMap<>();

    public void showCustomToast(final String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
