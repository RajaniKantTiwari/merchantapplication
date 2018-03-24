package com.app.merchant.network.response.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 24/03/18.
 */

public class MyInventory implements Parcelable {
    private String id;
    private String mID;
    private String mplID;
    private String quantity_add;
    private String batch_no;
    private String date;
    private String supplierID;
    private String purchase_price;
    private String delivery_charges;
    private String expiry_date;
    private String maufacturing_date;
    private String status;
    private String created_at;
    private String updated_at;
    private String isactive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getMplID() {
        return mplID;
    }

    public void setMplID(String mplID) {
        this.mplID = mplID;
    }

    public String getQuantity_add() {
        return quantity_add;
    }

    public void setQuantity_add(String quantity_add) {
        this.quantity_add = quantity_add;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(String purchase_price) {
        this.purchase_price = purchase_price;
    }

    public String getDelivery_charges() {
        return delivery_charges;
    }

    public void setDelivery_charges(String delivery_charges) {
        this.delivery_charges = delivery_charges;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getMaufacturing_date() {
        return maufacturing_date;
    }

    public void setMaufacturing_date(String maufacturing_date) {
        this.maufacturing_date = maufacturing_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public MyInventory() {

    }

    protected MyInventory(Parcel in) {
        id = in.readString();
        mID = in.readString();
        mplID = in.readString();
        quantity_add = in.readString();
        batch_no = in.readString();
        date = in.readString();
        supplierID = in.readString();
        purchase_price = in.readString();
        delivery_charges = in.readString();
        expiry_date = in.readString();
        maufacturing_date = in.readString();
        status = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        isactive = in.readString();
    }

    public static final Creator<MyInventory> CREATOR = new Creator<MyInventory>() {
        @Override
        public MyInventory createFromParcel(Parcel in) {
            return new MyInventory(in);
        }

        @Override
        public MyInventory[] newArray(int size) {
            return new MyInventory[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(mID);
        parcel.writeString(mplID);
        parcel.writeString(quantity_add);
        parcel.writeString(batch_no);
        parcel.writeString(date);
        parcel.writeString(supplierID);
        parcel.writeString(purchase_price);
        parcel.writeString(delivery_charges);
        parcel.writeString(expiry_date);
        parcel.writeString(maufacturing_date);
        parcel.writeString(status);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeString(isactive);
    }
}
