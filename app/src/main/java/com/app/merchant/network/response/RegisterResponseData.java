package com.app.merchant.network.response;

/**
 * Created by rajnikant on 24/03/18.
 */

public class RegisterResponseData extends BaseResponse {
    private int merchantid;

    public int getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(int merchantid) {
        this.merchantid = merchantid;
    }
}
