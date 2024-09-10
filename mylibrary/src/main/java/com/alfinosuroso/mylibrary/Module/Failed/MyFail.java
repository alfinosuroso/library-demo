package com.alfinosuroso.mylibrary.Module.Failed;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.example.mylibrary.R;

public class MyFail {
    public Context ctx;
    public Dialog dialog;
    public String str;
    public TextView tvFail;
    public TextView tvDescription;
    public Button btnBack;

    public MyFail(Context context, String msgFail, String msgDesc) {
        (dialog = new Dialog(context, android.R.style.Theme_Holo_Light_NoActionBar)).setContentView(R.layout.activity_payment_fail);
        this.tvFail = (TextView) this.dialog.findViewById(R.id.tv_fail);
        this.tvDescription = (TextView) this.dialog.findViewById(R.id.tv_description);
        this.btnBack = (Button) this.dialog.findViewById(R.id.btn_back);
        tvFail.setText(msgFail);
        tvDescription.setText(msgDesc);

        this.dialog.show();
        this.dialog.setCancelable(false);

        this.btnBack.setOnClickListener(view -> {
            this.dialog.dismiss();
        });
    }
}
