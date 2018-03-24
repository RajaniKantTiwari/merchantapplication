package com.app.merchant.event;

/**
 * Created by rajnikant on 17/03/18.
 */

public class UserEvent {
    private final String name;
    private final String mobileNumber;

    public UserEvent(String name, String mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getName() {
        return name;
    }
}
