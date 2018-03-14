package com.app.merchant.network.request.dashboard.cart;

/**
 * Created by rajnikant on 11/01/18.
 */

public class CartRequest {
    private int merchant_id;
    private int merchantlist_id;
    private int masterproductid;
    private int qty;
    private int userid;

    public int getMasterproductid() {
        return masterproductid;
    }

    public void setMasterproductid(int masterproductid) {
        this.masterproductid = masterproductid;
    }

    public int getMerchantlist_id() {
        return merchantlist_id;
    }

    public void setMerchantlist_id(int merchantlist_id) {
        this.merchantlist_id = merchantlist_id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
