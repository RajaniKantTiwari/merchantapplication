package com.app.merchant.network.response.dashboard;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 24/03/18.
 */

public class AllMerchantData extends BaseResponse {
    private ArrayList<AllMerchant> data;

    public ArrayList<AllMerchant> getData() {
        return data;
    }

    public void setData(ArrayList<AllMerchant> data) {
        this.data = data;
    }
}
