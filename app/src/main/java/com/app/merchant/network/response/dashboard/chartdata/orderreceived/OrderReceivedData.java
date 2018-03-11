package com.app.merchant.network.response.dashboard.chartdata.orderreceived;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderReceivedData extends BaseResponse{
    private ArrayList<OrderReceived> data;

    public ArrayList<OrderReceived> getData() {
        return data;
    }

    public void setData(ArrayList<OrderReceived> data) {
        this.data = data;
    }
}
