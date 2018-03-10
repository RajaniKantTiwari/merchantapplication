package com.app.merchant.utility;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.app.merchant.BuildConfig;
import com.app.merchant.R;
import com.app.merchant.event.UpdateCartEvent;
import com.app.merchant.ui.authentication.LoginActivity;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashBoardActivity;
import com.app.merchant.ui.dialogfrag.ConfirmOrderDialogFragment;
import com.app.merchant.ui.dialogfrag.CustomDialogFragment;
import com.app.merchant.ui.dialogfrag.DeliveryBoyDialogFragment;
import com.app.merchant.ui.dialogfrag.RatingDialogFragment;
import com.app.merchant.widget.CustomEditText;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by arvind on 01/11/17.
 */

public class CommonUtility {
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private DateFormat newDateFormat;
    private static final String TAG=CommonUtility.class.getSimpleName();

    public static int convertDpToPx(int dp, Context context) {
        return Math.round(dp * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));

    }

    /**
     * Show toast message for long time
     *
     * @param context
     * @param msg
     */
    public static void showToastLong(Context context, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }

    //Check object is null
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static void showDialog(AppCompatActivity activity, Bundle bundle, CustomDialogFragment.CustomDialogListener listener) {
        FragmentManager fm = activity.getSupportFragmentManager();
        CustomDialogFragment alertdFragment = new CustomDialogFragment();
        alertdFragment.dialogListener(listener);
        alertdFragment.setArguments(bundle);
        // Show Alert ConfirmOrderDialogFragment
        alertdFragment.show(fm, "");
    }


    public static void showConfirmOrderDialog(AppCompatActivity activity, Bundle bundle, ConfirmOrderDialogFragment.OrderDialogListener listener) {
        FragmentManager fm = activity.getSupportFragmentManager();
        ConfirmOrderDialogFragment alertdFragment = new ConfirmOrderDialogFragment();
        alertdFragment.addListener(listener);
        alertdFragment.setArguments(bundle);
        // Show Alert ConfirmOrderDialogFragment
        alertdFragment.show(fm, "");
    }



    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static boolean checkService(BaseActivity activity) {
        if (!BuildConfig.DEBUG && CommonUtility.isSimulator()) {
            activity.showToast(activity.getResources().getString(R.string.google_service_not_present));
            return false;
        }
        if (!BuildConfig.DEBUG && !CommonUtility.checkGooglePlaySevices(activity)) {
            activity.showToast(activity.getResources().getString(R.string.google_service_not_present));
            return false;
        }
        return true;
    }

    public static boolean isSimulator() {
        boolean isSimulator = "google_sdk".equals(Build.PRODUCT)
                || "vbox86p".equals(Build.PRODUCT)
                || "sdk".equals(Build.PRODUCT);
        LogUtils.LOGD(TAG, "Build.PRODUCT= " + Build.PRODUCT + "  isSimulator= "
                + isSimulator);

        return isSimulator;
    }

    public static boolean checkGooglePlaySevices(final Activity activity) {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(activity);
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                Dialog dialog = googleAPI.getErrorDialog(activity, result,
                        PLAY_SERVICES_RESOLUTION_REQUEST);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
                dialog.show();
            }
            return false;
        }

        return true;
    }

    /**
     * Convert Time from milli to DD/MM/YYYY format
     *
     * @param millis
     * @return
     */
    public static String convertMilliToEEEMMMDYYYY(long millis) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d, yyyy hh:mm aaa");
        return formatter.format(new Date(millis));
    }

    public static String convertMilliToTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aaa");

        return formatter.format(new Date(time));
    }

    public static int getColor(Context context, int color) {
        return ContextCompat.getColor(context, color);
    }

    public static int getColor(String color) {
        return Color.parseColor(color);
    }

    public static void clicked(View view) {
        view.setEnabled(false);
        final Animation animation = new AlphaAnimation(1.0f, 0.6f);
        animation.setDuration(200);
        animation.setFillAfter(true);
        view.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Animation animation = new AlphaAnimation(0.6f, 1.0f);
                animation.setDuration(200);
                animation.setFillAfter(true);
                view.startAnimation(animation);
            }
        }, 200);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, 3000);
    }

    public static void setDialog(Dialog dialog) {
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
    }

    public static void setPadding(Dialog dialog, Activity activity) {
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, convertDpToPx(10, activity));
        dialog.getWindow().setBackgroundDrawable(inset);
    }

    public static void register(Fragment fragment) {
        if (!EventBus.getDefault().isRegistered(fragment)) {
            EventBus.getDefault().register(fragment);
        }
    }

    public static void register(Activity activity) {
        if (!EventBus.getDefault().isRegistered(activity)) {
            EventBus.getDefault().register(activity);
        }
    }

    public static void unregister(Fragment fragment) {
        if (EventBus.getDefault().isRegistered(fragment)) {
            EventBus.getDefault().unregister(fragment);
        }
    }

    public static void unregister(Activity activity) {
        if (EventBus.getDefault().isRegistered(activity)) {
            EventBus.getDefault().unregister(activity);
        }
    }


    public static String oneDecimalPlaceString(String str) {
        if (isNotNull(str) && str.length() > 0) {
            double value = Double.parseDouble(str);
            return String.format("%.1f", value);
        }
        return null;
    }

    public static String twoDecimalPlaceString(String str) {
        if (isNotNull(str) && str.length() > 0) {
            double value = Double.parseDouble(str);
            return String.format("%.2f", value);
        }
        return null;
    }

    public static String oneDecimalPlace(double number) {
        return String.format("%.1f", number);
    }

    public static String twoDecimalPlace(double number) {
        return String.format("%.2f", number);
    }

    public static String getStartEndDate(String startDate, String endDate) {
        StringBuilder date = new StringBuilder();
        date.append(changeStringIntoDate(startDate));
        date.append(" to ");
        date.append(changeStringIntoDate(endDate));
        return date.toString();
    }

    public static String addStartEndDate(String startDate, String endDate) {
        StringBuilder date = new StringBuilder();
        date.append(startDate);
        date.append(" to ");
        date.append(endDate);
        return date.toString();
    }

    public static String getDay(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(AppConstants.TIME_ZONE));
        Date date = null;
        try {
            date = sdf.parse(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        DateFormat outputFormat = new SimpleDateFormat("E");// the day of the week

        //DateFormat outputFormat = new SimpleDateFormat("EEEE");// the day of the week spelled out completely
        outputFormat.setTimeZone(timeZone);
        try {
            String formattedDate = outputFormat.format(date);
            return formattedDate;
        } catch (Exception ex) {
            ex.toString();
        }
        return null;
    }

    public static String changeStringIntoDate(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(AppConstants.TIME_ZONE));
        Date date = null;
        try {
            date = sdf.parse(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        DateFormat outputFormat = new SimpleDateFormat("hh:mm a");
        outputFormat.setTimeZone(timeZone);
        try {
            String formattedDate = outputFormat.format(date);
            return formattedDate;
        } catch (Exception ex) {
            ex.toString();
        }
        return null;
    }

    public static String addStrings(String first, String second) {
        String str = first + second;
        return str;
    }

    public static float setRating(String rating) {
        String str = String.format("%.2f", Double.parseDouble(rating));
        return Float.parseFloat(str);
    }

    public static void setDays(ArrayList<Days> daysArrayList) {
        Days day1 = new Days();
        day1.setNameOfDays("Sunday");
        daysArrayList.add(day1);

        Days day2 = new Days();
        day2.setNameOfDays("Monday");
        daysArrayList.add(day2);

        Days day3 = new Days();
        day3.setNameOfDays("Tuesday");
        daysArrayList.add(day3);

        Days day4 = new Days();
        day4.setNameOfDays("WednusDay");
        daysArrayList.add(day4);

        Days day5 = new Days();
        day5.setNameOfDays("Thursday");
        daysArrayList.add(day5);

        Days day6 = new Days();
        day6.setNameOfDays("Friday");
        daysArrayList.add(day6);

        Days day7 = new Days();
        day7.setNameOfDays("Saturday");
        daysArrayList.add(day7);
    }

    public static String getDeviceUniqueId(Activity activity) {
        return Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getCountryName(Context context, double lat, double lng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
        } catch (IOException ignored) {
            Address result;
            if (addresses != null && !addresses.isEmpty()) {
                return addresses.get(0).getCountryName();
            }
            return null;
        }
        return addresses.get(0).getCountryCode();
    }

    public static void setRecyclerViewHeight(RecyclerView recyclerView, List list, int height) {
        if (CommonUtility.isNotNull(list)) {
            ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
            params.height = CommonUtility.convertDpToPx(height, recyclerView.getContext()) * list.size();
            recyclerView.setLayoutParams(params);
        }
    }

    public static void logout(Activity activity) {
        PreferenceUtils.setDeviceToken(null);
        PreferenceUtils.setUserId(-1);
        PreferenceUtils.setAuthToken(null);
        PreferenceUtils.setLogin(false);
        ExplicitIntent.getsInstance().navigateTo(activity, LoginActivity.class);
    }

    public static void setVisibility(View layoutMain, View layoutNoData, boolean shouldMainVisible) {
        if (shouldMainVisible) {
            layoutMain.setVisibility(View.VISIBLE);
            layoutNoData.setVisibility(View.GONE);
        } else {
            layoutMain.setVisibility(View.GONE);
            layoutNoData.setVisibility(View.VISIBLE);
        }
    }

    /**
     * create multipart from file
     *
     * @param filePath
     * @return
     */
    public static MultipartBody.Part createMultipart(String filePath, String parameter) {
        if (TextUtils.isEmpty(filePath)) return null;
        File file = new File(filePath.replace("file:////", "/"));

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(parameter, file.getName(), requestFile);
    }

    public static void showCursorEnd(CustomEditText editText) {
        editText.setSelection(editText.getText().length());
    }

    public static boolean isShopOpen(String openTime, String closeTime) {
        long open = 0;
        long close = 0;
        final Date current = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone(AppConstants.TIME_ZONE));
        String date = dateFormat.format(current);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm aa");
        try {
            Date startTime = simpleDateFormat.parse(date + " " + openTime);
            Date endTime = simpleDateFormat.parse(date + " " + closeTime);
            open = current.getTime() - startTime.getTime();
            close = endTime.getTime() - current.getTime();
            //Log.e("OPEN_TIME", "Current " + current.getTime() + " Open   " + startTime.getTime() + " Close  " + endTime.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return open > 0 && close > 0;
    }

    public static String getCreatedDate(String date) {
        String createdDate=null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone(AppConstants.TIME_ZONE));
            Date cDate=dateFormat.parse(date);
            DateFormat newDateFormat = new SimpleDateFormat("hh:mm aa dd MMM yyyy");
            newDateFormat.setTimeZone(TimeZone.getTimeZone(AppConstants.TIME_ZONE));
            return newDateFormat.format(cDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return createdDate;
    }

    public static void showNewDeliveryDialog(AppCompatActivity activity, Bundle bundle, DeliveryBoyDialogFragment.DeliveryBoyDialogListener listener) {
        FragmentManager fm = activity.getSupportFragmentManager();
        DeliveryBoyDialogFragment alertdFragment = new DeliveryBoyDialogFragment();
        alertdFragment.addListener(listener);
        alertdFragment.setArguments(bundle);
        // Show Alert DeliveryBoyDialogFragment
        alertdFragment.show(fm, "");
    }
    public static void showRatingDialog(AppCompatActivity activity, Bundle bundle, RatingDialogFragment.RatingDialogListener listener) {
        FragmentManager fm = activity.getSupportFragmentManager();
        RatingDialogFragment alertdFragment = new RatingDialogFragment();
        alertdFragment.addListener(listener);
        alertdFragment.setArguments(bundle);
        // Show Alert CustomDialogFragment
        alertdFragment.show(fm, "");
    }
    public static void resetCart(DashBoardActivity dashBoardActivity) {
        PreferenceUtils.setCartData(null);
        dashBoardActivity.onUpdateCartEvent(new UpdateCartEvent());
    }
    public static String convertSecondsToHMmSs(long seconds) {
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", h, m, s);
        //return String.format("%d:%02d:%02d", h,m,s);
    }
    public static String price(String sellingPrice) {

        return sellingPrice + "/";
    }
    public static boolean checkValidEmail(String email){
        return AppConstants.EMAIL_PATTERN.matcher(email).matches();
    }
    public static boolean checkValidName(String name){
        return AppConstants.USERNAME_PATTERN.matcher(name).matches();
    }
    public static boolean checkValidPassword(String password){
        return AppConstants.PASSWORD_PATTERN.matcher(password).matches();
    }
    public static boolean checkValidAddress(String address){
        return AppConstants.ADDRESS_PATTERN.matcher(address).matches();
    }

    public static Bitmap getBitmap(String profilePicFilePath) {
       return BitmapFactory.decodeFile(profilePicFilePath);

    }
}
