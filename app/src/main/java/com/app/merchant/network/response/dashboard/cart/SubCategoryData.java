package com.app.merchant.network.response.dashboard.cart;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 25/03/18.
 */

public class SubCategoryData extends BaseResponse {
    private ArrayList<SubCategory> info;

    public ArrayList<SubCategory> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<SubCategory> info) {
        this.info = info;
    }
}
