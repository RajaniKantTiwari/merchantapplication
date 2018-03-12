package com.app.merchant.network.response.dashboard.chartdata.orderconfirmed;

import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceived;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderConfirmedData extends BaseResponse {
    private ArrayList<OrderConfirmed> data;

    public ArrayList<OrderConfirmed> getData() {
        return data;
    }

    public void setData(ArrayList<OrderConfirmed> data) {
        this.data = data;
    }
}
