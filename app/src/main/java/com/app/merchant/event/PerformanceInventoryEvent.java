package com.app.merchant.event;

/**
 * Created by rajnikant on 16/03/18.
 */

public class PerformanceInventoryEvent {
    //use 1 for meeting 2 for event
    private int productInventory;

    public int getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(int productInventory) {
        this.productInventory = productInventory;
    }
}
