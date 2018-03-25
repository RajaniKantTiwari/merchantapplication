package com.app.merchant.network.request.dashboard;

/**
 * Created by rajnikant on 25/03/18.
 */

public class AssignedDeliveryBoyRequest {
    private String orderid;
    private String db_id;
    private String comments="";

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getDb_id() {
        return db_id;
    }

    public void setDb_id(String db_id) {
        this.db_id = db_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
