package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentOrderOutForDeliveryDetailsBinding;
import com.app.merchant.network.request.dashboard.AssignedDeliveryBoyRequest;
import com.app.merchant.network.request.dashboard.OrderRequest;
import com.app.merchant.network.request.dashboard.home.FeedBackRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.OrderDetail;
import com.app.merchant.network.response.dashboard.OrderDetailsData;
import com.app.merchant.network.response.dashboard.cart.Product;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoy;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyData;
import com.app.merchant.ui.adapter.AsignAdapter;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.OrderListAdapter;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.widget.CustomEditText;

import java.util.ArrayList;

/**
 * Created by ashok on 13/11/17.
 */

public class OrderDetailsOutForDeliveryFragment extends DashboardFragment implements OrderListAdapter.OrderListener {

    private FragmentOrderOutForDeliveryDetailsBinding mBinding;
    private OrderListAdapter mAdapter;
    private ArrayList<Product> productList;
    private ArrayList<DeliveryBoy> deliveryBoyList;
    private String orderId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_out_for_delivery_details, container, false);
        getDashboardActivity().showHideView(mBinding.getRoot(),mBinding.showHideView);
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
        deliveryBoyList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvOrderItems.setLayoutManager(layoutManager);
        mAdapter = new OrderListAdapter(getDashboardActivity(), productList, this);
        mBinding.rvOrderItems.setAdapter(mAdapter);
        getPresenter().getDeliveryBoyList(getDashboardActivity());
        getPresenter().getPartialOrderDetail(getDashboardActivity(), new OrderRequest(orderId));

    }

    @Override
    public void setListener() {
        mBinding.tvConfirmCancellation.setOnClickListener(this);
        mBinding.tvRejectCancellation.setOnClickListener(this);

    }

    @Override
    public String getFragmentName() {
        return OrderDetailsOutForDeliveryFragment.class.getSimpleName();
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
            } else if (requestCode == AppConstants.DELIVERY_BOY_DATA) {
                setDeliveryBoyList(response);
            } else if (requestCode == 7 || requestCode == 8) {
                getDashboardActivity().showToast(response.getMsg());
            } else if (requestCode == 10) {
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

    private void setDeliveryBoyList(BaseResponse response) {
        if (CommonUtility.isNotNull(response) && response instanceof DeliveryBoyData) {
            DeliveryBoyData data = (DeliveryBoyData) response;
            if (CommonUtility.isNotNull(data.getData()) && data.getData().size() > 0) {
                deliveryBoyList.clear();
                DeliveryBoy deliveryBoyLatter = new DeliveryBoy();
                deliveryBoyLatter.setId(-1);
                deliveryBoyLatter.setName(getResources().getString(R.string.assign_latter));
                deliveryBoyList.add(deliveryBoyLatter);
                deliveryBoyList.addAll(data.getData());
                DeliveryBoy deliveryBoy = new DeliveryBoy();
                deliveryBoy.setId(-1);
                deliveryBoy.setName(getResources().getString(R.string.assign_to));
                deliveryBoyList.add(deliveryBoy);
            }
        }

        ArrayList<String> deliveryBoyNameList = new ArrayList<>();
        for (int i = 0; i < deliveryBoyList.size(); i++) {
            deliveryBoyNameList.add(deliveryBoyList.get(i).getName());
        }

        AsignAdapter adapter = new AsignAdapter(getContext(), deliveryBoyNameList);
        adapter.setDropDownViewResource(R.layout.spinner_row);
        mBinding.deliveryBoySpiner.setAdapter(adapter);
        mBinding.deliveryBoySpiner.setSelection(adapter.getCount());
        mBinding.deliveryBoySpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                assignedDeliveryBoy(position);
                if (position != deliveryBoyNameList.size() - 1) {
                    if (CommonUtility.isNotNull(view)) {
                        view.setBackgroundResource(0);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void assignedDeliveryBoy(int position) {
        if (CommonUtility.isNotNull(deliveryBoyList) && deliveryBoyList.size() > position) {
            int deliveryBoyId = deliveryBoyList.get(position).getId();
            if (deliveryBoyId != -1) {
                AssignedDeliveryBoyRequest request = new AssignedDeliveryBoyRequest();
                request.setOrderid(orderId);
                request.setDb_id(String.valueOf(deliveryBoyList.get(position + 1).getId()));
                getPresenter().assignDeliveryBoyToOrder(getDashboardActivity(), request);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvRejectCancellation) {
            submitFeedback();
        } else if (view == mBinding.tvConfirmCancellation) {
            submitFeedback();
        }
    }

    private void submitFeedback() {
        FeedBackRequest request = new FeedBackRequest();
        request.setOrder_id(orderId);
        request.setRatings(String.valueOf(mBinding.ratingBar.getRating()));
        if (CommonUtility.isNotNull(mBinding.edFeedBack.getText().toString()) && mBinding.edFeedBack.getText().toString().trim().length() > 0) {
            request.setFeedback(mBinding.edFeedBack.getText().toString().trim());
        }
        getPresenter().addOrderFeedback(getDashboardActivity(), request);
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
