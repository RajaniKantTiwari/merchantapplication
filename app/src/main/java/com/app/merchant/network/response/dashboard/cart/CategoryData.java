package com.app.merchant.network.response.dashboard.cart;


import android.os.Parcel;
import android.os.Parcelable;

import com.app.merchant.network.request.dashboard.StoreImage;

import java.util.ArrayList;

public class CategoryData implements Parcelable {
    private int id;
    private String name;
    private String description;
    private String colorcode;
    private String icons;
    private String icon_web;
    private String status;
    private ArrayList<SubCategory> subproduct;
    private boolean isSelected;

    public CategoryData() {

    }

    protected CategoryData(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        colorcode = in.readString();
        icons = in.readString();
        icon_web = in.readString();
        status = in.readString();
        isSelected = in.readByte() != 0;
        subproduct = in.createTypedArrayList(SubCategory.CREATOR);

    }

    public ArrayList<SubCategory> getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(ArrayList<SubCategory> subproduct) {
        this.subproduct = subproduct;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColorcode() {
        return colorcode;
    }

    public void setColorcode(String colorcode) {
        this.colorcode = colorcode;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public String getIcon_web() {
        return icon_web;
    }

    public void setIcon_web(String icon_web) {
        this.icon_web = icon_web;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public static final Creator<CategoryData> CREATOR = new Creator<CategoryData>() {
        @Override
        public CategoryData createFromParcel(Parcel in) {
            return new CategoryData(in);
        }

        @Override
        public CategoryData[] newArray(int size) {
            return new CategoryData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(colorcode);
        parcel.writeString(icons);
        parcel.writeString(icon_web);
        parcel.writeString(status);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
        parcel.writeTypedList(subproduct);

    }
}

