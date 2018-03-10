package com.app.merchant.network.request.dashboard;

import android.graphics.Bitmap;

/**
 * Created by rajnikant on 11/02/18.
 */

public class StoreImage {
    private String storeName;
    private String imageUrl;
    private Bitmap bitmap;

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

    public void setBitMap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
