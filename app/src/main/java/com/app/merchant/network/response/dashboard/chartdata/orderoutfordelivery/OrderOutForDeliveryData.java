package com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderOutForDeliveryData extends BaseResponse {
    private ArrayList<OrderOutForDelivery> data;

    public ArrayList<OrderOutForDelivery> getData() {
        return data;
    }

    public void setData(ArrayList<OrderOutForDelivery> data) {
        this.data = data;
    }
}
