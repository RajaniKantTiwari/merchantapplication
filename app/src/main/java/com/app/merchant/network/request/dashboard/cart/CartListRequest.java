package com.app.merchant.network.request.dashboard.cart;

import java.util.ArrayList;

/**
 * Created by rajnikant on 10/02/18.
 */

public class CartListRequest {
    private int merchant_id;
    private ArrayList<Cart> cart;

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public ArrayList<Cart> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Cart> cart) {
        this.cart = cart;
    }
}
