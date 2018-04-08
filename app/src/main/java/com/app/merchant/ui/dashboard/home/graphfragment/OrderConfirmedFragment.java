package com.app.merchant.ui.dashboard.home.graphfragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentOrderConfirmedBinding;
import com.app.merchant.network.request.dashboard.AssignedDeliveryBoyRequest;
import com.app.merchant.network.request.dashboard.OrderRequest;
import com.app.merchant.network.request.dashboard.cart.CancelOrderRequest;
import com.app.merchant.network.request.dashboard.cart.ConfirmOrderRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.Order;
import com.app.merchant.network.response.dashboard.OrderData;
import com.app.merchant.network.response.dashboard.chartdata.orderconfirmed.OrderConfirmed;
import com.app.merchant.network.response.dashboard.chartdata.orderconfirmed.OrderConfirmedChart;
import com.app.merchant.network.response.dashboard.chartdata.orderconfirmed.OrderConfirmedChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderconfirmed.OrderConfirmedData;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoy;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyData;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.AssignNewDeliveryFragment;
import com.app.merchant.ui.dashboard.home.OrderDetailsFragment;
import com.app.merchant.ui.dashboard.home.adapter.OrderConfirmedAdapter;
import com.app.merchant.ui.dialogfrag.ConfirmOrderDialogFragment;
import com.app.merchant.ui.dialogfrag.DeliveryBoyDialogFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.BundleConstants;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;


/**
 * Created by ashok on 13/11/17.
 */

public class OrderConfirmedFragment extends DashboardFragment implements
        OrderConfirmedAdapter.OrderConfirmedListener, ConfirmOrderDialogFragment.OrderDialogListener  {
    private FragmentOrderConfirmedBinding mBinding;
    private ArrayList<OrderConfirmed> orderList;
    private ArrayList<DeliveryBoy> deliveryBoyList;

    //for chart
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfConfirmedOrder;
    float[][] confirmedOrderNumbersTab = new float[maxNumberOfLines][numberOfConfirmedOrder];
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    /*private boolean pointsHaveDifferentColor;*/
    private boolean hasGradientToTransparent = false;
    private OrderConfirmedAdapter mAdapter;
    private int orderPosition=-1;
    private Order order;
    //End Chart

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_confirmed, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.order_confirmed));
        deliveryBoyList=new ArrayList<>();
        orderList=new ArrayList<>();
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initializeData() {
        getPresenter().getOrderConfirmedChart(getDashboardActivity());
        getPresenter().getOrderConfirmed(getDashboardActivity());
        getPresenter().getDeliveryBoyList(getDashboardActivity());
        initializeOrderData();
    }

    private void initializeOrderData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvOrder.setLayoutManager(layoutManager);
        mAdapter = new OrderConfirmedAdapter(getDashboardActivity(),orderList, this);
        mBinding.rvOrder.setAdapter(mAdapter);
    }

    private void initializeChartData(ArrayList<OrderConfirmedChart> data) {
        mBinding.lineChart.setOnValueTouchListener(new ValueTouchListener());
        // Generate some random values.
        generateValues(data);
        generateData(data);
        // Disable viewport recalculations, see toggleCubic() method for more info.
        mBinding.lineChart.setViewportCalculationEnabled(false);
        resetViewport();

    }

    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(mBinding.lineChart.getMaximumViewport());
        v.bottom = 0;
        v.top = 100;
        v.left = 0;
        v.right = numberOfConfirmedOrder - 1;
        mBinding.lineChart.setMaximumViewport(v);
        mBinding.lineChart.setCurrentViewport(v);
    }

    private void generateValues(ArrayList<OrderConfirmedChart> data) {
        if (CommonUtility.isNotNull(data)) {
            numberOfConfirmedOrder = data.size();
            confirmedOrderNumbersTab = new float[maxNumberOfLines][numberOfConfirmedOrder];
            for (int i = 0; i < maxNumberOfLines; ++i) {
                for (int j = 0; j < numberOfConfirmedOrder; ++j) {
                    confirmedOrderNumbersTab[i][j] = data.get(j).getOrders_count();
                }
            }
        }
    }

    private void generateData(ArrayList<OrderConfirmedChart> data) {

        List<Line> lines = new ArrayList<>();
        List<PointValue> values = new ArrayList<>();
        List<AxisValue> axisValues = new ArrayList<>();

        for (int i = 0; i < numberOfLines; ++i) {
            for (int j = 0; j < numberOfConfirmedOrder; ++j) {
                values.add(new PointValue(j, confirmedOrderNumbersTab[i][j]));
                if(CommonUtility.isNotNull(data)&&data.size()>j){
                    axisValues.add(new AxisValue(j).setLabel(CommonUtility.formatDate(data.get(j).getInvoiceDate())));
                }
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[1]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            line.setHasGradientToTransparent(hasGradientToTransparent);
            /*if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }*/
            lines.add(line);
        }

        this.data = new LineChartData(lines);
        // For build-up animation you have to disable viewport recalculation.
        mBinding.lineChart.setViewportCalculationEnabled(false);
        this.data = new LineChartData(lines);
        this.data.setAxisXBottom(new Axis(axisValues).setHasLines(true).setMaxLabelChars(3));
        this.data.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));
        this.data.setBaseValue(Float.NEGATIVE_INFINITY);
        mBinding.lineChart.setLineChartData(this.data);

    }


    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return OrderConfirmedFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == AppConstants.CHART_DATA) {
                    OrderConfirmedChartData data = (OrderConfirmedChartData) response;
                    if (CommonUtility.isNotNull(data.getData()) && data.getData().size() > 0) {
                        initializeChartData(data.getData());
                    }
            } else if (requestCode == AppConstants.ORDER_DATA) {
                OrderConfirmedData confirmedData=(OrderConfirmedData)response;
                if(CommonUtility.isNotNull(confirmedData)&&CommonUtility.isNotNull(confirmedData.getData())){
                    orderList.addAll(confirmedData.getData());
                    mAdapter.notifyDataSetChanged();
                }
            }else if (requestCode == AppConstants.DELIVERY_BOY_DATA) {
                setDeliveryBoyList(response);
            }else if (requestCode == 3) {
                OrderData data = (OrderData) response;
                ArrayList<Order> orderList = data.getData();
                if (CommonUtility.isNotNull(orderList) && orderList.size() > 0) {
                    order = orderList.get(0);
                }
                showDialog();
            } else if (requestCode == 7) {
                getDashboardActivity().showToast(response.getMsg());
            }
        }
    }
    private void showDialog() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleConstants.ORDER, order);
        bundle.putParcelableArrayList(BundleConstants.DELIVERY_BOY_LIST, deliveryBoyList);
        CommonUtility.showConfirmOrderDialog(getDashboardActivity(), bundle, this);
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
    }
    @Override
    public void onClick(View view) {

    }


    @Override
    public void onOrderConfirmClick(int position) {
            if ((orderPosition != position)||orderPosition==-1) {
                orderPosition = position;
                getPresenter().getOrderSummary(getDashboardActivity(), new OrderRequest(orderList.get(position).getId()));
            } else {
                showDialog();
            }
    }

    @Override
    public void orderDetailClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(BundleConstants.ORDER_ID,orderList.get(position).getId());
        getDashboardActivity().addFragmentInContainer(new OrderDetailsFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
    }

    @Override
    public void headerChangedCalled() {

    }
    @Override
    public void assignNewDeliveryBoy() {
        Bundle bundle = new Bundle();
        getDashboardActivity().addFragmentInContainer(new AssignNewDeliveryFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
    }

    @Override
    public void assignedDeliveryBoy(int position) {
        if (CommonUtility.isNotNull(deliveryBoyList) && deliveryBoyList.size() > position) {
            int deliveryBoyId = deliveryBoyList.get(position).getId();
            if (deliveryBoyId != -1) {
                AssignedDeliveryBoyRequest request = new AssignedDeliveryBoyRequest();
                request.setOrderid(orderList.get(orderPosition).getId());
                request.setDb_id(String.valueOf(deliveryBoyList.get(position + 1).getId()));
                getPresenter().assignDeliveryBoyToOrder(getDashboardActivity(), request);
            }
        }
    }

    @Override
    public void orderConfirmed() {
        ConfirmOrderRequest request = new ConfirmOrderRequest();
        request.setOrderid(orderList.get(orderPosition).getId());
        getPresenter().confirmOrder(getDashboardActivity(), request);
    }

    @Override
    public void orderDetails() {
        Bundle bundle = new Bundle();
        bundle.putString(BundleConstants.ORDER_ID,orderList.get(orderPosition).getId());
        getDashboardActivity().addFragmentInContainer(new OrderDetailsFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
    }

    @Override
    public void orderCancel() {
        CancelOrderRequest request = new CancelOrderRequest();
        request.setOrder_id(orderList.get(orderPosition).getId());
        getPresenter().cancelOrder(getDashboardActivity(), request);
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(getActivity(), "Number Of Order : " + value.getY(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
