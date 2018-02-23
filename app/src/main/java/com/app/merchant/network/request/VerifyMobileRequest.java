package com.app.merchant.network.request;



public class VerifyMobileRequest {
    private String mobile;
    private int otp;

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public VerifyMobileRequest(String mobile, int otp) {
        this.mobile=mobile;
        this.otp=otp;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
