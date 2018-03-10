package com.app.merchant.network.request.dashboard.cart;

/**
 * Created by rajnikant on 11/01/18.
 */

public class DeleteCartRequest {
    private int merchant_id;
    private int masterproductid;

    public DeleteCartRequest(int merchantId, int masterproductid) {
        this.merchant_id=merchantId;
        this.masterproductid=masterproductid;
    }


    public int getMerchant_id() {
        return merchant_id;
    }


    public int getMasterproductid() {
        return masterproductid;
    }

}
