package com.app.merchant.network.response.dashboard.deliveryboy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 23/03/18.
 */

public class DeliveryBoyOrder implements Parcelable{
    private String count_orders_assigned;
    private String total_amt;
    private String deliveryboy;
    private String deliveryboy_id;

    public DeliveryBoyOrder() {
    }

    protected DeliveryBoyOrder(Parcel in) {
        count_orders_assigned = in.readString();
        total_amt = in.readString();
        deliveryboy = in.readString();
        deliveryboy_id = in.readString();
    }

    public static final Creator<DeliveryBoyOrder> CREATOR = new Creator<DeliveryBoyOrder>() {
        @Override
        public DeliveryBoyOrder createFromParcel(Parcel in) {
            return new DeliveryBoyOrder(in);
        }

        @Override
        public DeliveryBoyOrder[] newArray(int size) {
            return new DeliveryBoyOrder[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(count_orders_assigned);
        parcel.writeString(total_amt);
        parcel.writeString(deliveryboy);
        parcel.writeString(deliveryboy_id);
    }
}
