package com.app.merchant.network.response;

/**
 * Created by ashok on 24/12/17.
 */

public class VerifyMobileResponse extends BaseResponse {
    private int id;
    private long mobile;
    private String authkey;

    public String getAuthkey() {
        return authkey;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }
}
