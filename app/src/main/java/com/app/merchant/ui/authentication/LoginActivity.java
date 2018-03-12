package com.app.merchant.ui.authentication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.app.merchant.R;
import com.app.merchant.databinding.ActivityLoginBinding;
import com.app.merchant.network.request.LoginRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.LoginResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.ui.dashboard.DashBoardActivity;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;
import com.app.merchant.utility.PreferenceUtils;

import javax.inject.Inject;

public class LoginActivity extends CommonActivity implements MvpView, View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    ActivityLoginBinding mBinding;
    @Inject
    CommonPresenter presenter;
    private String password;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setListener();
    }

    public void setListener() {
        mBinding.forgotPassword.setOnClickListener(this);
        mBinding.tvLogin.setOnClickListener(this);
        mBinding.tvSignupForAccount.setOnClickListener(this);
    }


    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (isNotNull(response)) {
            if (response instanceof LoginResponse) {
                LoginResponse loginResponse = (LoginResponse) response;
                if (isNotNull(loginResponse)) {
                    if (loginResponse.getStatus().equals(AppConstants.SUCCESS)) {
                         PreferenceUtils.setAuthToken(loginResponse.getAuthkey());

                    }
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvLogin) {
            CommonUtility.clicked(mBinding.tvLogin);
            if (isValid()) {
                if (isNetworkConnected()) {
                    presenter.loginMerchant(this, new LoginRequest(email, password));
                }
            }
        } else if (view == mBinding.tvSignupForAccount) {
            CommonUtility.clicked(mBinding.tvSignupForAccount);
            ExplicitIntent.getsInstance().navigateTo(this, RegisterActivity.class);
        } else if (view == mBinding.forgotPassword) {
            CommonUtility.clicked(mBinding.forgotPassword);
            ExplicitIntent.getsInstance().navigateTo(this, ForgotPasswordActivity.class);
        }
    }

    private boolean isValid() {
        password = mBinding.edPassword.getText().toString();
        email = mBinding.edEmail.getText().toString();
        if (isNull(email) || email.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_email_address));
            return false;
        } else if (!CommonUtility.checkValidEmail(email)) {
            showToast(getResources().getString(R.string.please_enter_valid_email));
            return false;
        } else if (isNull(password) || password.trim().length() == 0) {
            showToast(getResources().getString(R.string.please_enter_password));
            return false;
        }
        return true;
    }


}
