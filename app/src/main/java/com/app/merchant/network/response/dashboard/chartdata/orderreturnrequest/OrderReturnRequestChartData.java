package com.app.merchant.network.response.dashboard.chartdata.orderreturnrequest;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderReturnRequestChartData extends BaseResponse {
    private ArrayList<OrderReturnRequestChart> data;

    public ArrayList<OrderReturnRequestChart> getData() {
        return data;
    }

    public void setData(ArrayList<OrderReturnRequestChart> data) {
        this.data = data;
    }
}
