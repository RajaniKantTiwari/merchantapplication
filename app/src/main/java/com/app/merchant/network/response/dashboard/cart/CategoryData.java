package com.app.merchant.network.response.dashboard.cart;


import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;


public class CategoryData extends BaseResponse {
    private ArrayList<Category> info;
    public ArrayList<Category> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<Category> info) {
        this.info = info;
    }
}


