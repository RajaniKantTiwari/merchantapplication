package com.app.merchant.network;

import com.app.merchant.network.request.CustomerPhoneRequest;
import com.app.merchant.network.request.CustomerRequest;
import com.app.merchant.network.request.LoginRequest;
import com.app.merchant.network.request.RegisterRequest;
import com.app.merchant.network.request.VerifyMobileRequest;
import com.app.merchant.network.request.dashboard.AssignedDeliveryBoyRequest;
import com.app.merchant.network.request.dashboard.EmailRequest;
import com.app.merchant.network.request.dashboard.MobileRequest;
import com.app.merchant.network.request.dashboard.OrderRequest;
import com.app.merchant.network.request.dashboard.ProductRequest;
import com.app.merchant.network.request.dashboard.cart.CancelOrderRequest;
import com.app.merchant.network.request.dashboard.cart.CartListRequest;
import com.app.merchant.network.request.dashboard.cart.CartRequest;
import com.app.merchant.network.request.dashboard.cart.CategoryRequest;
import com.app.merchant.network.request.dashboard.cart.CategorySubCatRequest;
import com.app.merchant.network.request.dashboard.cart.CheckoutRequest;
import com.app.merchant.network.request.dashboard.cart.ConfirmOrderRequest;
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
import com.app.merchant.network.response.NewCustomerResposeData;
import com.app.merchant.network.response.RegisterResponseData;
import com.app.merchant.network.response.UserSearchResponseData;
import com.app.merchant.network.response.VerifyMobileResponse;
import com.app.merchant.network.response.dashboard.AllMerchantData;
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

import io.reactivex.Observable;
import retrofit2.Retrofit;


public class RetrofitRepository implements Repository {
    private ApiService apiService;

    @Override
    public Observable<RegisterResponseData> registerMerchant(RegisterRequest register) {
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
    public Observable<CategoryData> getCategory(CategoryRequest productRequest) {
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

    @Override
    public Observable<OrderCancelRequestChartData> getCancelRequestChart() {
        return apiService.getCancelRequestChart();
    }

    @Override
    public Observable<OrderCancelRequestData> getCancelRequest() {
        return apiService.getCancelRequest();
    }

    @Override
    public Observable<UserSearchResponseData> searchCustomerByPhone(CustomerPhoneRequest request) {
        return apiService.searchCustomerByPhone(request);
    }

    @Override
    public Observable<CustomerDetailData> getCustomerDetails(CustomerRequest request) {
        return apiService.getCustomerDetails(request);
    }

    @Override
    public Observable<NewCustomerResposeData> addNewCustomer(NewCustomerRequest request) {
        return apiService.addNewCustomer(request);
    }

    @Override
    public Observable<BaseResponse> cancelOrder(CancelOrderRequest request) {
        return apiService.cancelOrder(request);
    }

    @Override
    public Observable<DeliveryBoyOrderData> getCountOrderPerDeliveryBoy() {
        return apiService.getCountOrderPerDeliveryBoy();
    }

    @Override
    public Observable<DeliveryBoyOrdersData> getDeliveryBoyOrderDetail(DeliveryBoyOrderDetailRequest request) {
        return apiService.getDeliveryBoyOrderDetail(request);
    }

    @Override
    public Observable<MyInventoryData> myInventoryHistory() {
        return apiService.myInventoryHistory();
    }

    @Override
    public Observable<OrderData> getOrderSummary(OrderRequest request) {
        return apiService.getOrderSummary(request);
    }

    @Override
    public Observable<BaseResponse> assignDeliveryBoyToOrder(AssignedDeliveryBoyRequest request) {
        return apiService.assignDeliveryBoyToOrder(request);
    }

    @Override
    public Observable<BaseResponse> confirmOrder(ConfirmOrderRequest request) {
        return apiService.confirmOrder(request);
    }

    @Override
    public Observable<OrderDetailsData> getPartialOrderDetail(OrderRequest request) {
        return apiService.getPartialOrderDetail(request);
    }

    @Override
    public Observable<CategoryData> getProductCategory(CategoryRequest request) {
        return apiService.getProductCategory(request);
    }

    @Override
    public Observable<SubCategoryData> getProductSubCategory(CategorySubCatRequest request) {
        return apiService.getProductSubCategory(request);
    }

    @Override
    public Observable<ProductData> getSubCategoryProduct(SubCatProductRequest request) {
        return apiService.getSubCategoryProduct(request);
    }

    @Override
    public Observable<BaseResponse> addProductToMerchantList(MerchantProductListRequest request) {
        return apiService.addProductToMerchantList(request);
    }

    @Override
    public Observable<BaseResponse> addOrderFeedback(FeedBackRequest request) {
        return apiService.addOrderFeedback(request);
    }

    @Override
    public Observable<AllMerchantData> getAllMerchantProduct() {
        return apiService.getAllMerchantProduct();
    }

    @Override
    public Observable<AllMerchantData> getAllMerchantRunningProduct() {
        return apiService.getAllMerchantRunningProduct();
    }

    @Override
    public Observable<BaseResponse> updateMyInventory(UpdateMyInventoryRequest request) {
        return apiService.updateMyInventory(request);
    }

    @Override
    public Observable<BaseResponse> updateMyInventoryStatus(InventoryStatusRequest request) {
        return apiService.updateMyInventoryStatus(request);
    }

    @Override
    public Observable<BaseResponse> veryfyEmail(EmailRequest request) {
        return apiService.veryfyEmail(request);
    }

    @Override
    public Observable<BaseResponse> veryfyMobile(MobileRequest request) {
        return apiService.veryfyMobile(request);
    }


}
