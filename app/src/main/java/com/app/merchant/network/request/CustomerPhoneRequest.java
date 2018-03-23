package com.app.merchant.network.request;

/**
 * Created by rajnikant on 23/03/18.
 */

public class CustomerPhoneRequest {
    private String phoneno;

    public CustomerPhoneRequest(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPhoneno() {
        return phoneno;
    }
}
