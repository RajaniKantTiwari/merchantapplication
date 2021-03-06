package com.app.merchant.utility;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.app.merchant.utility.AppConstants.FRAGMENTS.NOTIFICATION_FRAGMENT;
import static com.app.merchant.utility.AppConstants.FRAGMENTS.OFFER_FRAGMENT;
import static com.app.merchant.utility.AppConstants.FRAGMENTS.USER_FRAGMENT;
import static com.app.merchant.utility.AppConstants.FRAGMENTS.WELCOME_HOME_FRAGMENT;

/**
 * Created by arvind on 01/11/17.
 */

public interface AppConstants {
    String AUTHORIZATION = "Authorization";
    String SUCCESS = "success";
    String FORBIDDEN = "403";
    String TIME_ZONE="GMT";
    String PROFILE_UPDATE_PARAMETER = "profileUrl";
    String OFFER = "offer";

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

    @IntDef({WELCOME_HOME_FRAGMENT,OFFER_FRAGMENT,NOTIFICATION_FRAGMENT,USER_FRAGMENT})
    @Retention(RetentionPolicy.SOURCE)
    @interface FRAGMENTS {
        int WELCOME_HOME_FRAGMENT = 0;
        int OFFER_FRAGMENT=1;
        int NOTIFICATION_FRAGMENT=2;
        int USER_FRAGMENT=3;

    }
}
