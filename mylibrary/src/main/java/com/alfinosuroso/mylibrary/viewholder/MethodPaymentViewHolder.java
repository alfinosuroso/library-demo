package com.alfinosuroso.mylibrary.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MethodPaymentViewHolder extends RecyclerView.ViewHolder {
    private TextView txtName;
    private ImageView imgPayment;
    public RadioButton radioButton;
    public LinearLayout linearBtn;

    public MethodPaymentViewHolder(@NonNull View itemView) {
        super(itemView);
        txtName = itemView.findViewById(io.github.alfinosuroso.R.id.txt_payment_name);
        imgPayment = itemView.findViewById(io.github.alfinosuroso.R.id.img_payment_type);
        radioButton = itemView.findViewById(io.github.alfinosuroso.R.id.radio_button_payment);
        linearBtn = itemView.findViewById(io.github.alfinosuroso.R.id.linear_btn);
    }
    public void setName(String paymentName) {
        this.txtName.setText(paymentName);
    }
    public void setImage(int drawableImage) {
        this.imgPayment.setImageResource(drawableImage);
    }
}
