package com.app.merchant.ui.dashboard.drawer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.merchant.R;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.base.BaseActivity;

import javax.inject.Inject;


/**
 * Created by ashok on 13/11/17.
 */

public class HelpActivity extends BaseActivity {

    @Inject
    CommonPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this,R.layout.activity_help);
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {

    }
}
