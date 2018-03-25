package com.app.merchant.network;


import com.app.merchant.network.request.CustomerRequest;
import com.app.merchant.network.request.LoginRequest;
import com.app.merchant.network.request.RegisterRequest;
import com.app.merchant.network.request.VerifyMobileRequest;
import com.app.merchant.network.request.dashboard.AssignedDeliveryBoyRequest;
import com.app.merchant.network.request.dashboard.OrderRequest;
import com.app.merchant.network.request.dashboard.ProductRequest;
import com.app.merchant.network.request.dashboard.cart.CancelOrderRequest;
import com.app.merchant.network.request.dashboard.cart.CartListRequest;
import com.app.merchant.network.request.dashboard.cart.CartRequest;
import com.app.merchant.network.request.dashboard.cart.CategoryRequest;
import com.app.merchant.network.request.dashboard.cart.CategorySubCatRequest;
import com.app.merchant.network.request.dashboard.cart.CheckoutRequest;
import com.app.merchant.network.request.dashboard.cart.DeleteCartRequest;
import com.app.merchant.network.request.dashboard.cart.InventoryStatusRequest;
import com.app.merchant.network.request.dashboard.cart.MerchantProductListRequest;
import com.app.merchant.network.request.dashboard.cart.SubCatProductRequest;
import com.app.merchant.network.request.dashboard.cart.UpdateMyInventoryRequest;
import com.app.merchant.network.request.dashboard.home.DeliveryBoyOrderDetailRequest;
import com.app.merchant.network.request.dashboard.home.FeedBackRequest;
import com.app.merchant.network.request.dashboard.home.MyOrderData;
import com.app.merchant.network.request.dashboard.home.NewCustomerRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.CustomerDetailData;
import com.app.merchant.network.response.LoginResponseData;
import com.app.merchant.network.response.NewCustomerRespose;
import com.app.merchant.network.response.NewCustomerResposeData;
import com.app.merchant.network.response.RegisterResponseData;
import com.app.merchant.network.response.UserSearchResponseData;
import com.app.merchant.network.response.VerifyMobileResponse;
import com.app.merchant.network.request.CustomerPhoneRequest;
import com.app.merchant.network.response.dashboard.AllMerchantData;
import com.app.merchant.network.response.dashboard.MyInventoryData;
import com.app.merchant.network.response.dashboard.OrderData;
import com.app.merchant.network.response.dashboard.OrderDetailsData;
import com.app.merchant.network.response.dashboard.cart.CategoryResponse;
import com.app.merchant.network.response.dashboard.cart.ProductDetailsData;
import com.app.merchant.network.response.dashboard.cart.ProductFullInformationData;
import com.app.merchant.network.response.dashboard.chartdata.orderassigndeliveryboy.AssignDeliveryBoyChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderassigndeliveryboy.AssignDeliveryBoyData;
import com.app.merchant.network.response.dashboard.chartdata.ordercancelrequest.OrderCancelRequestChartData;
import com.app.merchant.network.response.dashboard.chartdata.ordercancelrequest.OrderCancelRequestData;
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
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyOrderData;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyOrdersData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
    @POST("register/user")
    Observable<RegisterResponseData> registerMerchant(@Body RegisterRequest register);
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
    Observable<AssignDeliveryBoyData> assignDeliveryBoy();

    @POST("shopping/get_merchant_orders_assigned_delivery_boy_chart")
    Observable<AssignDeliveryBoyChartData> assignDeliveryBoyChart();

    @POST("shopping/get_delivery_boys")
    Observable<DeliveryBoyData> getDeliveryBoyList();


    @POST("shopping/assign_delivery_boy_to_order")
    Observable<BaseResponse> assignDeliveryBoyToOrder(@Body AssignedDeliveryBoyRequest request);

    @POST("shopping/get_all_orders")
    Observable<MyOrderData> getAllOrder();

    @POST("shopping/get_merchant_orders_return_requested_chart")
    Observable<OrderReturnRequestChartData> getOrderReturnedRequestChart();

    @POST("shopping/get_merchant_orders_return_requested")
    Observable<OrderReturnRequestData> getOrderReturnedRequest();

    @POST("shopping/get_merchant_orders_cancel_requested_chart")
    Observable<OrderCancelRequestChartData> getCancelRequestChart();

    @POST("shopping/get_merchant_cancel_requested")
    Observable<OrderCancelRequestData> getCancelRequest();

    @POST("cart/deletecart")
    Observable<BaseResponse> deleteCart(@Body DeleteCartRequest request);

    @POST("cart/addcart")
    Observable<BaseResponse> addToCart(@Body CartRequest request);

    @POST("shopping/search_customers_number")
    Observable<UserSearchResponseData> searchCustomerByPhone(@Body CustomerPhoneRequest request);

    @POST("shopping/get_customer_details")
    Observable<CustomerDetailData> getCustomerDetails(@Body CustomerRequest request);

    @POST("shopping/add_new_customer")
    Observable<NewCustomerResposeData> addNewCustomer(@Body NewCustomerRequest request);

    @POST("shopping/cancel_order")
    Observable<BaseResponse> cancelOrder(@Body CancelOrderRequest request);

    @POST("shopping/get_count_orders_per_delivery_boy")
    Observable<DeliveryBoyOrderData> getCountOrderPerDeliveryBoy();

    @POST("shopping/get_deliveryboy_orders_details")
    Observable<DeliveryBoyOrdersData> getDeliveryBoyOrderDetail(@Body DeliveryBoyOrderDetailRequest request);


  /*  @POST("shopping/add_order_feedback")
    Observable<BaseResponse> addOrderFeedback(@Body FeedBackRequest request);*/

    @POST("shopping/get_all_merchant_products")
    Observable<AllMerchantData> getAllMerchantProduct();

    @POST("shopping/get_all_merchant_running_out_products")
    Observable<AllMerchantData> getAllMerchantRunningProduct();

    @POST("shopping/update_my_inventory")
    Observable<BaseResponse> updateMyInventory(@Body UpdateMyInventoryRequest request);

    @POST("shopping/update_my_inventory_status")
    Observable<BaseResponse> updateMyInventoryStatus(@Body InventoryStatusRequest request);

    @POST("shopping/my_inventory_history")
    Observable<MyInventoryData> myInventoryHistory();


    @POST("shopping/get_order_summary")
    Observable<OrderData> getOrderSummary(@Body OrderRequest request);

    @POST("shopping/confirm_order")
    Observable<BaseResponse> confirmOrder(@Body CancelOrderRequest request);


    @POST("shopping/getproduct")
    Observable<BaseResponse> getProductCategory(@Body CategoryRequest request);

    @POST("shopping/getsubcategories")
    Observable<BaseResponse> getProductSubCategory(@Body CategorySubCatRequest request);

    @POST("shopping/getsubcategories_products")
    Observable<BaseResponse> getSubCategoryProduct(@Body SubCatProductRequest request);

    @POST("shopping/add_product_to_merchant_product_list")
    Observable<BaseResponse> addProductToMerchantList(@Body MerchantProductListRequest request);

    @POST("shopping/get_partial_order_details")
    Observable<OrderDetailsData> getPartialOrderDetail(@Body OrderRequest request);



}
