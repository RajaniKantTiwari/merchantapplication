package com.app.merchant.ui.authentication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.app.merchant.R;
import com.app.merchant.databinding.ActivityLoginBinding;
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
    private String userName;

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
        if(isNotNull(response)){
            if(response instanceof LoginResponse){
                LoginResponse loginResponse=(LoginResponse)response;
                if(isNotNull(loginResponse)){
                    String type=loginResponse.getType();
                    if(type.equals(AppConstants.SUCCESS)){
                        PreferenceUtils.setUserName(userName);
                        PreferenceUtils.setUserMono(password);
                        Bundle bundle=new Bundle();
                        bundle.putString(BundleConstants.USER_NAME,userName);
                        bundle.putString(BundleConstants.MOBILE_NUMBER, password);
                        ExplicitIntent.getsInstance().navigateTo(this,VerifyAccountActivity.class,bundle);
                    }
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        if(view==mBinding.tvLogin){
            CommonUtility.clicked(mBinding.tvLogin);
            ExplicitIntent.getsInstance().navigateTo(this,DashBoardActivity.class);

           /*if(isValid()){
               if(isNetworkConnected()){
                   presenter.getLoginDetail(this,new LoginRequest(userName, password,
                           PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude()));
               }
           }*/
        }else if(view==mBinding.tvSignupForAccount){
            CommonUtility.clicked(mBinding.tvSignupForAccount);
            ExplicitIntent.getsInstance().navigateTo(this,SignupActivity.class);
        }else if(view==mBinding.forgotPassword){
            CommonUtility.clicked(mBinding.forgotPassword);
        }
    }

    private boolean isValid() {
        password =mBinding.edPassword.getText().toString();
        userName=mBinding.edName.getText().toString();
        if((isNotNull(password)&& password.trim().length()>0)&&(isNotNull(userName)&&userName.trim().length()>0)){
            return true;
        }else if(isNull(userName)||userName.trim().length()==0){
            showToast(getResources().getString(R.string.please_enter_address));
            return false;
        }else{
            showToast(getResources().getString(R.string.please_enter_password));
            return false;
        }

    }


}
