package com.app.merchant.network.response.dashboard.deliveryboy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 12/03/18.
 */

public class DeliveryBoy implements Parcelable {
    private int id;
    private String name;

    public DeliveryBoy() {

    }

    protected DeliveryBoy(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<DeliveryBoy> CREATOR = new Creator<DeliveryBoy>() {
        @Override
        public DeliveryBoy createFromParcel(Parcel in) {
            return new DeliveryBoy(in);
        }

        @Override
        public DeliveryBoy[] newArray(int size) {
            return new DeliveryBoy[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }
}
