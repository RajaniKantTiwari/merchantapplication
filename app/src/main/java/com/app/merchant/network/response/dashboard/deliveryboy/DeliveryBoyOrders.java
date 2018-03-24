package com.app.merchant.network.response.dashboard.deliveryboy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 23/03/18.
 */

public class DeliveryBoyOrders implements Parcelable{
    private String orderid;
    private String order_amt;
    private String current_status;
    private String payment_status;
    private String invoiceNumber;
    private String order_date;
    private String delivery_boy_id;
    private String deliveryboy_name;


    protected DeliveryBoyOrders(Parcel in) {
        orderid = in.readString();
        order_amt = in.readString();
        current_status = in.readString();
        payment_status = in.readString();
        invoiceNumber = in.readString();
        order_date = in.readString();
        delivery_boy_id = in.readString();
        deliveryboy_name = in.readString();
    }

    public DeliveryBoyOrders() {
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrder_amt() {
        return order_amt;
    }

    public void setOrder_amt(String order_amt) {
        this.order_amt = order_amt;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getDelivery_boy_id() {
        return delivery_boy_id;
    }

    public void setDelivery_boy_id(String delivery_boy_id) {
        this.delivery_boy_id = delivery_boy_id;
    }

    public String getDeliveryboy_name() {
        return deliveryboy_name;
    }

    public void setDeliveryboy_name(String deliveryboy_name) {
        this.deliveryboy_name = deliveryboy_name;
    }

    public static final Creator<DeliveryBoyOrders> CREATOR = new Creator<DeliveryBoyOrders>() {
        @Override
        public DeliveryBoyOrders createFromParcel(Parcel in) {
            return new DeliveryBoyOrders(in);
        }

        @Override
        public DeliveryBoyOrders[] newArray(int size) {
            return new DeliveryBoyOrders[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(orderid);
        parcel.writeString(order_amt);
        parcel.writeString(current_status);
        parcel.writeString(payment_status);
        parcel.writeString(invoiceNumber);
        parcel.writeString(order_date);
        parcel.writeString(delivery_boy_id);
        parcel.writeString(deliveryboy_name);
    }
}
