package com.app.merchant.network.response.dashboard.deliveryboy;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 23/03/18.
 */

public class DeliveryBoyOrderData extends BaseResponse{
    private ArrayList<DeliveryBoyOrder> data;

    public ArrayList<DeliveryBoyOrder> getData() {
        return data;
    }

    public void setData(ArrayList<DeliveryBoyOrder> data) {
        this.data = data;
    }
}
