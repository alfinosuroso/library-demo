package com.alfinosuroso.mylibrary.CustomizeModule;

public class PaymentIcon {
    private String paymentCode;

    public PaymentIcon(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public int getIcon() {
        switch (paymentCode) {
            case "10001":
                return io.github.alfinosuroso.R.drawable.ic_tunai;
            case "10004":
                return io.github.alfinosuroso.R.drawable.ic_debit;
            case "10005":
                return io.github.alfinosuroso.R.drawable.ic_cc;
            case "16001":
                return io.github.alfinosuroso.R.drawable.ic_qris;
            case "40002":
                return io.github.alfinosuroso.R.drawable.ic_debit;
            default:
                return io.github.alfinosuroso.R.drawable.ic_debit;
        }
    }
}
