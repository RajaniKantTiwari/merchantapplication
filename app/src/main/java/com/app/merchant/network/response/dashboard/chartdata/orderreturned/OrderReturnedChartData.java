package com.app.merchant.network.response.dashboard.chartdata.orderreturned;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderReturnedChartData extends BaseResponse {
    private ArrayList<OrderReturnedChart> data;

    public ArrayList<OrderReturnedChart> getData() {
        return data;
    }

    public void setData(ArrayList<OrderReturnedChart> data) {
        this.data = data;
    }
}
