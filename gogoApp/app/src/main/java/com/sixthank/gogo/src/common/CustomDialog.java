package com.sixthank.gogo.src.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

public class CustomDialog {
    Context context;

    AlertDialog.Builder oDialog;

    AlertDialog alertDialog;

    CustomDialogCallback popupCallback;

    public CustomDialog(Context context) {
        this.context = context;
    }

    public void setPopupCallback(CustomDialogCallback popupCallback) {
        this.popupCallback = popupCallback;
    }

    public void showPopupDialog(String message, String type) {
        oDialog = new AlertDialog.Builder(context,
                android.R.style.Theme_DeviceDefault_Light_Dialog);

        WindowManager.LayoutParams wm = new WindowManager.LayoutParams();
        wm.width=200;
        wm.height=200;

        oDialog.setMessage(message)
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Dialog", "취소");
                        if (popupCallback != null)
                            popupCallback.btnPositive(type);

                    }
                })
                .setNeutralButton("아니오", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (popupCallback != null)
                            popupCallback.btnNegative();
                    }
                })
                .setCancelable(false); // 백버튼으로 팝업창이 닫히지 않도록 한다.
        alertDialog = oDialog.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(1000, 550);
    }

}
