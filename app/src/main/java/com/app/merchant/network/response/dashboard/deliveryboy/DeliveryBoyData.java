package com.app.merchant.network.response.dashboard.deliveryboy;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 12/03/18.
 */

public class DeliveryBoyData extends BaseResponse {
    private ArrayList<DeliveryBoy> data;

    public ArrayList<DeliveryBoy> getData() {
        return data;
    }

    public void setData(ArrayList<DeliveryBoy> data) {
        this.data = data;
    }
}
