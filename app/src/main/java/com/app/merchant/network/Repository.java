package com.app.merchant.network;

import com.app.merchant.network.request.LoginRequest;
import com.app.merchant.network.request.VerifyMobileRequest;
import com.app.merchant.network.response.LoginResponse;
import com.app.merchant.network.response.VerifyMobileResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;


public interface Repository {
    Observable<LoginResponse> getLoginDetail(LoginRequest request);

    Observable<VerifyMobileResponse> verifyMobileNumber(VerifyMobileRequest verifyMobileRequest);

    Completable logout();
}
