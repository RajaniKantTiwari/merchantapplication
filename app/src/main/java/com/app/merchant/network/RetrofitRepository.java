package com.app.merchant.network;

import com.app.merchant.network.request.RegisterRequest;
import com.app.merchant.network.request.VerifyMobileRequest;
import com.app.merchant.network.request.dashboard.ProductRequest;
import com.app.merchant.network.request.dashboard.cart.CartListRequest;
import com.app.merchant.network.request.dashboard.cart.CategoryRequest;
import com.app.merchant.network.request.dashboard.cart.CheckoutRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.LoginResponse;
import com.app.merchant.network.response.VerifyMobileResponse;
import com.app.merchant.network.response.dashboard.cart.CategoryResponse;
import com.app.merchant.network.response.dashboard.cart.ProductDetailsData;
import com.app.merchant.network.response.dashboard.cart.ProductFullInformationData;
import com.app.merchant.network.response.dashboard.chartdata.orderconfirmed.OrderConfirmedChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderconfirmed.OrderConfirmedData;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDeliveryChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDeliveryData;
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceivedChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceivedData;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.Retrofit;


public class RetrofitRepository implements Repository {
    private ApiService apiService;

    public RetrofitRepository(Retrofit retrofit) {
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Observable<LoginResponse> getLoginDetail(RegisterRequest request) {
        return apiService.getLoginDetail(request);
    }

    @Override
    public Observable<VerifyMobileResponse> verifyMobileNumber(VerifyMobileRequest request) {
        return apiService.verifyMobileNumber(request);
    }

    @Override
    public Completable logout() {
        return null;
    }



    @Override
    public Observable<BaseResponse> addForCartList(CartListRequest request) {
        return apiService.addForCartList(request);
    }

    @Override
    public Observable<CategoryResponse> getCategory(CategoryRequest productRequest) {
        return apiService.getProducts(productRequest);

    }
    @Override
    public Observable<ProductDetailsData> viewCart() {
        return apiService.viewCart();
    }
    @Override
    public Observable<BaseResponse> checkout(CheckoutRequest checkoutRequest) {
        return apiService.checkout(checkoutRequest);
    }
    @Override
    public Observable<ProductFullInformationData> getProductDetail(ProductRequest productRequest) {
        return apiService.getProductDetail(productRequest);
    }


    @Override
    public Observable<OrderReceivedChartData> getOrderReceivedChart() {
        return apiService.getOrderReceivedChart();
    }

    @Override
    public Observable<OrderReceivedData> getOrderReceived() {
        return apiService.getOrderReceived();
    }

    @Override
    public Observable<OrderConfirmedChartData> getOrderConfirmedChart() {
        return apiService.getOrderConfirmedChart();
    }

    @Override
    public Observable<OrderConfirmedData> getOrderConfirmed() {
        return apiService.getOrderConfirmed();
    }

    @Override
    public Observable<OrderOutForDeliveryChartData> getOrderOutForDeliveryChart() {
        return apiService.getOrderOutForDeliveryChart();
    }

    @Override
    public Observable<OrderOutForDeliveryData> getOrderOutForDelivery() {
        return apiService.getOrderOutForDelivery();
    }
}
