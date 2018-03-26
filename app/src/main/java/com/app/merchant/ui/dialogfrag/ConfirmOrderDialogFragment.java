package com.app.merchant.ui.dialogfrag;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.app.merchant.R;
import com.app.merchant.databinding.DialogConfirmOrderBinding;
import com.app.merchant.network.response.dashboard.Order;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoy;
import com.app.merchant.ui.adapter.AsignAdapter;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;


public class ConfirmOrderDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogConfirmOrderBinding mBinding;
    private OrderDialogListener listener;
    private FragmentActivity mActivity;
    private ArrayList<DeliveryBoy> deliveryBoyList;
    private Order order;

    public interface OrderDialogListener {
        void orderConfirmed();
        void orderDetails();
        void orderCancel();
        void assignNewDeliveryBoy();
        void assignedDeliveryBoy(int position);
    }

    public void addListener(OrderDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_confirm_order, container, false);
        dialog = getDialog();
        CommonUtility.setDialog(dialog);
        mActivity = getActivity();
        initializeData();
        setListener();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        CommonUtility.setPadding(dialog, mActivity);
    }

    private void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtility.isNotNull(bundle)) {
            order = bundle.getParcelable(BundleConstants.ORDER);
            deliveryBoyList = bundle.getParcelableArrayList(BundleConstants.DELIVERY_BOY_LIST);
        }
        ArrayList<String> deliveryBoyNameList = new ArrayList<>();
        for (int i = 0; i < deliveryBoyList.size(); i++) {
            deliveryBoyNameList.add(deliveryBoyList.get(i).getName());
        }

        AsignAdapter adapter = new AsignAdapter(getContext(), deliveryBoyNameList);
        adapter.setDropDownViewResource(R.layout.spinner_row);
        mBinding.selectedSpiner.setAdapter(adapter);
        mBinding.selectedSpiner.setSelection(adapter.getCount());
        mBinding.selectedSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(CommonUtility.isNotNull(listener)){
                    listener.assignedDeliveryBoy(position);
                }
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
        if(CommonUtility.isNotNull(order)){
            mBinding.tvOrderNumber.setText(CommonUtility.addStrings(getContext().getResources().getString(R.string.order_number), order.getOrder_id()));
            mBinding.tvCustomerName.setText(CommonUtility.addStrings(getContext().getResources().getString(R.string.customer_name), order.getCustomername()));
            mBinding.tvCustomerAddress.setText(CommonUtility.addStrings(getContext().getResources().getString(R.string.customer_address), order.getCustomer_delivery_address()));
            mBinding.tvNumberOfItem.setText(CommonUtility.addStrings(getContext().getResources().getString(R.string.number_of_item), order.getTotalitems()));
            mBinding.tvOrdervalue.setText(CommonUtility.addStrings(getContext().getResources().getString(R.string.order_value), order.getOrdervalue()));
        }else{
            Toast.makeText(getActivity(),"No Order Found",Toast.LENGTH_SHORT).show();
        }
    }

    public void setListener() {
        mBinding.tvCancel.setOnClickListener(this);
        mBinding.tvConfirmOrder.setOnClickListener(this);
        mBinding.tvOrderDetail.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvConfirmOrder) {
            CommonUtility.clicked(mBinding.tvConfirmOrder);
            if (CommonUtility.isNotNull(listener)) {
                listener.orderConfirmed();
            }
        } else if (view == mBinding.tvOrderDetail) {
            CommonUtility.clicked(mBinding.tvOrderDetail);
            if (CommonUtility.isNotNull(listener)) {
                listener.orderDetails();
            }
        } else if (view == mBinding.tvCancel) {
            CommonUtility.clicked(mBinding.tvCancel);
            if (CommonUtility.isNotNull(listener)) {
                listener.orderCancel();
            }
        }else if(view==mBinding.tvNewDelivery){
            CommonUtility.clicked(mBinding.tvNewDelivery);
            if (CommonUtility.isNotNull(listener)) {
                listener.assignNewDeliveryBoy();
            }
        }
        dialog.cancel();
    }
}