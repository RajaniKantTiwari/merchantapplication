package com.app.merchant.network;


import com.app.merchant.network.request.LoginRequest;
import com.app.merchant.network.request.VerifyMobileRequest;
import com.app.merchant.network.request.dashboard.cart.CartListRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.LoginResponse;
import com.app.merchant.network.response.VerifyMobileResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {
    @POST("register/user")
    Observable<LoginResponse> getLoginDetail(@Body LoginRequest request);

    @POST("register/verifyotp")
    Observable<VerifyMobileResponse> verifyMobileNumber(@Body VerifyMobileRequest request);

    @POST("cart/addalltocart")
    Observable<BaseResponse> addForCartList(@Body CartListRequest request);

}
