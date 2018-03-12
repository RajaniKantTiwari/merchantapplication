package com.app.merchant.network.response.dashboard.chartdata.orderconfirmed;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderConfirmedChartData extends BaseResponse {
    private ArrayList<OrderConfirmedChart> data;

    public ArrayList<OrderConfirmedChart> getData() {
        return data;
    }

    public void setData(ArrayList<OrderConfirmedChart> data) {
        this.data = data;
    }
}
