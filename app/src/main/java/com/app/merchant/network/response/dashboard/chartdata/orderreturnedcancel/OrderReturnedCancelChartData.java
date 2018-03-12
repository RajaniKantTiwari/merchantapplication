package com.app.merchant.network.response.dashboard.chartdata.orderreturnedcancel;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderReturnedCancelChartData extends BaseResponse {
    private ArrayList<OrderReturnedCancelChart> data;

    public ArrayList<OrderReturnedCancelChart> getData() {
        return data;
    }

    public void setData(ArrayList<OrderReturnedCancelChart> data) {
        this.data = data;
    }
}
