package com.app.merchant.network.response.dashboard.offer;

/**
 * Created by rajnikant on 02/02/18.
 */

public class OfferType {
    private boolean isSelected;
    private String offerType;

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
