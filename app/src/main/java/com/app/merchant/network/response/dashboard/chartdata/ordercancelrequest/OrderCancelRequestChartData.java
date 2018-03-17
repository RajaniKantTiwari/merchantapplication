package com.app.merchant.network.response.dashboard.chartdata.ordercancelrequest;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderCancelRequestChartData extends BaseResponse {
    private ArrayList<OrderCancelRequestChart> data;

    public ArrayList<OrderCancelRequestChart> getData() {
        return data;
    }

    public void setData(ArrayList<OrderCancelRequestChart> data) {
        this.data = data;
    }
}
