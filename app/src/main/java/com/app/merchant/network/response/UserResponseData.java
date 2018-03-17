package com.app.merchant.network.response;

import java.util.ArrayList;

/**
 * Created by rajnikant on 17/03/18.
 */

public class UserResponseData extends BaseResponse {
    private ArrayList<UserResponse> data;

    public ArrayList<UserResponse> getData() {
        return data;
    }

    public void setData(ArrayList<UserResponse> data) {
        this.data = data;
    }
}
