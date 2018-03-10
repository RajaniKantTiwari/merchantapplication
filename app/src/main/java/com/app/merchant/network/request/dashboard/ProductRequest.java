package com.app.merchant.network.request.dashboard;

/**
 * Created by rajnikant on 31/12/17.
 */

public class ProductRequest {
   private int product_id;
    private int merchant_id;

    public ProductRequest(int product_id, int merchant_id) {
        this.product_id = product_id;
        this.merchant_id = merchant_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }
}
