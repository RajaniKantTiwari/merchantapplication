package com.app.merchant.network.request.dashboard.home;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 16/03/18.
 */

public class MyOrderData extends BaseResponse{
    private ArrayList<MyOrder> data;

    public ArrayList<MyOrder> getData() {
        return data;
    }

    public void setData(ArrayList<MyOrder> data) {
        this.data = data;
    }
}
