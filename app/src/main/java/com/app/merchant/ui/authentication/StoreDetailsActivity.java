package com.app.merchant.ui.authentication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.app.merchant.R;
import com.app.merchant.databinding.ActivityLoginBinding;
import com.app.merchant.databinding.ActivityStoreDetailsBinding;
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

public class StoreDetailsActivity extends CommonActivity implements MvpView, View.OnClickListener {
    private static final String TAG = StoreDetailsActivity.class.getSimpleName();
    ActivityStoreDetailsBinding mBinding;
    @Inject
    CommonPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_store_details);
        setListener();
    }

    public void setListener() {
        mBinding.tvDone.setOnClickListener(this);
        mBinding.layoutPayment.layoutCash.setOnClickListener(this);
        mBinding.layoutPayment.layoutCard.setOnClickListener(this);
        mBinding.layoutPayment.layoutPaytm.setOnClickListener(this);
        mBinding.layoutPayment.layoutOther.setOnClickListener(this);

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

                    }
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        if(view==mBinding.tvDone){
            CommonUtility.clicked(mBinding.tvDone);
            ExplicitIntent.getsInstance().navigateTo(this,DashBoardActivity.class);
           /*if(isValid()){
               if(isNetworkConnected()){
                   presenter.getLoginDetail(this,new LoginRequest(userName, password,
                           PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude()));
               }
           }*/
        }else if(view==mBinding.layoutPayment.layoutCash){
            selectCash();
        }else if(view==mBinding.layoutPayment.layoutCard){
            selectCard();
        }else if(view==mBinding.layoutPayment.layoutPaytm){
            selectPaytm();
        }else if(view==mBinding.layoutPayment.layoutOther){
            selectOther();
        }
    }

    private void selectOther() {
        mBinding.layoutPayment.radioCash.setChecked(false);
        mBinding.layoutPayment.radioCard.setChecked(false);
        mBinding.layoutPayment.radioPaytm.setChecked(false);
        mBinding.layoutPayment.radioOther.setChecked(true);
    }

    private void selectPaytm() {
        mBinding.layoutPayment.radioCash.setChecked(false);
        mBinding.layoutPayment.radioCard.setChecked(false);
        mBinding.layoutPayment.radioPaytm.setChecked(true);
        mBinding.layoutPayment.radioOther.setChecked(false);
    }

    private void selectCard() {
        mBinding.layoutPayment.radioCash.setChecked(false);
        mBinding.layoutPayment.radioCard.setChecked(true);
        mBinding.layoutPayment.radioPaytm.setChecked(false);
        mBinding.layoutPayment.radioOther.setChecked(false);
    }

    private void selectCash() {
        mBinding.layoutPayment.radioCash.setChecked(true);
        mBinding.layoutPayment.radioCard.setChecked(false);
        mBinding.layoutPayment.radioPaytm.setChecked(false);
        mBinding.layoutPayment.radioOther.setChecked(false);
    }

    private boolean isValid() {
        return false;

    }


}
