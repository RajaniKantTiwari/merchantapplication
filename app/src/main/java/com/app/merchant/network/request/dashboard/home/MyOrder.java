package com.app.merchant.network.request.dashboard.home;

/**
 * Created by rajnikant on 25/02/18.
 */

public class MyOrder {
    private int order_count;

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    private String order_status;

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
