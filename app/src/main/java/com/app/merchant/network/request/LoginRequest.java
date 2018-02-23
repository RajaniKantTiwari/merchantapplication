package com.app.merchant.network.request;



public class LoginRequest {
    private String name;
    private String mobile;
    private double lat;
    private double lng;

    public LoginRequest(String name, String mobile, double lat, double lng) {
        this.name = name;
        this.mobile = mobile;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
