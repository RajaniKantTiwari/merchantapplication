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
import com.app.merchant.databinding.FragmentOrderDeliveredBinding;
import com.app.merchant.databinding.FragmentOrderDetailsBinding;
import com.app.merchant.databinding.FragmentUserProfileBinding;
import com.app.merchant.network.request.dashboard.AssignedDeliveryBoyRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.cart.ProductData;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoy;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyData;
import com.app.merchant.presenter.CommonPresenter;
import com.app.merchant.ui.adapter.AsignAdapter;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.OrderConfirmedAdapter;
import com.app.merchant.ui.dashboard.home.adapter.OrderListAdapter;
import com.app.merchant.ui.dashboard.user.UpdateProfileFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by ashok on 13/11/17.
 */

public class OrderDetailsFragment extends DashboardFragment implements OrderListAdapter.OrderListener {

    private FragmentOrderDetailsBinding mBinding;
    private OrderListAdapter mAdapter;
    private ArrayList<ProductData> productList;
    private ArrayList<DeliveryBoy> deliveryBoyList;
    private String orderId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_details, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.order_details));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        Bundle bundle=getArguments();
        if(CommonUtility.isNotNull(bundle)){
            orderId=bundle.getString(BundleConstants.ORDER_ID);
        }
        productList = new ArrayList<>();
        deliveryBoyList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvOrderItems.setLayoutManager(layoutManager);
        mAdapter = new OrderListAdapter(getDashboardActivity(), productList, this);
        mBinding.rvOrderItems.setAdapter(mAdapter);
        getPresenter().getDeliveryBoyList(getDashboardActivity());
    }

    @Override
    public void setListener() {
    }

    @Override
    public String getFragmentName() {
        return OrderDetailsFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if(CommonUtility.isNotNull(response)){
            if (requestCode == AppConstants.DELIVERY_BOY_DATA) {
                setDeliveryBoyList(response);
            } else if (requestCode == 7) {
                getDashboardActivity().showToast(response.getMsg());
            }
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
    }

    @Override
    public void headerChangedCalled() {

    }

    @Override
    public void onOrderClick(int position) {

    }
}
