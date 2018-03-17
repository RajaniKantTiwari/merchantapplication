package com.app.merchant.network;

import com.app.merchant.network.request.LoginRequest;
import com.app.merchant.network.request.RegisterRequest;
import com.app.merchant.network.request.VerifyMobileRequest;
import com.app.merchant.network.request.dashboard.ProductRequest;
import com.app.merchant.network.request.dashboard.cart.CartListRequest;
import com.app.merchant.network.request.dashboard.cart.CartRequest;
import com.app.merchant.network.request.dashboard.cart.CategoryRequest;
import com.app.merchant.network.request.dashboard.cart.CheckoutRequest;
import com.app.merchant.network.request.dashboard.cart.DeleteCartRequest;
import com.app.merchant.network.request.dashboard.home.MyOrderData;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.LoginResponseData;
import com.app.merchant.network.response.VerifyMobileResponse;
import com.app.merchant.network.response.dashboard.cart.CategoryResponse;
import com.app.merchant.network.response.dashboard.cart.ProductDetailsData;
import com.app.merchant.network.response.dashboard.cart.ProductFullInformationData;
import com.app.merchant.network.response.dashboard.chartdata.orderassigndeliveryboy.AssignDeliveryBoyChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderassigndeliveryboy.AssignDeliveryBoyData;
import com.app.merchant.network.response.dashboard.chartdata.orderconfirmed.OrderConfirmedChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderconfirmed.OrderConfirmedData;
import com.app.merchant.network.response.dashboard.chartdata.orderdelivered.OrderDeliveredChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderdelivered.OrderDeliveredData;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDeliveryChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDeliveryData;
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceivedChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceivedData;
import com.app.merchant.network.response.dashboard.chartdata.orderreturned.OrderReturnedChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderreturned.OrderReturnedData;
import com.app.merchant.network.response.dashboard.chartdata.orderreturnedcancel.OrderReturnedCancelChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderreturnedcancel.OrderReturnedCancelData;
import com.app.merchant.network.response.dashboard.chartdata.orderreturnrequest.OrderReturnRequestChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderreturnrequest.OrderReturnRequestData;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyData;

import io.reactivex.Observable;
import retrofit2.Retrofit;


public class RetrofitRepository implements Repository {
    private ApiService apiService;
    @Override
    public Observable<BaseResponse> registerMerchant(RegisterRequest register) {
        return apiService.registerMerchant(register);
    }

    public RetrofitRepository(Retrofit retrofit) {
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Observable<LoginResponseData> loginMerchant(LoginRequest request) {
        return apiService.loginMerchant(request);
    }

    @Override
    public Observable<VerifyMobileResponse> verifyMobileNumber(VerifyMobileRequest request) {
        return apiService.verifyMobileNumber(request);
    }

    @Override
    public Observable<BaseResponse> logout() {
        return apiService.logout();
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

    @Override
    public Observable<DeliveryBoyData> getDeliveryBoyList() {
        return apiService.getDeliveryBoyList();

    }

    @Override
    public Observable<OrderDeliveredChartData> getOrderDeliveredChart() {
        return apiService.getOrderDeliveredChart();
    }

    @Override
    public Observable<OrderDeliveredData> getOrderDelivered() {
        return apiService.getOrderDelivered();
    }

    @Override
    public Observable<OrderReturnedCancelChartData> getOrderCancelledChart() {
        return apiService.getOrderCancelledChart();
    }

    @Override
    public Observable<OrderReturnedCancelData> getOrderCancelled() {
        return apiService.getOrderCancelled();
    }

    @Override
    public Observable<OrderReturnedChartData> getOrderReturnedChart() {
        return apiService.getOrderReturnedChart();
    }

    @Override
    public Observable<OrderReturnedData> getOrderReturned() {
        return apiService.getOrderReturned();
    }
    @Override
    public Observable<BaseResponse> deleteFromCart(DeleteCartRequest request) {
        return apiService.deleteCart(request);
    }

    @Override
    public Observable<BaseResponse> addToCart(CartRequest cartRequest) {
        return apiService.addToCart(cartRequest);
    }

    @Override
    public Observable<MyOrderData> getAllOrder() {
        return apiService.getAllOrder();
    }

    @Override
    public Observable<AssignDeliveryBoyChartData> assignDeliveryBoyChart() {
        return apiService.assignDeliveryBoyChart();
    }

    @Override
    public Observable<AssignDeliveryBoyData> assignDeliveryBoy() {
        return apiService.assignDeliveryBoy();
    }

    @Override
    public Observable<OrderReturnRequestChartData> getOrderReturnedRequestChart() {
        return apiService.getOrderReturnedRequestChart();
    }

    @Override
    public Observable<OrderReturnRequestData> getOrderReturnedRequest() {
        return apiService.getOrderReturnedRequest();
    }

}
