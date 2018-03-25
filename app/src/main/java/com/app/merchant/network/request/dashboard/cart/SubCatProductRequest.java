package com.app.merchant.network.request.dashboard.cart;

/**
 * Created by  on 03/01/18.
 */

public class SubCatProductRequest {
    private int merchant_id;
    private int category_id;
    private int sub_category_id;


    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(int sub_category_id) {
        this.sub_category_id = sub_category_id;
    }
}
