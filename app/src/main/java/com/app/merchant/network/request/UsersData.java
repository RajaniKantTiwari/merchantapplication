package com.app.merchant.network.request;

/**
 * Created by rajnikant on 09/03/18.
 */

public class UsersData {
    private String mobileNumber;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
