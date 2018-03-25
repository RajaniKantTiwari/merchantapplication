package com.app.merchant.network.response.dashboard;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 25/03/18.
 */

public class OrderData extends BaseResponse{
    private ArrayList<Order> data;

    public ArrayList<Order> getData() {
        return data;
    }

    public void setData(ArrayList<Order> data) {
        this.data = data;
    }
}
