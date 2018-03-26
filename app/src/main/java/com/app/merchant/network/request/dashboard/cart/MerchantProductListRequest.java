package com.app.merchant.network.request.dashboard.cart;

/**
 * Created by  on 03/01/18.
 */

public class MerchantProductListRequest {
    private int merchant_id;
    private String master_product_id;
    private String qty;
    private String mrp;
    private String selling_price;
    private String tax_type;
    private String tax_percent;
    private String expirey_date;
    private String manufacturer_date;
    private String manufacturer;
    private int category;
    private int subcategory;

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMaster_product_id() {
        return master_product_id;
    }

    public void setMaster_product_id(String master_product_id) {
        this.master_product_id = master_product_id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    public String getTax_type() {
        return tax_type;
    }

    public void setTax_type(String tax_type) {
        this.tax_type = tax_type;
    }

    public String getTax_percent() {
        return tax_percent;
    }

    public void setTax_percent(String tax_percent) {
        this.tax_percent = tax_percent;
    }

    public String getExpirey_date() {
        return expirey_date;
    }

    public void setExpirey_date(String expirey_date) {
        this.expirey_date = expirey_date;
    }

    public String getManufacturer_date() {
        return manufacturer_date;
    }

    public void setManufacturer_date(String manufacturer_date) {
        this.manufacturer_date = manufacturer_date;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }
}
