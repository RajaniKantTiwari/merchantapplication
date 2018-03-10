package com.app.merchant.event;

/**
 * Created by rajnikant on 19/02/18.
 */

public class EncodedBitmap {
    private final String encodeImage;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public EncodedBitmap(int type, String encodeImage) {
        this.type=type;
        this.encodeImage = encodeImage;
    }

    public String getEncodeImage() {
        return encodeImage;
    }
}
