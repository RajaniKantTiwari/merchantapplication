package com.app.merchant.network.response.dashboard.cart;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by virender on 10/01/18.
 */

public class Product implements Parcelable{
    private int id;
    private String productname;
    private String manufacture;
    private String producttype;
    private String avg_price;
    private String created_at;
    private String updated_at;
    private String isactive;
    private String manufacturerID;
    private String cat_id;
    private String subcat_id;
    private String added_by;
    private String added_id;
    private String approved_at;
    private String approved_by;
    private String remark;
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





    public Product() {
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

    public String getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(String avg_price) {
        this.avg_price = avg_price;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(String manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getSubcat_id() {
        return subcat_id;
    }

    public void setSubcat_id(String subcat_id) {
        this.subcat_id = subcat_id;
    }

    public String getAdded_by() {
        return added_by;
    }

    public void setAdded_by(String added_by) {
        this.added_by = added_by;
    }

    public String getAdded_id() {
        return added_id;
    }

    public void setAdded_id(String added_id) {
        this.added_id = added_id;
    }

    public String getApproved_at() {
        return approved_at;
    }

    public void setApproved_at(String approved_at) {
        this.approved_at = approved_at;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

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

    protected Product(Parcel in) {
        id = in.readInt();
        productname = in.readString();
        manufacture = in.readString();
        producttype = in.readString();
        avg_price = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        isactive = in.readString();
        manufacturerID = in.readString();
        cat_id = in.readString();
        subcat_id = in.readString();
        added_by = in.readString();
        added_id = in.readString();
        approved_at = in.readString();
        approved_by = in.readString();
        remark = in.readString();
        product_mrp = in.readFloat();
        selling_price = in.readString();
        qty = in.readInt();
        measure = in.readString();
        colorcode = in.readString();
        icon = in.readString();
        merchantlistid = in.readInt();
        masterproductid = in.readInt();
        merchantId = in.readInt();
        imagepath = in.readString();
        productdescription = in.readString();
        mrp = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(productname);
        parcel.writeString(manufacture);
        parcel.writeString(producttype);
        parcel.writeString(avg_price);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeString(isactive);
        parcel.writeString(manufacturerID);
        parcel.writeString(cat_id);
        parcel.writeString(subcat_id);
        parcel.writeString(added_by);
        parcel.writeString(added_id);
        parcel.writeString(approved_at);
        parcel.writeString(approved_by);
        parcel.writeString(remark);
        parcel.writeFloat(product_mrp);
        parcel.writeString(selling_price);
        parcel.writeInt(qty);
        parcel.writeString(measure);
        parcel.writeString(colorcode);
        parcel.writeString(icon);
        parcel.writeInt(merchantlistid);
        parcel.writeInt(masterproductid);
        parcel.writeInt(merchantId);
        parcel.writeString(imagepath);
        parcel.writeString(productdescription);
        parcel.writeString(mrp);
    }
}

