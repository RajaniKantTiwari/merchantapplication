package com.app.merchant.network.response.dashboard.chartdata.orderdelivered;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderDeliveredChartData extends BaseResponse {
    private ArrayList<OrderDeliveredChart> data;

    public ArrayList<OrderDeliveredChart> getData() {
        return data;
    }

    public void setData(ArrayList<OrderDeliveredChart> data) {
        this.data = data;
    }
}
