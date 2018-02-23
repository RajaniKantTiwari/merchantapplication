package com.app.merchant.network.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arvind on 03/11/17.
 */

public class LoginResponse extends BaseResponse implements Parcelable {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LoginResponse(){

    }
    protected LoginResponse(Parcel in) {
        type = in.readString();
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
    }
}
