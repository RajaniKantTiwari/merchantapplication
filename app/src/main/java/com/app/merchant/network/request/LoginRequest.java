package com.app.merchant.network.request;

/**
 * Created by rajnikant on 12/03/18.
 */

public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest(String email, String password) {
        this.email=email;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

}
