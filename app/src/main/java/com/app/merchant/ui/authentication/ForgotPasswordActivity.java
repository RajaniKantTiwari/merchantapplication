package com.app.merchant.ui.authentication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.app.merchant.R;
import com.app.merchant.databinding.ForgotPasswordBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.ui.dashboard.DashBoardActivity;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.ExplicitIntent;

import javax.inject.Inject;

public class ForgotPasswordActivity extends CommonActivity implements MvpView, View.OnClickListener {
    private static final String TAG = ForgotPasswordActivity.class.getSimpleName();
    ForgotPasswordBinding mBinding;
    @Inject
    CommonPresenter presenter;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.forgot_password);
        setListener();
    }

    public void setListener() {
        mBinding.tvReset.setOnClickListener(this);
    }


    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {


    }

    @Override
    public void onClick(View view) {
        if(view==mBinding.tvReset) {
            CommonUtility.clicked(mBinding.tvReset);
            ExplicitIntent.getsInstance().clearPreviousNavigateTo(this, LoginActivity.class);
            if (isValid()) {
                if (isNetworkConnected()) {
                    //presenter.resetPassword(this, new LoginRequest(email, password));
                }
            }
        }
    }

    private boolean isValid() {
        email =mBinding.edEmail.getText().toString();
        if(isNull(email)|| email.trim().length()==0){
            showToast(getResources().getString(R.string.please_enter_email_address));
            return false;
        }else if(!CommonUtility.checkValidEmail(email)){
            showToast(getResources().getString(R.string.please_enter_valid_email));
            return false;
        }
        return true;
    }


}
