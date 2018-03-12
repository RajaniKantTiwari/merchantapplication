package com.app.merchant.network.response.dashboard.chartdata.orderreturned;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderReturnedData extends BaseResponse{
    private ArrayList<OrderReturned> data;

    public ArrayList<OrderReturned> getData() {
        return data;
    }

    public void setData(ArrayList<OrderReturned> data) {
        this.data = data;
    }
}
