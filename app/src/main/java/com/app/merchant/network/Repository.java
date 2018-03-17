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

import io.reactivex.Completable;
import io.reactivex.Observable;


public interface Repository {
    Observable<BaseResponse> registerMerchant(RegisterRequest register);

    Observable<LoginResponseData> loginMerchant(LoginRequest request);

    Observable<VerifyMobileResponse> verifyMobileNumber(VerifyMobileRequest verifyMobileRequest);
    Observable<BaseResponse> addForCartList(CartListRequest request);
    Observable<CategoryResponse> getCategory(CategoryRequest productRequest);
    Observable<ProductDetailsData> viewCart();
    Observable<BaseResponse> checkout(CheckoutRequest checkoutRequest);
    Observable<ProductFullInformationData> getProductDetail(ProductRequest productRequest);

    Observable<BaseResponse> logout();

    Observable<OrderReceivedChartData> getOrderReceivedChart();

    Observable<OrderReceivedData> getOrderReceived();

    Observable<OrderConfirmedChartData> getOrderConfirmedChart();

    Observable<OrderConfirmedData> getOrderConfirmed();

    Observable<OrderOutForDeliveryChartData> getOrderOutForDeliveryChart();

    Observable<OrderOutForDeliveryData> getOrderOutForDelivery();

    Observable<DeliveryBoyData> getDeliveryBoyList();

    Observable<OrderDeliveredChartData> getOrderDeliveredChart();

    Observable<OrderDeliveredData> getOrderDelivered();

    Observable<OrderReturnedCancelChartData> getOrderCancelledChart();

    Observable<OrderReturnedCancelData> getOrderCancelled();

    Observable<OrderReturnedChartData> getOrderReturnedChart();

    Observable<OrderReturnedData> getOrderReturned();

    Observable<BaseResponse> deleteFromCart(DeleteCartRequest request);

    Observable<BaseResponse> addToCart(CartRequest cartRequest);

    Observable<MyOrderData> getAllOrder();

    Observable<AssignDeliveryBoyChartData> assignDeliveryBoyChart();

    Observable<AssignDeliveryBoyData> assignDeliveryBoy();

    Observable<OrderReturnRequestChartData> getOrderReturnedRequestChart();

    Observable<OrderReturnRequestData> getOrderReturnedRequest();
}
