package com.app.merchant.network.response.dashboard.cart;



import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by Amul on 28/12/17.
 */

public class ProductDetailsData extends BaseResponse {
private ArrayList<ProductResponse> product;

    public ArrayList<ProductResponse> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<ProductResponse> product) {
        this.product = product;
    }
}
