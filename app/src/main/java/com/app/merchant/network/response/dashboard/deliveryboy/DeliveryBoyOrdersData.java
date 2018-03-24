package com.app.merchant.network.response.dashboard.deliveryboy;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 23/03/18.
 */

public class DeliveryBoyOrdersData extends BaseResponse {
    private ArrayList<DeliveryBoyOrders> data;

    public ArrayList<DeliveryBoyOrders> getData() {
        return data;
    }

    public void setData(ArrayList<DeliveryBoyOrders> data) {
        this.data = data;
    }
}
