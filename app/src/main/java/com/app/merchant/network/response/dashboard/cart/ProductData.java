package com.app.merchant.network.response.dashboard.cart;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by virender on 10/01/18.
 */

public class ProductData implements Parcelable{
    private int id;
    private String productname;
    private String manufacture;
    private String producttype;
    private float product_mrp;
    private String selling_price;
    private int qty;
    private String measure;
    private String colorcode;
    private String icon;
    private int merchantlistid;
    private int masterproductid;
    private int merchantId;
    private String imagepath;
    private String productdescription;
    private String mrp;

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public int getMerchantlistid() {
        return merchantlistid;
    }

    public void setMerchantlistid(int merchantlistid) {
        this.merchantlistid = merchantlistid;
    }

    public int getMasterproductid() {
        return masterproductid;
    }

    public void setMasterproductid(int masterproductid) {
        this.masterproductid = masterproductid;
    }

    public ProductData() {
    }

    protected ProductData(Parcel in) {
        id = in.readInt();
        productname = in.readString();
        manufacture = in.readString();
        producttype = in.readString();
        product_mrp = in.readFloat();
        selling_price = in.readString();
        qty = in.readInt();
        measure = in.readString();
        colorcode = in.readString();
        icon = in.readString();
        merchantId=in.readInt();
        imagepath=in.readString();
        productdescription=in.readString();
        mrp=in.readString();
    }

    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @Override
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

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

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public float getProduct_mrp() {
        return product_mrp;
    }

    public void setProduct_mrp(float product_mrp) {
        this.product_mrp = product_mrp;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(productname);
        dest.writeString(manufacture);
        dest.writeString(producttype);
        dest.writeFloat(product_mrp);
        dest.writeString(selling_price);
        dest.writeInt(qty);
        dest.writeString(measure);
        dest.writeString(colorcode);
        dest.writeString(icon);
        dest.writeInt(merchantId);

        dest.writeString(imagepath);
        dest.writeString(productdescription);
        dest.writeString(mrp);
    }


}

