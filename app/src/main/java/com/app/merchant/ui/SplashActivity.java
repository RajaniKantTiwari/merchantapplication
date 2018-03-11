package com.app.merchant.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.merchant.MerchantApplication;
import com.app.merchant.R;
import com.app.merchant.ui.authentication.LoginActivity;
import com.app.merchant.ui.authentication.StoreDetailsActivity;
import com.app.merchant.ui.authentication.VerifyAccountActivity;
import com.app.merchant.ui.dashboard.DashBoardActivity;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.ExplicitIntent;
import com.app.merchant.utility.PreferenceUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(MerchantApplication.isDebug){
                    ExplicitIntent.getsInstance().navigateTo(SplashActivity.this, DashBoardActivity.class);
                }else{
                    if (PreferenceUtils.isLogin()) {
                        ExplicitIntent.getsInstance().navigateTo(SplashActivity.this, DashBoardActivity.class);
                    } else {
                        ExplicitIntent.getsInstance().navigateTo(SplashActivity.this, LoginActivity.class);

                    }
                }
                finish();
            }
        }, AppConstants.SPLASH_TIME);
    }
}
