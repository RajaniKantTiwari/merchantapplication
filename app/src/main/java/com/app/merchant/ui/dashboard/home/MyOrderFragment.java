package com.app.merchant.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentOrderBinding;
import com.app.merchant.event.InventoryEvent;
import com.app.merchant.network.request.dashboard.home.MyOrder;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.MyOrderAdapter;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderConfirmedFragment;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderDeliveredFragment;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderOutForDeliveryFragment;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderReceivedFragment;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderReturnedCancelFragment;
import com.app.merchant.ui.dashboard.home.graphfragment.OrderReturnedFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


/**
 * Created by ashok on 13/11/17.
 */

public class MyOrderFragment extends DashboardFragment implements MyOrderAdapter.OrderListener{

    private FragmentOrderBinding mBinding;
    private MyOrderAdapter myOrderAdapter;
    private ArrayList<MyOrder> orderList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        CommonUtility.register(this);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        LinearLayoutManager notificationManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvOrder.setLayoutManager(notificationManager);
        myOrderAdapter = new MyOrderAdapter(getDashboardActivity(),this);
        mBinding.rvOrder.setAdapter(myOrderAdapter);
        setOrderList();
        myOrderAdapter.setOrderList(orderList);
    }

    private void setOrderList() {
        orderList=new ArrayList<>();
        MyOrder myOrder1=new MyOrder();
        myOrder1.setOrderStatus("Order Received.");
        orderList.add(myOrder1);

        MyOrder myOrder2=new MyOrder();
        myOrder2.setOrderStatus("Order Confirmed.");
        orderList.add(myOrder2);

        MyOrder myOrder3=new MyOrder();
        myOrder3.setOrderStatus("Order Out for delivery");
        orderList.add(myOrder3);

        MyOrder myOrder4=new MyOrder();
        myOrder4.setOrderStatus("Order Delivered.");
        orderList.add(myOrder4);

        MyOrder myOrder5=new MyOrder();
        myOrder5.setOrderStatus("Order Canceled");
        orderList.add(myOrder5);

        MyOrder myOrder6=new MyOrder();
        myOrder6.setOrderStatus("Order Returned");
        orderList.add(myOrder6);
    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return MyOrderFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {

    }
    @Subscribe
    public void onMessageEvent(InventoryEvent event) {
        if (event.getOrderInventory() == AppConstants.MY_ORDER) {
            mBinding.layoutOrder.setVisibility(View.VISIBLE);
            event.setMyOrderList(orderList);
            myOrderAdapter.setOrderList(event.getMyOrderList());
        } else{
            mBinding.layoutOrder.setVisibility(View.GONE);

        }
    }

    @Override
    public void onViewClick(int position) {
        if(position==0){
            Bundle bundle=new Bundle();
            getDashboardActivity().addFragmentInContainer(new OrderReceivedFragment(),bundle,true,true, BaseActivity.AnimationType.NONE);
        }else if(position==1){
            Bundle bundle=new Bundle();
            getDashboardActivity().addFragmentInContainer(new OrderConfirmedFragment(),bundle,true,true, BaseActivity.AnimationType.NONE);
        }else if(position==2){
            Bundle bundle=new Bundle();
            getDashboardActivity().addFragmentInContainer(new OrderOutForDeliveryFragment(),bundle,true,true, BaseActivity.AnimationType.NONE);
        }else if(position==3){
            Bundle bundle=new Bundle();
            getDashboardActivity().addFragmentInContainer(new OrderDeliveredFragment(),bundle,true,true, BaseActivity.AnimationType.NONE);
        }else if(position==4){
            Bundle bundle=new Bundle();
            getDashboardActivity().addFragmentInContainer(new OrderReturnedCancelFragment(),bundle,true,true, BaseActivity.AnimationType.NONE);
        }else if(position==5){
            Bundle bundle=new Bundle();
            getDashboardActivity().addFragmentInContainer(new OrderReturnedFragment(),bundle,true,true, BaseActivity.AnimationType.NONE);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtility.unregister(this);
    }
}
