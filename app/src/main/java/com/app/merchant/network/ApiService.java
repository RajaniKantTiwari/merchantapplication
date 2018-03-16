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
import com.app.merchant.network.response.LoginResponse;
import com.app.merchant.network.response.LoginResponseData;
import com.app.merchant.network.response.VerifyMobileResponse;
import com.app.merchant.network.response.dashboard.cart.CategoryResponse;
import com.app.merchant.network.response.dashboard.cart.ProductDetailsData;
import com.app.merchant.network.response.dashboard.cart.ProductFullInformationData;
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
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
    @POST("register/user")
    Observable<BaseResponse> registerMerchant(@Body RegisterRequest register);
    @POST("register/login")
    Observable<LoginResponseData> loginMerchant(@Body LoginRequest request);

    @GET("register/logout")
    Observable<BaseResponse> logout();

    @POST("register/verifyotp")
    Observable<VerifyMobileResponse> verifyMobileNumber(@Body VerifyMobileRequest request);

    @POST("cart/addalltocart")
    Observable<BaseResponse> addForCartList(@Body CartListRequest request);

    @POST("product/getallproducts")
    Observable<CategoryResponse> getProducts(@Body CategoryRequest request);
    @POST("cart/checkoutcart")
    Observable<BaseResponse> checkout(@Body CheckoutRequest request);

    @GET("cart/viewcart")
    Observable<ProductDetailsData> viewCart();

    @POST("product/getproductdetails")
    Observable<ProductFullInformationData> getProductDetail(@Body ProductRequest request);

    @POST("shopping/get_merchant_orders_received_chart")
    Observable<OrderReceivedChartData> getOrderReceivedChart();

    @POST("shopping/get_merchant_orders_received")
    Observable<OrderReceivedData> getOrderReceived();

    @POST("shopping/get_merchant_orders_confirmed_chart")
    Observable<OrderConfirmedChartData> getOrderConfirmedChart();

    @POST("shopping/get_merchant_orders_confirmed")
    Observable<OrderConfirmedData> getOrderConfirmed();

    @POST("shopping/get_merchant_orders_orderoutfordelivery_chart")
    Observable<OrderOutForDeliveryChartData> getOrderOutForDeliveryChart();

    @POST("shopping/get_merchant_orders_orderoutfordelivery")
    Observable<OrderOutForDeliveryData> getOrderOutForDelivery();

    @POST("shopping/get_merchant_orders_delivered_chart")
    Observable<OrderDeliveredChartData> getOrderDeliveredChart();

    @POST("shopping/get_merchant_orders_delivered")
    Observable<OrderDeliveredData> getOrderDelivered();

    @POST("shopping/get_merchant_orders_canceled_chart")
    Observable<OrderReturnedCancelChartData> getOrderCancelledChart();

    @POST("shopping/get_merchant_orders_canceled")
    Observable<OrderReturnedCancelData> getOrderCancelled();

    @POST("shopping/get_merchant_orders_returned_chart")
    Observable<OrderReturnedChartData> getOrderReturnedChart();

    @POST("shopping/get_merchant_orders_returned")
    Observable<OrderReturnedData> getOrderReturned();

    @POST("shopping/get_merchant_orders_assigned_delivery_boy")
    Observable<ProductFullInformationData> assignDeliveryBoy();

    @POST("shopping/get_merchant_orders_assigned_delivery_boy_chart")
    Observable<BaseResponse> assignDeliveryBoyChart();

    @POST("shopping/get_delivery_boys")
    Observable<DeliveryBoyData> getDeliveryBoyList();

    @POST("shopping/get_partial_order_details")
    Observable<BaseResponse> getPartialOrderDetail();

    @POST("shopping/assign_delivery_boy_to_order")
    Observable<ProductFullInformationData> assignDeliveryBoyToOrder();

    @POST("shopping/get_all_orders")
    Observable<MyOrderData> getAllOrder();

    @POST("shopping/get_merchant_orders_return_requested_chart")
    Observable<BaseResponse> getOrderReturnedRequestChart();

    @POST("shopping/get_merchant_orders_return_requested")
    Observable<ProductFullInformationData> getOrderReturnedRequest();


    @POST("shopping/get_merchant_orders_cancel_requested_chart")
    Observable<BaseResponse> getCancelRequestChart();

    @POST("shopping/get_merchant_cancel_requested")
    Observable<ProductFullInformationData> getCancelRequest();

    @POST("cart/deletecart")
    Observable<BaseResponse> deleteCart(@Body DeleteCartRequest request);

    @POST("cart/addcart")
    Observable<BaseResponse> addToCart(@Body CartRequest request);
}
