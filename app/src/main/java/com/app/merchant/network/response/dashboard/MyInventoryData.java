package com.app.merchant.network.response.dashboard;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 24/03/18.
 */

public class MyInventoryData extends BaseResponse {
    private ArrayList<MyInventory> data;

    public ArrayList<MyInventory> getData() {
        return data;
    }

    public void setData(ArrayList<MyInventory> data) {
        this.data = data;
    }
}
