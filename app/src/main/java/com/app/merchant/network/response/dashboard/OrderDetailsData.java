package com.app.merchant.network.response.dashboard;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 25/03/18.
 */

public class OrderDetailsData extends BaseResponse {
    private ArrayList<OrderDetail> data;

    public ArrayList<OrderDetail> getData() {
        return data;
    }

    public void setData(ArrayList<OrderDetail> data) {
        this.data = data;
    }
}
