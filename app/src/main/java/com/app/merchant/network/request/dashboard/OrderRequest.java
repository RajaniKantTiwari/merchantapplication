package com.app.merchant.network.request.dashboard;

/**
 * Created by rajnikant on 25/03/18.
 */

public class OrderRequest {
    private String orderid;
    public OrderRequest(String orderid) {
        this.orderid=orderid;
    }
    public String getOrderid() {
        return orderid;
    }

}
