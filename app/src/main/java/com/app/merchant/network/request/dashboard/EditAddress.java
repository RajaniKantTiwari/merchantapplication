package com.app.merchant.network.request.dashboard;

/**
 * Created by rajnikant on 06/02/18.
 */

public class EditAddress {
    private boolean isSelected;
    private String address;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
