package com.app.merchant.network.response;

/**
 * Created by rajnikant on 24/03/18.
 */

public class NewCustomerResposeData extends BaseResponse {
    private NewCustomerRespose data;

    public NewCustomerRespose getData() {
        return data;
    }

    public void setData(NewCustomerRespose data) {
        this.data = data;
    }
}
