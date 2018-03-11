package com.app.merchant.network.response.dashboard.chartdata;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderReceivedChartData extends BaseResponse {
    private ArrayList<OrderReceivedChart> data;

    public ArrayList<OrderReceivedChart> getData() {
        return data;
    }

    public void setData(ArrayList<OrderReceivedChart> data) {
        this.data = data;
    }
}
