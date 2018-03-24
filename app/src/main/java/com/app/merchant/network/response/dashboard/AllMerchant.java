package com.app.merchant.network.response.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 24/03/18.
 */

public class AllMerchant implements Parcelable {
    private String id;
    private String merchant_id;
    private String product_id;
    private String subcat_id;
    private String master_product_id;
    private String product_mrp;
    private String selling_price;
    private String tax_status;
    private String tax_percent;
    private String status;
    private String tags;
    private String created_at;
    private String updated_at;
    private String isactive;
    private String total_sold;
    private String total_left;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getSubcat_id() {
        return subcat_id;
    }

    public void setSubcat_id(String subcat_id) {
        this.subcat_id = subcat_id;
    }

    public String getMaster_product_id() {
        return master_product_id;
    }

    public void setMaster_product_id(String master_product_id) {
        this.master_product_id = master_product_id;
    }

    public String getProduct_mrp() {
        return product_mrp;
    }

    public void setProduct_mrp(String product_mrp) {
        this.product_mrp = product_mrp;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    public String getTax_status() {
        return tax_status;
    }

    public void setTax_status(String tax_status) {
        this.tax_status = tax_status;
    }

    public String getTax_percent() {
        return tax_percent;
    }

    public void setTax_percent(String tax_percent) {
        this.tax_percent = tax_percent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getTotal_sold() {
        return total_sold;
    }

    public void setTotal_sold(String total_sold) {
        this.total_sold = total_sold;
    }

    public String getTotal_left() {
        return total_left;
    }

    public void setTotal_left(String total_left) {
        this.total_left = total_left;
    }

    public AllMerchant() {

    }

    protected AllMerchant(Parcel in) {
        id = in.readString();
        merchant_id = in.readString();
        product_id = in.readString();
        subcat_id = in.readString();
        master_product_id = in.readString();
        product_mrp = in.readString();
        selling_price = in.readString();
        tax_status = in.readString();
        tax_percent = in.readString();
        status = in.readString();
        tags = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        isactive = in.readString();
        total_sold = in.readString();
        total_left = in.readString();
    }

    public static final Creator<AllMerchant> CREATOR = new Creator<AllMerchant>() {
        @Override
        public AllMerchant createFromParcel(Parcel in) {
            return new AllMerchant(in);
        }

        @Override
        public AllMerchant[] newArray(int size) {
            return new AllMerchant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(merchant_id);
        parcel.writeString(product_id);
        parcel.writeString(subcat_id);
        parcel.writeString(master_product_id);
        parcel.writeString(product_mrp);
        parcel.writeString(selling_price);
        parcel.writeString(tax_status);
        parcel.writeString(tax_percent);
        parcel.writeString(status);
        parcel.writeString(tags);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeString(isactive);
        parcel.writeString(total_sold);
        parcel.writeString(total_left);
    }
}
