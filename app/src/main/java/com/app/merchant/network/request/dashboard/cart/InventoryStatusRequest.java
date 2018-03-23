package com.app.merchant.network.request.dashboard.cart;

/**
 * Created by rajnikant on 23/03/18.
 */

public class InventoryStatusRequest {
    private String merchant_product_id;
    private String status;

    public String getMerchant_product_id() {
        return merchant_product_id;
    }

    public void setMerchant_product_id(String merchant_product_id) {
        this.merchant_product_id = merchant_product_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
