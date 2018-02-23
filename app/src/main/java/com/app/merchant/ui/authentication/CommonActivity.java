package com.app.merchant.ui.authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.merchant.MerchantApplication;
import com.app.merchant.injector.component.CommonComponent;
import com.app.merchant.injector.component.DaggerCommonComponent;
import com.app.merchant.injector.module.CommonModule;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.base.MvpView;


/**
 * Created by arvind on 01/11/17.
 */
/*
Parent Activity of Authorization to give functionality to all Activity
*/
public abstract class CommonActivity extends BaseActivity implements MvpView {
    private CommonComponent mActivityComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent= DaggerCommonComponent.builder().
                commonModule(new CommonModule(this)).
                applicationComponent(((MerchantApplication) getApplication()).
                        getApplicationComponent()).build();
        attachView();
    }

    public CommonComponent getActivityComponent() {
        return mActivityComponent;
    }
}