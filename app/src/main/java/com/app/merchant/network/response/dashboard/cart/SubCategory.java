package com.app.merchant.network.response.dashboard.cart;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by virender on 10/01/18.
 */

public class SubCategory implements Parcelable {
    private int id;
    private String name;
    private String description;
    private String product_id;
    private String status;
    private String created_at;
    private boolean isSelected;
    private String colorcode = "#ffffff";
    private ArrayList<Product> subproduct;

    public SubCategory() {

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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getColorcode() {
        return colorcode;
    }

    public void setColorcode(String colorcode) {
        this.colorcode = colorcode;
    }

    public ArrayList<Product> getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(ArrayList<Product> subproduct) {
        this.subproduct = subproduct;
    }

    protected SubCategory(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        product_id = in.readString();
        status = in.readString();
        created_at = in.readString();
        isSelected = in.readByte() != 0;
        colorcode = in.readString();
        subproduct = in.createTypedArrayList(Product.CREATOR);
    }

    public static final Creator<SubCategory> CREATOR = new Creator<SubCategory>() {
        @Override
        public SubCategory createFromParcel(Parcel in) {
            return new SubCategory(in);
        }

        @Override
        public SubCategory[] newArray(int size) {
            return new SubCategory[size];
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
        parcel.writeString(product_id);
        parcel.writeString(status);
        parcel.writeString(created_at);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
        parcel.writeString(colorcode);
        parcel.writeTypedList(subproduct);
    }
}
