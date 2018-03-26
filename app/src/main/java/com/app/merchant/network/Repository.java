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
import com.app.merchant.network.request.dashboard.cart.MerchantProductListRequest;
import com.app.merchant.network.request.dashboard.cart.SubCatProductRequest;
import com.app.merchant.network.request.dashboard.home.DeliveryBoyOrderDetailRequest;
import com.app.merchant.network.request.dashboard.home.MyOrderData;
import com.app.merchant.network.request.dashboard.home.NewCustomerRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.CustomerDetailData;
import com.app.merchant.network.response.LoginResponseData;
import com.app.merchant.network.response.NewCustomerResposeData;
import com.app.merchant.network.response.RegisterResponseData;
import com.app.merchant.network.response.UserSearchResponseData;
import com.app.merchant.network.response.VerifyMobileResponse;
import com.app.merchant.network.request.CustomerPhoneRequest;
import com.app.merchant.network.response.dashboard.MyInventoryData;
import com.app.merchant.network.response.dashboard.OrderData;
import com.app.merchant.network.response.dashboard.OrderDetailsData;
import com.app.merchant.network.response.dashboard.cart.CategoryData;
import com.app.merchant.network.response.dashboard.cart.ProductData;
import com.app.merchant.network.response.dashboard.cart.ProductDetailsData;
import com.app.merchant.network.response.dashboard.cart.ProductFullInformationData;
import com.app.merchant.network.response.dashboard.cart.SubCategoryData;
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

import io.reactivex.Completable;
import io.reactivex.Observable;


public interface Repository {
    Observable<RegisterResponseData> registerMerchant(RegisterRequest register);

    Observable<LoginResponseData> loginMerchant(LoginRequest request);

    Observable<VerifyMobileResponse> verifyMobileNumber(VerifyMobileRequest verifyMobileRequest);

    Observable<BaseResponse> addForCartList(CartListRequest request);

    Observable<CategoryData> getCategory(CategoryRequest productRequest);

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

    Observable<OrderCancelRequestChartData> getCancelRequestChart();

    Observable<OrderCancelRequestData> getCancelRequest();

    Observable<UserSearchResponseData> searchCustomerByPhone(CustomerPhoneRequest request);

    Observable<CustomerDetailData> getCustomerDetails(CustomerRequest request);

    Observable<NewCustomerResposeData> addNewCustomer(NewCustomerRequest request);

    Observable<BaseResponse> cancelOrder(CancelOrderRequest request);

    Observable<DeliveryBoyOrderData> getCountOrderPerDeliveryBoy();

    Observable<DeliveryBoyOrdersData> getDeliveryBoyOrderDetail(DeliveryBoyOrderDetailRequest request);

    Observable<MyInventoryData> myInventoryHistory();

    Observable<OrderData> getOrderSummary(OrderRequest request);

    Observable<BaseResponse> assignDeliveryBoyToOrder(AssignedDeliveryBoyRequest request);

    Observable<BaseResponse> confirmOrder(CancelOrderRequest request);

    Observable<OrderDetailsData> getPartialOrderDetail(OrderRequest request);

    Observable<CategoryData> getProductCategory(CategoryRequest request);

    Observable<SubCategoryData> getProductSubCategory(CategorySubCatRequest request);

    Observable<ProductData> getSubCategoryProduct(SubCatProductRequest request);

    Observable<BaseResponse> addProductToMerchantList(MerchantProductListRequest request);
}
