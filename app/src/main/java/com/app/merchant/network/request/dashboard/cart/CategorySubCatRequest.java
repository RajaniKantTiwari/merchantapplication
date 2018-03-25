package com.app.merchant.network.request.dashboard.cart;

/**
 * Created by  on 03/01/18.
 */

public class CategorySubCatRequest {
    private int merchant_id;
    private int category_id;

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
}
