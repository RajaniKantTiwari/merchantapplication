package com.app.merchant.network.request;

/**
 * Created by rajnikant on 23/03/18.
 */

public class CustomerRequest {
    private String customer_id;

    public String getCustomer_id() {
        return customer_id;
    }

    public CustomerRequest(String customer_id) {
        this.customer_id = customer_id;
    }
}
