package com.app.merchant.network.response.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 25/03/18.
 */

public class Order implements Parcelable {
    private String order_id;
    private String totalitems;
    private String customername;
    private String customer_delivery_address;
    private String ordervalue;
    private String customeremail;
    private String customermobile;

    public Order() {

    }

    protected Order(Parcel in) {
        order_id = in.readString();
        totalitems = in.readString();
        customername = in.readString();
        customer_delivery_address = in.readString();
        ordervalue = in.readString();
        customeremail = in.readString();
        customermobile = in.readString();
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getTotalitems() {
        return totalitems;
    }

    public void setTotalitems(String totalitems) {
        this.totalitems = totalitems;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomer_delivery_address() {
        return customer_delivery_address;
    }

    public void setCustomer_delivery_address(String customer_delivery_address) {
        this.customer_delivery_address = customer_delivery_address;
    }

    public String getOrdervalue() {
        return ordervalue;
    }

    public void setOrdervalue(String ordervalue) {
        this.ordervalue = ordervalue;
    }

    public String getCustomeremail() {
        return customeremail;
    }

    public void setCustomeremail(String customeremail) {
        this.customeremail = customeremail;
    }

    public String getCustomermobile() {
        return customermobile;
    }

    public void setCustomermobile(String customermobile) {
        this.customermobile = customermobile;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(order_id);
        parcel.writeString(totalitems);
        parcel.writeString(customername);
        parcel.writeString(customer_delivery_address);
        parcel.writeString(ordervalue);
        parcel.writeString(customeremail);
        parcel.writeString(customermobile);
    }
}
