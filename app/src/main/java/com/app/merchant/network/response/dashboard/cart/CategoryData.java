package com.app.merchant.network.response.dashboard.cart;


import java.util.ArrayList;

public class CategoryData {
    private int id;
    private String name;
    private boolean isSelected;
    private String colorcode;
    private String icons;

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }



    public String getColorcode() {
        return colorcode;
    }

    public void setColorcode(String colorcode) {
        this.colorcode = colorcode;
    }

    private ArrayList<SubCategory> subproduct;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    public ArrayList<SubCategory> getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(ArrayList<SubCategory> subproduct) {
        this.subproduct = subproduct;
    }
}

