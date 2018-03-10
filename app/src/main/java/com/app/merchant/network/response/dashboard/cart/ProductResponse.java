package com.app.merchant.network.response.dashboard.cart;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 26/01/18.
 */

public class ProductResponse implements Parcelable{
    private String productname;
    private String mrp;
    private int quantity;
    private int selling_price;
    private int product_mrp;
    private int tax;
    private int avg_time_to_deliver;
    private int shipping;

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public int getProduct_mrp() {
        return product_mrp;
    }

    public void setProduct_mrp(int product_mrp) {
        this.product_mrp = product_mrp;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getAvg_time_to_deliver() {
        return avg_time_to_deliver;
    }

    public void setAvg_time_to_deliver(int avg_time_to_deliver) {
        this.avg_time_to_deliver = avg_time_to_deliver;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    protected ProductResponse(Parcel in) {
        productname = in.readString();
        mrp = in.readString();
        quantity = in.readInt();
    }

    public static final Creator<ProductResponse> CREATOR = new Creator<ProductResponse>() {
        @Override
        public ProductResponse createFromParcel(Parcel in) {
            return new ProductResponse(in);
        }

        @Override
        public ProductResponse[] newArray(int size) {
            return new ProductResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productname);
        dest.writeString(mrp);
        dest.writeInt(quantity);
    }
}
