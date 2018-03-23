package com.app.merchant.network.response;

import java.util.ArrayList;

/**
 * Created by rajnikant on 23/03/18.
 */

public class CustomerDetailData extends BaseResponse{
    private ArrayList<CustomerDetail> data;

    public ArrayList<CustomerDetail> getData() {
        return data;
    }

    public void setData(ArrayList<CustomerDetail> data) {
        this.data = data;
    }
}
