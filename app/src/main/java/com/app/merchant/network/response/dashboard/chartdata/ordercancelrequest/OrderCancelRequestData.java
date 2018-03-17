package com.app.merchant.network.response.dashboard.chartdata.ordercancelrequest;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderCancelRequestData extends BaseResponse {
    private ArrayList<OrderCancelRequest> data;

    public ArrayList<OrderCancelRequest> getData() {
        return data;
    }

    public void setData(ArrayList<OrderCancelRequest> data) {
        this.data = data;
    }
}
