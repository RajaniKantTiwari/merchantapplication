package com.app.merchant.network.response;

import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.UserSearchResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 23/03/18.
 */

public class UserSearchResponseData extends BaseResponse {
    private ArrayList<UserSearchResponse> data;

    public ArrayList<UserSearchResponse> getData() {
        return data;
    }

    public void setData(ArrayList<UserSearchResponse> data) {
        this.data = data;
    }
}
