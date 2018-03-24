package com.app.merchant.presenter;

import android.app.Activity;


import com.app.merchant.network.DefaultApiObserver;
import com.app.merchant.network.Repository;
import com.app.merchant.network.request.CustomerRequest;
import com.app.merchant.network.request.LoginRequest;
import com.app.merchant.network.request.RegisterRequest;
import com.app.merchant.network.request.VerifyMobileRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.CustomerDetailData;
import com.app.merchant.network.response.LoginResponseData;
import com.app.merchant.network.response.UserSearchResponseData;
import com.app.merchant.network.response.VerifyMobileResponse;
import com.app.merchant.network.request.CustomerPhoneRequest;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.ui.base.Presenter;
import com.app.merchant.ui.dashboard.CustomerDetailActivity;
import com.app.merchant.ui.dashboard.SearchActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by arvind on 09/11/17.
 */

public class CommonPresenter implements Presenter<MvpView> {

    private final Repository mRepository;
    private MvpView mView;


    public CommonPresenter(Repository repository) {
        this.mRepository = repository;
    }

    @Override
    public void attachView(MvpView view) {
        this.mView = view;
    }

    public void registerMerchant(Activity activity, RegisterRequest register) {
        mView.showProgress();
        mRepository.registerMerchant(register).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
               mView.hideProgress();
                mView.onSuccess(response, 2);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), 2);
            }
        });
    }

    public void verifyMobileNumber(Activity activity, VerifyMobileRequest verifyMobileRequest) {
        mView.showProgress();
        mRepository.verifyMobileNumber(verifyMobileRequest).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<VerifyMobileResponse>(activity) {
            @Override
            public void onResponse(VerifyMobileResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, 1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), 1);
            }
        });
    }




    public void loginMerchant(Activity activity, LoginRequest loginRequest) {
        mView.showProgress();
        mRepository.loginMerchant(loginRequest).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<LoginResponseData>(activity) {
            @Override
            public void onResponse(LoginResponseData response) {
                mView.hideProgress();
                mView.onSuccess(response, 2);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), 2);
            }
        });
    }

    public void searchCustomerByPhone(SearchActivity activity, CustomerPhoneRequest request) {
        //mView.showProgress();
        mRepository.searchCustomerByPhone(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<UserSearchResponseData>(activity) {
            @Override
            public void onResponse(UserSearchResponseData response) {
                mView.hideProgress();
                mView.onSuccess(response, 1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), 1);
            }
        });
    }

    public void getCustomerDetails(CustomerDetailActivity activity, CustomerRequest request) {
        mView.showProgress();
        mRepository.getCustomerDetails(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<CustomerDetailData>(activity) {
            @Override
            public void onResponse(CustomerDetailData response) {
                mView.hideProgress();
                mView.onSuccess(response, 1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), 1);
            }
        });
    }
}
