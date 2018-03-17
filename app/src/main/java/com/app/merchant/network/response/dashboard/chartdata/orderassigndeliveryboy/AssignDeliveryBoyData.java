package com.app.merchant.network.response.dashboard.chartdata.orderassigndeliveryboy;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class AssignDeliveryBoyData extends BaseResponse {
    private ArrayList<AssignDeliveryBoy> data;

    public ArrayList<AssignDeliveryBoy> getData() {
        return data;
    }

    public void setData(ArrayList<AssignDeliveryBoy> data) {
        this.data = data;
    }
}
