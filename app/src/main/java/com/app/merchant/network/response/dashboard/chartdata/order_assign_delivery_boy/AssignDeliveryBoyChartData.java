package com.app.merchant.network.response.dashboard.chartdata.order_assign_delivery_boy;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 11/03/18.
 */

public class AssignDeliveryBoyChartData extends BaseResponse {
    private ArrayList<AssignDeliveryBoyChart> data;

    public ArrayList<AssignDeliveryBoyChart> getData() {
        return data;
    }

    public void setData(ArrayList<AssignDeliveryBoyChart> data) {
        this.data = data;
    }
}
