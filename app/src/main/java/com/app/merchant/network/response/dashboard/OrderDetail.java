package com.app.merchant.network.response.dashboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 25/03/18.
 */

public class OrderDetail implements Parcelable {
    private String id;
    private String invoiceNumber;
    private String userId;
    private String merchant_id;
    private String paymentStatus;
    private String invoiceDate;
    private String nextReminder;
    private String description;
    private String agreement_id;
    private String commission_amount;
    private String createdAt;
    private String invoiceID;
    private String subtotal;
    private String shipping;
    private String grandtotal;
    private String totalpaid;
    private String totalrefund;
    private String totaldue;
    private String ratings;
    private String order_status;
    private String total_time_to_deliver;
    private String entrytype;
    private String FYID;
    private String prev_balance;
    private String discount_amount;
    private String discount_code;
    private String parentorderID;
    private String delivertype;
    private String delivery_address;
    private String username;
    private String auth_key;
    private String access_token;
    private String password_hash;
    private String password_reset_token;
    private String oauth_client;
    private String oauth_client_user_id;
    private String email;
    private String status;
    private String mobile;
    private String otp;
    private String address;
    private String city;
    private String landmark;
    private String pincode;
    private String lat;
    private String lng;
    private String created_at;
    private String updated_at;
    private String logged_at;
    private String user_id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String avatar_path;
    private String avatar_base_url;
    private String locale;
    private String gender;
    private String phone;
    private String facebook_id;
    private String linkedin_id;
    private String orderitemsid;
    private String customer_order_id;
    private String merchantproductid;
    private String masterproductid;
    private String quantity;
    private String unitsellingprice;
    private String tax_collected;
    private String merchant_actual_selling_price;
    private String total_cost_to_customer;
    private String comission_amount;
    private String isactive;
    private String productname;
    private String productdescription;
    private String avg_price;
    private String mrp;
    private String manufacture;
    private String colorcode;
    private String icon;
    private String measure;
    private String producttype;
    private String manufacturerID;
    private String cat_id;
    private String subcat_id;
    private String added_by;
    private String added_id;
    private String approved_at;
    private String remark;

    public OrderDetail() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getNextReminder() {
        return nextReminder;
    }

    public void setNextReminder(String nextReminder) {
        this.nextReminder = nextReminder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgreement_id() {
        return agreement_id;
    }

    public void setAgreement_id(String agreement_id) {
        this.agreement_id = agreement_id;
    }

    public String getCommission_amount() {
        return commission_amount;
    }

    public void setCommission_amount(String commission_amount) {
        this.commission_amount = commission_amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getTotalpaid() {
        return totalpaid;
    }

    public void setTotalpaid(String totalpaid) {
        this.totalpaid = totalpaid;
    }

    public String getTotalrefund() {
        return totalrefund;
    }

    public void setTotalrefund(String totalrefund) {
        this.totalrefund = totalrefund;
    }

    public String getTotaldue() {
        return totaldue;
    }

    public void setTotaldue(String totaldue) {
        this.totaldue = totaldue;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getTotal_time_to_deliver() {
        return total_time_to_deliver;
    }

    public void setTotal_time_to_deliver(String total_time_to_deliver) {
        this.total_time_to_deliver = total_time_to_deliver;
    }

    public String getEntrytype() {
        return entrytype;
    }

    public void setEntrytype(String entrytype) {
        this.entrytype = entrytype;
    }

    public String getFYID() {
        return FYID;
    }

    public void setFYID(String FYID) {
        this.FYID = FYID;
    }

    public String getPrev_balance() {
        return prev_balance;
    }

    public void setPrev_balance(String prev_balance) {
        this.prev_balance = prev_balance;
    }

    public String getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(String discount_amount) {
        this.discount_amount = discount_amount;
    }

    public String getDiscount_code() {
        return discount_code;
    }

    public void setDiscount_code(String discount_code) {
        this.discount_code = discount_code;
    }

    public String getParentorderID() {
        return parentorderID;
    }

    public void setParentorderID(String parentorderID) {
        this.parentorderID = parentorderID;
    }

    public String getDelivertype() {
        return delivertype;
    }

    public void setDelivertype(String delivertype) {
        this.delivertype = delivertype;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_reset_token() {
        return password_reset_token;
    }

    public void setPassword_reset_token(String password_reset_token) {
        this.password_reset_token = password_reset_token;
    }

    public String getOauth_client() {
        return oauth_client;
    }

    public void setOauth_client(String oauth_client) {
        this.oauth_client = oauth_client;
    }

    public String getOauth_client_user_id() {
        return oauth_client_user_id;
    }

    public void setOauth_client_user_id(String oauth_client_user_id) {
        this.oauth_client_user_id = oauth_client_user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
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

    public String getLogged_at() {
        return logged_at;
    }

    public void setLogged_at(String logged_at) {
        this.logged_at = logged_at;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAvatar_path() {
        return avatar_path;
    }

    public void setAvatar_path(String avatar_path) {
        this.avatar_path = avatar_path;
    }

    public String getAvatar_base_url() {
        return avatar_base_url;
    }

    public void setAvatar_base_url(String avatar_base_url) {
        this.avatar_base_url = avatar_base_url;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getLinkedin_id() {
        return linkedin_id;
    }

    public void setLinkedin_id(String linkedin_id) {
        this.linkedin_id = linkedin_id;
    }

    public String getOrderitemsid() {
        return orderitemsid;
    }

    public void setOrderitemsid(String orderitemsid) {
        this.orderitemsid = orderitemsid;
    }

    public String getCustomer_order_id() {
        return customer_order_id;
    }

    public void setCustomer_order_id(String customer_order_id) {
        this.customer_order_id = customer_order_id;
    }

    public String getMerchantproductid() {
        return merchantproductid;
    }

    public void setMerchantproductid(String merchantproductid) {
        this.merchantproductid = merchantproductid;
    }

    public String getMasterproductid() {
        return masterproductid;
    }

    public void setMasterproductid(String masterproductid) {
        this.masterproductid = masterproductid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitsellingprice() {
        return unitsellingprice;
    }

    public void setUnitsellingprice(String unitsellingprice) {
        this.unitsellingprice = unitsellingprice;
    }

    public String getTax_collected() {
        return tax_collected;
    }

    public void setTax_collected(String tax_collected) {
        this.tax_collected = tax_collected;
    }

    public String getMerchant_actual_selling_price() {
        return merchant_actual_selling_price;
    }

    public void setMerchant_actual_selling_price(String merchant_actual_selling_price) {
        this.merchant_actual_selling_price = merchant_actual_selling_price;
    }

    public String getTotal_cost_to_customer() {
        return total_cost_to_customer;
    }

    public void setTotal_cost_to_customer(String total_cost_to_customer) {
        this.total_cost_to_customer = total_cost_to_customer;
    }

    public String getComission_amount() {
        return comission_amount;
    }

    public void setComission_amount(String comission_amount) {
        this.comission_amount = comission_amount;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(String avg_price) {
        this.avg_price = avg_price;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
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

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    protected OrderDetail(Parcel in) {
        id = in.readString();
        invoiceNumber = in.readString();
        userId = in.readString();
        merchant_id = in.readString();
        paymentStatus = in.readString();
        invoiceDate = in.readString();
        nextReminder = in.readString();
        description = in.readString();
        agreement_id = in.readString();
        commission_amount = in.readString();
        createdAt = in.readString();
        invoiceID = in.readString();
        subtotal = in.readString();
        shipping = in.readString();
        grandtotal = in.readString();
        totalpaid = in.readString();
        totalrefund = in.readString();
        totaldue = in.readString();
        ratings = in.readString();
        order_status = in.readString();
        total_time_to_deliver = in.readString();
        entrytype = in.readString();
        FYID = in.readString();
        prev_balance = in.readString();
        discount_amount = in.readString();
        discount_code = in.readString();
        parentorderID = in.readString();
        delivertype = in.readString();
        delivery_address = in.readString();
        username = in.readString();
        auth_key = in.readString();
        access_token = in.readString();
        password_hash = in.readString();
        password_reset_token = in.readString();
        oauth_client = in.readString();
        oauth_client_user_id = in.readString();
        email = in.readString();
        status = in.readString();
        mobile = in.readString();
        otp = in.readString();
        address = in.readString();
        city = in.readString();
        landmark = in.readString();
        pincode = in.readString();
        lat = in.readString();
        lng = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        logged_at = in.readString();
        user_id = in.readString();
        firstname = in.readString();
        middlename = in.readString();
        lastname = in.readString();
        avatar_path = in.readString();
        avatar_base_url = in.readString();
        locale = in.readString();
        gender = in.readString();
        phone = in.readString();
        facebook_id = in.readString();
        linkedin_id = in.readString();
        orderitemsid = in.readString();
        customer_order_id = in.readString();
        merchantproductid = in.readString();
        masterproductid = in.readString();
        quantity = in.readString();
        unitsellingprice = in.readString();
        tax_collected = in.readString();
        merchant_actual_selling_price = in.readString();
        total_cost_to_customer = in.readString();
        comission_amount = in.readString();
        isactive = in.readString();
        productname = in.readString();
        productdescription = in.readString();
        avg_price = in.readString();
        mrp = in.readString();
        manufacture = in.readString();
        colorcode = in.readString();
        icon = in.readString();
        measure = in.readString();
        producttype = in.readString();
        manufacturerID = in.readString();
        cat_id = in.readString();
        subcat_id = in.readString();
        added_by = in.readString();
        added_id = in.readString();
        approved_at = in.readString();
        remark = in.readString();
    }

    public static final Creator<OrderDetail> CREATOR = new Creator<OrderDetail>() {
        @Override
        public OrderDetail createFromParcel(Parcel in) {
            return new OrderDetail(in);
        }

        @Override
        public OrderDetail[] newArray(int size) {
            return new OrderDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(invoiceNumber);
        parcel.writeString(userId);
        parcel.writeString(merchant_id);
        parcel.writeString(paymentStatus);
        parcel.writeString(invoiceDate);
        parcel.writeString(nextReminder);
        parcel.writeString(description);
        parcel.writeString(agreement_id);
        parcel.writeString(commission_amount);
        parcel.writeString(createdAt);
        parcel.writeString(invoiceID);
        parcel.writeString(subtotal);
        parcel.writeString(shipping);
        parcel.writeString(grandtotal);
        parcel.writeString(totalpaid);
        parcel.writeString(totalrefund);
        parcel.writeString(totaldue);
        parcel.writeString(ratings);
        parcel.writeString(order_status);
        parcel.writeString(total_time_to_deliver);
        parcel.writeString(entrytype);
        parcel.writeString(FYID);
        parcel.writeString(prev_balance);
        parcel.writeString(discount_amount);
        parcel.writeString(discount_code);
        parcel.writeString(parentorderID);
        parcel.writeString(delivertype);
        parcel.writeString(delivery_address);
        parcel.writeString(username);
        parcel.writeString(auth_key);
        parcel.writeString(access_token);
        parcel.writeString(password_hash);
        parcel.writeString(password_reset_token);
        parcel.writeString(oauth_client);
        parcel.writeString(oauth_client_user_id);
        parcel.writeString(email);
        parcel.writeString(status);
        parcel.writeString(mobile);
        parcel.writeString(otp);
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(landmark);
        parcel.writeString(pincode);
        parcel.writeString(lat);
        parcel.writeString(lng);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeString(logged_at);
        parcel.writeString(user_id);
        parcel.writeString(firstname);
        parcel.writeString(middlename);
        parcel.writeString(lastname);
        parcel.writeString(avatar_path);
        parcel.writeString(avatar_base_url);
        parcel.writeString(locale);
        parcel.writeString(gender);
        parcel.writeString(phone);
        parcel.writeString(facebook_id);
        parcel.writeString(linkedin_id);
        parcel.writeString(orderitemsid);
        parcel.writeString(customer_order_id);
        parcel.writeString(merchantproductid);
        parcel.writeString(masterproductid);
        parcel.writeString(quantity);
        parcel.writeString(unitsellingprice);
        parcel.writeString(tax_collected);
        parcel.writeString(merchant_actual_selling_price);
        parcel.writeString(total_cost_to_customer);
        parcel.writeString(comission_amount);
        parcel.writeString(isactive);
        parcel.writeString(productname);
        parcel.writeString(productdescription);
        parcel.writeString(avg_price);
        parcel.writeString(mrp);
        parcel.writeString(manufacture);
        parcel.writeString(colorcode);
        parcel.writeString(icon);
        parcel.writeString(measure);
        parcel.writeString(producttype);
        parcel.writeString(manufacturerID);
        parcel.writeString(cat_id);
        parcel.writeString(subcat_id);
        parcel.writeString(added_by);
        parcel.writeString(added_id);
        parcel.writeString(approved_at);
        parcel.writeString(remark);
    }
}
