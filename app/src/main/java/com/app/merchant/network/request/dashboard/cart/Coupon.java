package com.app.merchant.network.request.dashboard.cart;

/**
 * Created by rajnikant on 19/02/18.
 */

public class Coupon {
    private boolean isChecked;
    private String couponOffer;

    public String getCouponOffer() {
        return couponOffer;
    }

    public void setCouponOffer(String couponOffer) {
        this.couponOffer = couponOffer;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
