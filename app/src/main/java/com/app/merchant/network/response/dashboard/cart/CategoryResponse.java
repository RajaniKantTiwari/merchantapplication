package com.app.merchant.network.response.dashboard.cart;


import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;


public class CategoryResponse extends BaseResponse {
    private ArrayList<CategoryData> info;
    public ArrayList<CategoryData> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<CategoryData> info) {
        this.info = info;
    }
}


