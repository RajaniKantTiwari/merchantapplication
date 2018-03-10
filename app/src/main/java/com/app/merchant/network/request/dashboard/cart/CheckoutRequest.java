package com.app.merchant.network.request.dashboard.cart;

/**
 * Created by rajnikant on 14/02/18.
 */

public class CheckoutRequest {
private int response;

    public CheckoutRequest(int response) {
      this.response=response;
    }

    public int getResponse() {
        return response;
    }
}
