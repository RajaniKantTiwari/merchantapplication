package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentOrderShowDetailsBinding;
import com.app.merchant.network.request.dashboard.OrderRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.OrderDetail;
import com.app.merchant.network.response.dashboard.OrderDetailsData;
import com.app.merchant.network.response.dashboard.cart.Product;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.OrderListAdapter;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.widget.CustomEditText;

import java.util.ArrayList;

/**
 * Created by ashok on 13/11/17.
 */

public class OrderDetailsShowFragment extends DashboardFragment implements OrderListAdapter.OrderListener {

    private FragmentOrderShowDetailsBinding mBinding;
    private OrderListAdapter mAdapter;
    private ArrayList<Product> productList;
    private String orderId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_show_details, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.order_details));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            orderId = bundle.getString(BundleConstants.ORDER_ID);
        }
        productList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvOrderItems.setLayoutManager(layoutManager);
        mAdapter = new OrderListAdapter(getDashboardActivity(), productList, this);
        mBinding.rvOrderItems.setAdapter(mAdapter);
        getPresenter().getPartialOrderDetail(getDashboardActivity(), new OrderRequest(orderId));

    }

    @Override
    public void setListener() {
        mBinding.tvGoBack.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return OrderDetailsShowFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == 1) {
                getDashboardActivity().showToast(response.getMsg());
                OrderDetailsData data = (OrderDetailsData) response;
                ArrayList<OrderDetail> orderDetailList = data.getData();
                if (CommonUtility.isNotNull(orderDetailList) && orderDetailList.size() > 0) {
                    setOrderDetails(orderDetailList.get(0));
                }
            } else if (requestCode == 7 || requestCode == 8) {
                getDashboardActivity().showToast(response.getMsg());
            }else if(requestCode==10){
                getDashboardActivity().showToast(response.getMsg());
                getDashboardActivity().onBackPressed();
            }
        }
    }

    private void setOrderDetails(OrderDetail orderDetail) {
        mBinding.tvCustomerName.setText(CommonUtility.addStrings(orderDetail.getFirstname(), orderDetail.getMiddlename(), orderDetail.getLastname()));
        mBinding.tvCustomerAddress.setText(orderDetail.getDelivery_address());
        mBinding.tvOrderValue.setText(orderDetail.getGrandtotal());
        mBinding.tvOrderNumber.setText(orderDetail.getInvoiceNumber());
        mBinding.tvOrderDate.setText(orderDetail.getInvoiceDate());
        mBinding.tvOrderStatus.setText(orderDetail.getOrder_status());
        mBinding.tvPaymentStatus.setText(orderDetail.getPaymentStatus());
        try {
            Product productData = new Product();
            productData.setId(Integer.parseInt(orderId));
            productData.setProductname(orderDetail.getProductname());
            productData.setQty(Integer.parseInt(orderDetail.getQuantity()));
            productData.setMrp(orderDetail.getMrp());
            productData.setSelling_price(orderDetail.getMerchant_actual_selling_price());
            productList.add(productData);
            mAdapter.notifyDataSetChanged();

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvGoBack) {
            getDashboardActivity().onBackPressed();
        }
    }

    @Override
    public void headerChangedCalled() {

    }

    @Override
    public void onEdit(CustomEditText edMrp, CustomEditText edSellingPrice, int position) {

    }

    @Override
    public void onCancel(int position) {

    }
}
