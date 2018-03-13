package com.app.merchant.network.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arvind on 03/11/17.
 */

public class LoginResponse extends BaseResponse {
    private int id;
   private String auth_key;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }
}
