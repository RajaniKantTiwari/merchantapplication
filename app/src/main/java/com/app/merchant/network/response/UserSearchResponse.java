package com.app.merchant.network.response;

/**
 * Created by rajnikant on 23/03/18.
 */

public class UserSearchResponse {
   private int id;
   private String username;
   private String auth_key;
   private String access_token;
   private String password_hash;
   private String password_reset_token;
   private String oauth_client;
    private String oauth_client_user_id;
    private String email;
    private String status;
    private String mobile;
    private String otp;
    private String address;
    private String city;
    private String landmark;
    private String pincode;
    private String lat;
    private String lng;
    private String created_at;
    private String updated_at;
    private String logged_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_reset_token() {
        return password_reset_token;
    }

    public void setPassword_reset_token(String password_reset_token) {
        this.password_reset_token = password_reset_token;
    }

    public String getOauth_client() {
        return oauth_client;
    }

    public void setOauth_client(String oauth_client) {
        this.oauth_client = oauth_client;
    }

    public String getOauth_client_user_id() {
        return oauth_client_user_id;
    }

    public void setOauth_client_user_id(String oauth_client_user_id) {
        this.oauth_client_user_id = oauth_client_user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getLogged_at() {
        return logged_at;
    }

    public void setLogged_at(String logged_at) {
        this.logged_at = logged_at;
    }
}
