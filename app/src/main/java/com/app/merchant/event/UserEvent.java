package com.app.merchant.event;

/**
 * Created by rajnikant on 17/03/18.
 */

public class UserEvent {
    private final String mobileNumber;
    public UserEvent(String mobileNumber) {
        this.mobileNumber=mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
