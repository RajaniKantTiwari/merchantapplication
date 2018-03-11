package com.app.merchant.network.request.dashboard;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 11/02/18.
 */

public class StoreImage implements Parcelable {
    private String storeName;
    private String imageUrl;

    public StoreImage() {

    }

    protected StoreImage(Parcel in) {
        storeName = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<StoreImage> CREATOR = new Creator<StoreImage>() {
        @Override
        public StoreImage createFromParcel(Parcel in) {
            return new StoreImage(in);
        }

        @Override
        public StoreImage[] newArray(int size) {
            return new StoreImage[size];
        }
    };

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(storeName);
        parcel.writeString(imageUrl);
    }
}
