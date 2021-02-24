package com.sixthank.gogo.src.common;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public class BaseActivity<B extends ViewBinding> extends AppCompatActivity {
    protected B binding;

    public void showCustomToast(final String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
