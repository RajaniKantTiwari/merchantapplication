package com.app.merchant.network.request.dashboard.home;

/**
 * Created by rajnikant on 23/03/18.
 */

public class DeliveryBoyOrderDetailRequest {
    private String delivery_boy_id;

    public DeliveryBoyOrderDetailRequest(String deliveryBoyId) {
        this.delivery_boy_id = delivery_boy_id;
    }

    public String getDelivery_boy_id() {
        return delivery_boy_id;
    }

}
