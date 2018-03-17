package com.app.merchant.network.response.dashboard.chartdata.orderreturnrequest;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderReturnRequestData extends BaseResponse {
    private ArrayList<OrderReturnRequest> data;

    public ArrayList<OrderReturnRequest> getData() {
        return data;
    }

    public void setData(ArrayList<OrderReturnRequest> data) {
        this.data = data;
    }
}
