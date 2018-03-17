package com.app.merchant.network.response.dashboard.chartdata.order_assign_delivery_boy;

import com.app.merchant.network.response.BaseResponse;

/**
 * Created by rajnikant on 11/03/18.
 */

public class AssignDeliveryBoy extends BaseResponse {
    private String id;
    private String invoiceNumber;
    private String createdAt;
    private String totaldue;
    private String paymentStatus;
    private String order_status;
    private String address;
    private String deliveryboy;
    private String ratings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTotaldue() {
        return totaldue;
    }

    public void setTotaldue(String totaldue) {
        this.totaldue = totaldue;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryboy() {
        return deliveryboy;
    }

    public void setDeliveryboy(String deliveryboy) {
        this.deliveryboy = deliveryboy;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
