package com.app.merchant.network.request;


import android.os.Parcel;
import android.os.Parcelable;

import com.app.merchant.network.request.dashboard.StoreImage;

import java.util.ArrayList;

public class RegisterRequest implements Parcelable {
    private String email;
    private String mobile;
    private String legalname;
    private String merchantname;
    private String pancard;
    private String gst;
    private String bankname;
    private String accountno;
    private String branch;
    private String ifsc;
    private String paymentmode;
    private String delivery;
    private String servicingarea;
    private String location;
    private String address;
    private String storetype;
    private String storeimage;
    private String facultyimage;
    private String ownerimage;
    private double lat;
    private double lng;
    private ArrayList<StoreImage> storeList;
    private ArrayList<String> otherServiceArea;
    private String password;

    public RegisterRequest() {

    }

    protected RegisterRequest(Parcel in) {
        email = in.readString();
        password=in.readString();
        mobile = in.readString();
        legalname = in.readString();
        merchantname = in.readString();
        gst = in.readString();
        bankname = in.readString();
        accountno = in.readString();
        branch = in.readString();
        ifsc = in.readString();
        paymentmode = in.readString();
        delivery = in.readString();
        servicingarea = in.readString();
        location = in.readString();
        address = in.readString();
        storetype = in.readString();
        storeimage = in.readString();
        facultyimage = in.readString();
        ownerimage = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
        pancard=in.readString();
        storeList = in.createTypedArrayList(StoreImage.CREATOR);
        otherServiceArea=in.createStringArrayList();
    }

    public static final Creator<RegisterRequest> CREATOR = new Creator<RegisterRequest>() {
        @Override
        public RegisterRequest createFromParcel(Parcel in) {
            return new RegisterRequest(in);
        }

        @Override
        public RegisterRequest[] newArray(int size) {
            return new RegisterRequest[size];
        }
    };

    public ArrayList<String> getOtherServiceArea() {
        return otherServiceArea;
    }

    public void setOtherServiceArea(ArrayList<String> otherServiceArea) {
        this.otherServiceArea = otherServiceArea;
    }

    public ArrayList<StoreImage> getStoreList() {
        return storeList;
    }

    public void setStoreList(ArrayList<StoreImage> storeList) {
        this.storeList = storeList;
    }

    public String getPancard() {
        return pancard;
    }

    public void setPancard(String pancard) {
        this.pancard = pancard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLegalname() {
        return legalname;
    }

    public void setLegalname(String legalname) {
        this.legalname = legalname;
    }

    public String getMerchantname() {
        return merchantname;
    }

    public void setMerchantname(String merchantname) {
        this.merchantname = merchantname;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getServicingarea() {
        return servicingarea;
    }

    public void setServicingarea(String servicingarea) {
        this.servicingarea = servicingarea;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStoretype() {
        return storetype;
    }

    public void setStoretype(String storetype) {
        this.storetype = storetype;
    }

    public String getStoreimage() {
        return storeimage;
    }

    public void setStoreimage(String storeimage) {
        this.storeimage = storeimage;
    }

    public String getFacultyimage() {
        return facultyimage;
    }

    public void setFacultyimage(String facultyimage) {
        this.facultyimage = facultyimage;
    }

    public String getOwnerimage() {
        return ownerimage;
    }

    public void setOwnerimage(String ownerimage) {
        this.ownerimage = ownerimage;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public void setPassword(String password) {
         this.password=password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(mobile);
        parcel.writeString(legalname);
        parcel.writeString(merchantname);
        parcel.writeString(gst);
        parcel.writeString(bankname);
        parcel.writeString(accountno);
        parcel.writeString(branch);
        parcel.writeString(ifsc);
        parcel.writeString(paymentmode);
        parcel.writeString(delivery);
        parcel.writeString(servicingarea);
        parcel.writeString(location);
        parcel.writeString(address);
        parcel.writeString(storetype);
        parcel.writeString(storeimage);
        parcel.writeString(facultyimage);
        parcel.writeString(ownerimage);
        parcel.writeDouble(lat);
        parcel.writeDouble(lng);
        parcel.writeString(pancard);
        parcel.writeTypedList(storeList);
        parcel.writeStringList(otherServiceArea);
    }


}
