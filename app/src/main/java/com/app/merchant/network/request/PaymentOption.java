package com.app.merchant.network.request;



public class PaymentOption {
    private boolean isChecked;
    private String paymentString;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getPaymentString() {
        return paymentString;
    }

    public void setPaymentString(String paymentString) {
        this.paymentString = paymentString;
    }
}
