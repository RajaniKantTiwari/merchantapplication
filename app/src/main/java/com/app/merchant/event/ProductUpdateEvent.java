package com.app.merchant.event;


import com.app.merchant.network.response.dashboard.cart.Product;

/**
 * Created by rajnikant on 17/02/18.
 */

public class ProductUpdateEvent {
    private final int position;
    private final Product productData;

    public ProductUpdateEvent(int position, Product productData) {
        this.position=position;
        this.productData=productData;
    }

    public int getPosition() {
        return position;
    }

    public Product getProductData() {
        return productData;
    }
}
