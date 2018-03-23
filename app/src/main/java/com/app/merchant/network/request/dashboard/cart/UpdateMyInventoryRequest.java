package com.app.merchant.network.request.dashboard.cart;

/**
 * Created by rajnikant on 23/03/18.
 */

public class UpdateMyInventoryRequest {
    private String merchant_product_id;
    private String qty;
    private String expirydate;
    private String manufactdate;
    private String manufacturer;

    public String getMerchant_product_id() {
        return merchant_product_id;
    }

    public void setMerchant_product_id(String merchant_product_id) {
        this.merchant_product_id = merchant_product_id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getManufactdate() {
        return manufactdate;
    }

    public void setManufactdate(String manufactdate) {
        this.manufactdate = manufactdate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
