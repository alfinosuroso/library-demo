package com.alfinosuroso.mylibrary.Module.Success;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.alfinosuroso.mylibrary.Listener.DialogListener;

import java.text.NumberFormat;
import java.util.Locale;

public class MySuccess {
    public Context ctx;
    public Dialog dialog;
    public TextView tvAmount;
    public TextView tvDate;
    public TextView tvPayment;
    public TextView tvDescription;
    public Button btnBack;
    public DialogListener dialogListener;

    public MySuccess(Context context, DialogListener dialogListener, String amount, String date, String payment, String desc) {
        this.ctx = context;
        this.dialogListener = dialogListener;
        (dialog = new Dialog(context, android.R.style.Theme_Holo_Light_NoActionBar)).setContentView(io.github.alfinosuroso.R.layout.activity_payment_success);
        this.tvAmount = (TextView) this.dialog.findViewById(io.github.alfinosuroso.R.id.tv_amount);
        this.tvDate = (TextView) this.dialog.findViewById(io.github.alfinosuroso.R.id.tv_date);
        this.tvPayment = (TextView) this.dialog.findViewById(io.github.alfinosuroso.R.id.tv_payment);
        this.tvDescription = (TextView) this.dialog.findViewById(io.github.alfinosuroso.R.id.tv_description);
        this.btnBack = (Button) this.dialog.findViewById(io.github.alfinosuroso.R.id.btn_back);
        String formattedAmount = formatToRupiah(Integer.parseInt(amount));
        tvAmount.setText(formattedAmount + " Terkirim");
        tvDate.setText(date);
        tvPayment.setText(payment);
        tvDescription.setText(desc);

        this.dialog.show();
        this.dialog.setCancelable(false);

        this.btnBack.setOnClickListener(view -> {
            this.dialog.dismiss();
            if (this.dialogListener != null) {
                this.dialogListener.onDialogDismiss();
            }
        });
    }

    public String formatToRupiah(double amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        String formattedAmount = currencyFormat.format(amount);

        if (formattedAmount.contains("Rp")) {
            formattedAmount = formattedAmount.replace("Rp", "Rp. ");
        }

        return formattedAmount;
    }
}
