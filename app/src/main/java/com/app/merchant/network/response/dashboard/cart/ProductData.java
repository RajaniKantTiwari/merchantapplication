package com.app.merchant.network.response.dashboard.cart;

import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 25/03/18.
 */

public class ProductData extends BaseResponse {
    private ArrayList<Product> info;

    public ArrayList<Product> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<Product> info) {
        this.info = info;
    }
}
