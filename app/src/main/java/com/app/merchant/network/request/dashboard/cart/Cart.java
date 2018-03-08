package com.app.merchant.network.request.dashboard.cart;

/**
 * Created by rajnikant on 10/02/18.
 */

public class Cart {
    private int merchantlist_id;
    private int masterproductid;
    private int qty;

    public int getMerchantlist_id() {
        return merchantlist_id;
    }

    public void setMerchantlist_id(int merchantlist_id) {
        this.merchantlist_id = merchantlist_id;
    }

    public int getMasterproductid() {
        return masterproductid;
    }

    public void setMasterproductid(int masterproductid) {
        this.masterproductid = masterproductid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
