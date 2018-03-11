package com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderOutForDeliveryChartData extends BaseResponse {
    private ArrayList<OrderOutForDeliveryChart> data;

    public ArrayList<OrderOutForDeliveryChart> getData() {
        return data;
    }

    public void setData(ArrayList<OrderOutForDeliveryChart> data) {
        this.data = data;
    }
}
