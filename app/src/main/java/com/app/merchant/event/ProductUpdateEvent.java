package com.app.merchant.event;


import com.app.merchant.network.response.dashboard.cart.ProductData;

/**
 * Created by rajnikant on 17/02/18.
 */

public class ProductUpdateEvent {
    private final int position;
    private final ProductData productData;

    public ProductUpdateEvent(int position, ProductData productData) {
        this.position=position;
        this.productData=productData;
    }

    public int getPosition() {
        return position;
    }

    public ProductData getProductData() {
        return productData;
    }
}
