package com.app.merchant.network.response.dashboard.chartdata.orderdelivered;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderDeliveredData extends BaseResponse{
    private ArrayList<OrderDelivered> data;

    public ArrayList<OrderDelivered> getData() {
        return data;
    }

    public void setData(ArrayList<OrderDelivered> data) {
        this.data = data;
    }
}
