package com.app.merchant.network.request.dashboard.cart;

/**
 * Created by rajnikant on 23/03/18.
 */

public class CancelOrderRequest {
    private String order_id;
    private String reason="";

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
