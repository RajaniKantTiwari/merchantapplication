package com.app.merchant.utility;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.app.merchant.network.response.dashboard.cart.Product;
import com.orhanobut.hawk.Hawk;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by arvind on 01/11/17.
 */

public class PreferenceUtils {
    public PreferenceUtils() {
    }

    public static void setAuthToken(String token) {
        Hawk.put(PreferenceConstants.TOKEN, token);
    }

    public static String getAuthToken() {
        return Hawk.get(PreferenceConstants.TOKEN);

    }

    public static void setUserName(String userName) {
        Hawk.put(PreferenceConstants.USER_NAME, userName);
    }

    public static String getUserName() {
        return Hawk.get(PreferenceConstants.USER_NAME, null);
    }

    public static void setEmail(String email) {
        Hawk.put(PreferenceConstants.EMAIL, email);
    }

    public static String getEmail() {
        return Hawk.get(PreferenceConstants.EMAIL, null);
    }

    public static void setPaymentOption(String paymentOption) {
        Hawk.put(PreferenceConstants.PAYMENT_OPTION, paymentOption);
    }

    public static String getPaymentOption() {
        return Hawk.get(PreferenceConstants.PAYMENT_OPTION, null);
    }

    public static void setCardNumber(String cardNumber) {
        Hawk.put(PreferenceConstants.CARD_NUMBER, cardNumber);
    }

    public static String getCardNumber() {
        return Hawk.get(PreferenceConstants.CARD_NUMBER, null);
    }

    public static void setLatitude(double latitude) {
        Hawk.put(PreferenceConstants.USER_LATITUDE, latitude);
    }

    public static void setLongitude(double longitude) {
        Hawk.put(PreferenceConstants.USER_LONGITUDE, longitude);
    }

    public static double getLatitude() {
        return Hawk.get(PreferenceConstants.USER_LATITUDE, 0.0);
    }

    public static double getLongitude() {
        return Hawk.get(PreferenceConstants.USER_LONGITUDE, 0.0);
    }

    public static void setDeviceToken(String token) {
        Hawk.put(PreferenceConstants.DEVICE_TOKEN, token);
    }

    public static String getDeviceToken() {
        return Hawk.get(PreferenceConstants.DEVICE_TOKEN, null);
    }

    public static void setImage(String imageUrl) {
        Hawk.put(PreferenceConstants.IMAGE_URL, imageUrl);
    }

    public static String getImage() {
        return Hawk.get(PreferenceConstants.IMAGE_URL, null);
    }

    public static void setLogin(boolean isLogin) {
        Hawk.put(PreferenceConstants.IS_LOGIN, isLogin);
    }

    public static boolean isLogin() {
        return Hawk.get(PreferenceConstants.IS_LOGIN, false);
    }


    public static void setAddress(String address) {
        Hawk.put(PreferenceConstants.ADDRESS, address);
    }

    public static String getAddress() {
        return Hawk.get(PreferenceConstants.ADDRESS, null);
    }

    public static String getAddress(Context context, double latitude, double longitude) {
        try {
            Geocoder geoCoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geoCoder.getFromLocation(latitude, longitude, 1);
            String address = "";
            if (addresses != null && addresses.size() >= 0) {
                if (addresses.get(0).getAddressLine(0) != null)
                    address = addresses.get(0).getAddressLine(0);

                if (addresses != null && addresses.size() >= 1) {
                    if (addresses.get(0).getAddressLine(1) != null)
                        address += ", " + addresses.get(0).getAddressLine(1);
                }

                if (addresses != null && addresses.size() >= 2) {
                    if (addresses.get(0).getAddressLine(2) != null)
                        address += ", " + addresses.get(0).getAddressLine(2);
                }
            }
            return address;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLocationCityName( double lat, double lon ){
        JSONObject result = getLocationFormGoogle(lat + "," + lon );
        return getCityAddress(result);
    }

    protected static JSONObject getLocationFormGoogle(String placesName) {

        String apiRequest = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + placesName+"&sensor=true";
        HttpGet httpGet = new HttpGet(apiRequest);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {

            e.printStackTrace();
        }

        return jsonObject;
    }

    protected static String getCityAddress( JSONObject result ){
        if( result.has("results") ){
            try {
                JSONArray array = result.getJSONArray("results");
                if( array.length() > 0 ){
                    JSONObject place = array.getJSONObject(0);
                    JSONArray components = place.getJSONArray("address_components");
                    String address="";
                    for( int i = 0 ; i < components.length() ; i++ ){
                        JSONObject component = components.getJSONObject(i);
                        String shortAdd = component.getString("long_name");
                        address=address+shortAdd;
                    }
                    return address;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void setCartData(ArrayList<Product> addCartList) {
        Hawk.put(getEmail(), addCartList);
    }

    public static ArrayList<Product> getCartData() {
        return Hawk.get(getEmail(), null);
    }

    public static void setUserMono(String userMono) {
        Hawk.put(PreferenceConstants.USER_MONO, userMono);

    }

    public static String getUserMono() {
        return Hawk.get(PreferenceConstants.USER_MONO, null);
    }

    public static void setMerchantId(int merchantId) {
        Hawk.put(PreferenceConstants.MERCHANT_ID, merchantId);
    }
    public static int getMerchantId() {
        return Hawk.get(PreferenceConstants.MERCHANT_ID, null);
    }
}
