package com.app.merchant.event;

/**
 * Created by rajnikant on 17/03/18.
 */

public class UserEvent {
    private final String name;
    private final String mobileNumber;
    private final String customerid;

    public UserEvent(String customerid, String name, String mobileNumber) {
        this.customerid=customerid;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getName() {
        return name;
    }
    public String getCustomerid() {
        return customerid;
    }
}
