package com.app.merchant.network.request;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by rajnikant on 10/03/18.
 */

public class A implements Parcelable{
    private ArrayList<String> arrayList;

    protected A(Parcel in) {
        arrayList = in.createStringArrayList();
    }

    public static final Creator<A> CREATOR = new Creator<A>() {
        @Override
        public A createFromParcel(Parcel in) {
            return new A(in);
        }

        @Override
        public A[] newArray(int size) {
            return new A[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(arrayList);
    }
}
