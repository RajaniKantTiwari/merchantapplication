package com.app.merchant.network.response.dashboard.chartdata.orderreturnedcancel;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderReturnedCancelData extends BaseResponse{
    private ArrayList<OrderReturnedCancel> data;

    public ArrayList<OrderReturnedCancel> getData() {
        return data;
    }

    public void setData(ArrayList<OrderReturnedCancel> data) {
        this.data = data;
    }
}
