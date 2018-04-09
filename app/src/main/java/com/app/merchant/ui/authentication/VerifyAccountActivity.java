package com.app.merchant.ui.authentication;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;


import com.app.merchant.MerchantApplication;
import com.app.merchant.R;
import com.app.merchant.databinding.ActivityVerifyAccountBinding;
import com.app.merchant.network.request.RegisterRequest;
import com.app.merchant.network.request.VerifyMobileRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.LoginResponse;
import com.app.merchant.network.response.VerifyMobileResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.dashboard.DashBoardActivity;
import com.app.merchant.ui.dialogfrag.CustomDialogFragment;
import com.app.merchant.ui.dialogfrag.OkDialogFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;
import com.app.merchant.utility.PreferenceUtils;

import javax.inject.Inject;

import io.fabric.sdk.android.services.common.CommonUtils;

/**
 * Created by on 23/12/17.
 */

public class VerifyAccountActivity extends CommonActivity implements TextWatcher, View.OnKeyListener,
        CustomDialogFragment.CustomDialogListener, OkDialogFragment.OkDialogListener {
    private ActivityVerifyAccountBinding mBinding;
    StringBuilder otpNumber = new StringBuilder();
    @Inject
    CommonPresenter presenter;
    private String mobileNumber;
    private String email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_verify_account);
        showKeyboard();
        initializeData();
        setListener();
    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    public void setListener() {
        mBinding.edFirst.addTextChangedListener(this);
        mBinding.edSecond.addTextChangedListener(this);
        mBinding.edThird.addTextChangedListener(this);
        mBinding.edFourth.addTextChangedListener(this);
        mBinding.edFirst.setOnKeyListener(this);
        mBinding.edSecond.setOnKeyListener(this);
        mBinding.edThird.setOnKeyListener(this);
        mBinding.edFourth.setOnKeyListener(this);
        mBinding.tvResend.setOnClickListener(this);
        mBinding.tvChange.setOnClickListener(this);
        mBinding.tvResend.setOnClickListener(this);
    }

    public void initializeData() {
        Intent intent = getIntent();
        if (isNotNull(intent)) {
            Bundle bundle = intent.getExtras();
            if (isNotNull(bundle)) {
                email = bundle.getString(BundleConstants.EMAIL);
                mobileNumber = bundle.getString(BundleConstants.MOBILE_NUMBER);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvResend) {
            CommonUtility.clicked(mBinding.tvResend);
            /*presenter.registerMerchant(this, new RegisterRequest(userName, mobileNumber,
                    PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude()));*/
        } else if (view == mBinding.tvChange) {
            //CommonUtils.clicked(mBinding.tvChange);
            Bundle bundle = new Bundle();
            bundle.putString(BundleConstants.TITLE, getResources().getString(R.string.please_enter_mobile_number));
            bundle.putBoolean(BundleConstants.VISIBLE, true);
            CommonUtility.showDialog(this, bundle, this);
        } else if (mBinding.tvResend == view) {
            presenter.verifyMobileNumber(this, new VerifyMobileRequest(mobileNumber, Integer.parseInt(otpNumber.toString())));
        } else if (mBinding.tvChange == view) {
            //CommonUtils.clicked(mBinding.tvChange);
            Bundle bundle = new Bundle();
            bundle.putString(AppConstants.TITLE, getResources().getString(R.string.please_enter_mobile_number));
            bundle.putBoolean(AppConstants.VISIBLE, true);
            CommonUtility.showDialog(this, bundle, this);
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (requestCode == 1) {
            hideSoftKeyboard(mBinding.getRoot());
            gotoNext(response);
        } else if (requestCode == 2) {
            getOtp(response);
        }
        if (!MerchantApplication.isDebug) {
            ExplicitIntent.getsInstance().navigateTo(this, DashBoardActivity.class);
        }
    }

    private void getOtp(BaseResponse response) {
        if (isNotNull(response)) {
            if (response instanceof LoginResponse) {
                LoginResponse loginResponse = (LoginResponse) response;
                if (isNotNull(loginResponse)) {
                    if (loginResponse.getStatus().equals(AppConstants.SUCCESS)) {
                        showToast(getResources().getString(R.string.otp_has_been_send));
                    }
                }
            }
        }
    }

    private void gotoNext(BaseResponse response) {
        try {
            PreferenceUtils.setUserMono(mobileNumber);
            if (isNotNull(response)) {
                if (response instanceof VerifyMobileResponse) {
                    VerifyMobileResponse verifyMobileResponse = (VerifyMobileResponse) response;
                    if (isNotNull(verifyMobileResponse)) {
                        String status = verifyMobileResponse.getStatus();
                        if (status.equals(AppConstants.SUCCESS)) {
                            PreferenceUtils.setAuthToken(verifyMobileResponse.getAuthkey());
                            PreferenceUtils.setEmail(email);
                            PreferenceUtils.setLogin(true);
                            CommonUtility.showOkDialog(this, this);
                        } else {
                            hideKeyboard();
                            showToast(getResources().getString(R.string.server_error));
                        }
                    }
                }
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    /*private void setToken() {
        DeviceTokenRequest request=new DeviceTokenRequest();
        request.setUserid(PreferenceUtils.getUserId());
        DeviceToken token=new DeviceToken();
        token.setDeveiceUniqId(CommonUtils.getDeviceUniqueId(this));
        token.setDeviceTokenId(PreferenceUtils.getDeviceToken());
        token.setDeviceType(GeneralConstant.DEVICETYPE);
        request.setInfo(token);
        presenter.setDeviceToken(this,request);
    }*/

    @Override
    public void onError(Throwable call, int requestCode) {
        showToast(call.getMessage());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.e("CharSequence", "" + otpNumber.length());
        if (otpNumber.length() == 0 && mBinding.edFirst.length() == 1) {
            otpNumber.append(s);
            mBinding.edFirst.clearFocus();
            mBinding.edSecond.requestFocus();
            mBinding.edSecond.setCursorVisible(true);
        } else if (otpNumber.length() == 1 && mBinding.edSecond.length() == 1) {
            otpNumber.append(s);
            mBinding.edSecond.clearFocus();
            mBinding.edThird.requestFocus();
            mBinding.edThird.setCursorVisible(true);
        } else if (otpNumber.length() == 2 && mBinding.edThird.length() == 1) {
            otpNumber.append(s);
            mBinding.edThird.clearFocus();
            mBinding.edFourth.requestFocus();
            mBinding.edFourth.setCursorVisible(true);
        } else if (otpNumber.length() == 3 && mBinding.edFourth.length() == 1) {
            otpNumber.append(s);
            mBinding.edThird.clearFocus();
            mBinding.edFourth.requestFocus();
            mBinding.edFourth.setCursorVisible(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (otpNumber.length() == 4) {
            presenter.verifyMobileNumber(this, new VerifyMobileRequest(mobileNumber, Integer.parseInt(otpNumber.toString())));
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (otpNumber.length() == 1) {
                mBinding.edSecond.clearFocus();
                mBinding.edFirst.requestFocus();
                otpNumber.deleteCharAt(0);
            } else if (otpNumber.length() == 2) {
                mBinding.edSecond.clearFocus();
                mBinding.edFirst.requestFocus();
                otpNumber.deleteCharAt(1);
            } else if (otpNumber.length() == 3) {
                mBinding.edThird.clearFocus();
                mBinding.edSecond.requestFocus();
                otpNumber.deleteCharAt(2);
            } else if (otpNumber.length() == 4) {
                mBinding.edFourth.clearFocus();
                mBinding.edThird.requestFocus();
                otpNumber.deleteCharAt(3);
            }
        }
        return false;
    }

    @Override
    public void ok(String str) {
        mobileNumber = str;
        /*presenter.registerMerchant(this, new RegisterRequest(userName, mobileNumber,
                PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude()));*/
        hideKeyboard();
    }

    @Override
    public void cancel() {

    }

    @Override
    public void next(String str) {
        hideSoftKeyboard();
        ExplicitIntent.getsInstance().clearPreviousNavigateTo(this, DashBoardActivity.class);
        finish();
    }
}
