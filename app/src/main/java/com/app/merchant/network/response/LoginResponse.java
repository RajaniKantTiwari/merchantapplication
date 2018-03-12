package com.app.merchant.network.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arvind on 03/11/17.
 */

public class LoginResponse extends BaseResponse {
   private String authkey;

    public String getAuthkey() {
        return authkey;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey;
    }
}
