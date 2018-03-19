package com.app.merchant.utility;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Pattern;

import static com.app.merchant.utility.AppConstants.FRAGMENTS.NOTIFICATION_FRAGMENT;
import static com.app.merchant.utility.AppConstants.FRAGMENTS.OFFER_FRAGMENT;
import static com.app.merchant.utility.AppConstants.FRAGMENTS.USER_FRAGMENT;
import static com.app.merchant.utility.AppConstants.FRAGMENTS.WELCOME_HOME_FRAGMENT;

/**
 * Created by arvind on 01/11/17.
 */

public interface AppConstants {
    Pattern EMAIL_PATTERN = Pattern
            .compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");

    Pattern USERNAME_PATTERN = Pattern
            .compile("[a-zA-Z0-9]{1,250}");
    Pattern NAME_PATTERN = Pattern
            .compile("[a-zA-Z0-9]{1,250}");
    Pattern LAST_NAME_PATTERN = Pattern
            .compile("[a-zA-Z0-9]{1,250}");
    Pattern PASSWORD_PATTERN = Pattern
            .compile("[a-zA-Z0-9+_.]{4,16}");
    Pattern CONFIRM_PASSWORD_PATTERN = Pattern
            .compile("[a-zA-Z0-9+_.]{4,16}");
    Pattern ADDRESS_PATTERN=Pattern.compile("[A-Za-z0-9'\\.\\-\\s\\,]");

    String AUTHORIZATION = "Authorization";
    String SUCCESS = "success";
    String FORBIDDEN = "403";
    String TIME_ZONE="GMT";
    String PROFILE_UPDATE_PARAMETER = "profileUrl";
    String OFFER = "offer";
    int MY_ORDER = 1;
    int MY_INVENTORY = 2;
    int LOGOUT = 101;
    String DEVICETYPE="1";
    int NO_OF_TAB = 4;
    int DEVICE_TOKEN_RESPONSE=1;
    int HOME =0;
    int MYWARANTY = 1;
    int INSURANCE = 2;
    int EDIT_PROFILE = 3;
    int HELP = 4;
    int SENIOR_CITIZEN = 5;
    int TERM_CONDITION = 6;
    int PAYMENT_HEIGHT = 48;
    long SPLASH_TIME = 800;
    int CORNER_RADIUS = 10;
    int CARTADDED = 10001;
    String MERCHANT_ID = "merchant_id";
    String MERCHANT_IMAGE = "merchant_image";
    String MERCHANT_ADDRESS = "merchant_address";
    String MERCHANT_BACKGROUND_COLOR = "merchant_background_color";
    String PRODUCT_DATA = "product_data";
    String POSITION = "position";
    int VIEW_CART=1;
    int CHECKOUT=2;
    long COUNT_INTERVAL = 1000;
    int STORE_IMAGE=0;
    int FACULTY_IMAGE=1;
    int OWNER_IMAGE=2;
    int CHART_DATA =1;
    int ORDER_DATA =2;
    int DELIVERY_BOY_DATA = 10091;
    int SELECT_MANUALLY = 999;
    int PERMISSIONS_REQUEST_LOCATION = 99;
    int SCANNER_RESULT = 222;
    int PRODUCT_INVENTORY = 1;
    int ALL_PERFORMANCE = 2;
    int ALL_ORDER = 3;
    long API_SERVICE = 500;

    String FIREBASE_BASE_URL = "https://hamaradost-196011.firebaseio.com/";
    String FIREBASE_USER = "/users.json";
    String FIREBASE_MESSAGE = "/messages/";
    int VIEW_TYPE_USER_MESSAGE = 0;
    int VIEW_TYPE_OTHERS_MESSAGE = 1;
    String CHAT_WITH = "chat_with";
    String CHAT_USER_NAME = "chat_user_name";

    @IntDef({WELCOME_HOME_FRAGMENT,OFFER_FRAGMENT,NOTIFICATION_FRAGMENT,USER_FRAGMENT})
    @Retention(RetentionPolicy.SOURCE)
    @interface FRAGMENTS {
        int MY_INVENYORY_FRAGMENT = 16;
        int MY_ORDER_FRAGMENT = 17;
        int WELCOME_HOME_FRAGMENT = 0;
        int OFFER_FRAGMENT=1;
        int NOTIFICATION_FRAGMENT=2;
        int USER_FRAGMENT=3;
        int PRODUCT_INVENTORY_FRAGMENT=5;
        int ALL_PERFORMANCE=6;

    }
}
