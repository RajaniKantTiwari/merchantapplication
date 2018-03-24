package com.app.merchant.network.response;

/**
 * Created by arvind on 03/11/17.
 */

public class LoginResponseData extends BaseResponse {
   private LoginResponse authkey;

    public LoginResponse getAuthkey() {
        return authkey;
    }

    public void setAuthkey(LoginResponse authkey) {
        this.authkey = authkey;
    }


}
