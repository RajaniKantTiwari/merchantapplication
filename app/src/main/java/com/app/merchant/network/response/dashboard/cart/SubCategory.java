package com.app.merchant.network.response.dashboard.cart;

import java.util.ArrayList;

/**
 * Created by virender on 10/01/18.
 */

public class SubCategory {
    private int id;
    private String name;
    private boolean isSelected;
    private String colorcode="#ffffff";
    private ArrayList<ProductData> subproduct;

    public String getColorcode() {
        return colorcode;
    }

    public void setColorcode(String colorcode) {
        this.colorcode = colorcode;
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

    public ArrayList<ProductData> getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(ArrayList<ProductData> subproduct) {
        this.subproduct = subproduct;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
