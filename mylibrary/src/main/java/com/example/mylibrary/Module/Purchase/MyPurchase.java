package com.example.mylibrary.Module.Purchase;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylibrary.Adapter.MethodPaymentAdapter;
import com.example.mylibrary.Listener.DialogListener;
import com.example.mylibrary.Listener.ItemClickListener;
import com.example.mylibrary.Models.MethodPayment;
import com.example.mylibrary.Module.Success.MySuccess;
import com.example.mylibrary.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class MyPurchase implements DialogListener {
    public Context ctx;
    public Dialog dialog;
    public int isTrue;
    public Button btnNext;
    public ImageView btnBack;
    public MyPurchaseCallback mpc;
    public TextView tv1;
    public TextView tv2;
    public ArrayList<MethodPayment> paymentsList;
    public RecyclerView rvPayments;
    public ItemClickListener itemClickListener;
    public MethodPaymentAdapter adapter;
    public String selectedPayment;

    public MyPurchase(final Context context, boolean isDisplay, int amount, ArrayList<MethodPayment> listMethodPayment, MyPurchaseCallback myPurchaseCallback) {
        this.ctx = context;
        this.mpc = myPurchaseCallback;
        (dialog = new Dialog(context, android.R.style.Theme_Holo_Light_NoActionBar)).setContentView(R.layout.activity_payment);
        this.btnBack = (ImageView) this.dialog.findViewById(R.id.iv_back);
        this.btnBack.setOnClickListener(view -> {
            this.dialog.dismiss();
        });
        this.tv1 = (TextView) this.dialog.findViewById(R.id.total_payment_value);
        this.tv2 = (TextView) this.dialog.findViewById(R.id.description_value);

//        Init
        this.paymentsList = listMethodPayment;
        this.rvPayments = (RecyclerView) this.dialog.findViewById(R.id.rv_method_payment);
        itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(String s) {
//                Notify adapter
                rvPayments.post(new Runnable() {
                    @Override
                    public void run() {
                        selectedPayment = s;
                        adapter.notifyDataSetChanged();
                    }
                });
                Toast.makeText(context.getApplicationContext(), "Selected: " + s, Toast.LENGTH_SHORT).show();
            }
        };

        this.tv1.setText(String.valueOf(amount));
        this.tv2.setText("Deskripsi");
        this.btnNext = (Button) this.dialog.findViewById(R.id.btn_next);
        this.btnNext.setOnClickListener(view -> {
//            this.dialog.dismiss();
            if (this.selectedPayment == "" || this.selectedPayment == null) {
                mpc.onFailed("Fail", "Payment tidak tersedia!");
            } else {
                new MySuccess(this.ctx, this, tv1.getText().toString(), "30-08-2024", selectedPayment, this.tv2.getText().toString());
            }
        });

//        Set data
        rvPayments.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MethodPaymentAdapter(context, paymentsList, itemClickListener);
        rvPayments.setAdapter(adapter);

        if (isDisplay) {
            this.isTrue = 1;
        } else {
            this.isTrue = 0;
        }

//        Panggil default
        if (this.isTrue == 1) {
            this.dialog.show();
            Log.i("SUCCESS", "Payment Default Tampil!");
            String brand = Build.BRAND;

//            Belum ada fungsi tambahan (Cek brand)
            if (brand.equals("SUNMI")) {
                Log.i("SUNMI", "Ini sunmi");
            } else {
                Log.i("LAIN", "Ini brand lain");
            }
        }

    }

    @Override
    public void onDialogDismiss() {
        this.dialog.dismiss();
    }
}
