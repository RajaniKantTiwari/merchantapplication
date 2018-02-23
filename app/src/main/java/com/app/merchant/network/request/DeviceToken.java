package com.app.merchant.network.request;



public class DeviceToken {
    private String deviceTokenId;
    private String deveiceUniqId;
    private String deviceType;

    public String getDeviceTokenId() {
        return deviceTokenId;
    }

    public void setDeviceTokenId(String deviceTokenId) {
        this.deviceTokenId = deviceTokenId;
    }

    public String getDeveiceUniqId() {
        return deveiceUniqId;
    }

    public void setDeveiceUniqId(String deveiceUniqId) {
        this.deveiceUniqId = deveiceUniqId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
