package com.app.merchant.ui.dashboard.home.graphfragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.merchant.R;
import com.app.merchant.databinding.FragmentOrderReceivedBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceived;
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceivedChart;
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceivedChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceivedData;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoy;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyData;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.OrderReceivedAdapter;
import com.app.merchant.ui.dialogfrag.ConfirmOrderDialogFragment;
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

public class OrderReceivedFragment extends DashboardFragment implements
        OrderReceivedAdapter.OrderReceivedListener, ConfirmOrderDialogFragment.OrderDialogListener {
    private FragmentOrderReceivedBinding mBinding;
    private ArrayList<OrderReceived> orderReceivedList;
    //for chart
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfOrderReceived;
    float[][] orderReceivedTab;

    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    /* private boolean pointsHaveDifferentColor;*/
    private boolean hasGradientToTransparent = false;
    private OrderReceivedAdapter mAdapter;
    private ArrayList<DeliveryBoy> deliveryBoyList;
    //End Chart

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_received, container, false);
        orderReceivedList = new ArrayList<>();
        deliveryBoyList=new ArrayList<>();
        getDashboardActivity().setHeaderTitle(getString(R.string.order_received));
        return mBinding.getRoot();
    }


    @Override
    public void initializeData() {
        getPresenter().getOrderReceivedChart(getDashboardActivity());
        getPresenter().getOrderReceived(getDashboardActivity());
        getPresenter().getDeliveryBoyList(getDashboardActivity());
        initializeOrderData();
    }

    private void initializeOrderData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvOrder.setLayoutManager(layoutManager);
        mAdapter = new OrderReceivedAdapter(getDashboardActivity(), orderReceivedList, this);
        mBinding.rvOrder.setAdapter(mAdapter);
    }



    private void initializeChartData(ArrayList<OrderReceivedChart> data) {
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
        v.right = numberOfOrderReceived - 1;
        mBinding.lineChart.setMaximumViewport(v);
        mBinding.lineChart.setCurrentViewport(v);
    }

    private void generateValues(ArrayList<OrderReceivedChart> data) {
        if (CommonUtility.isNotNull(data)) {
            numberOfOrderReceived = data.size();
            orderReceivedTab = new float[maxNumberOfLines][numberOfOrderReceived];
            for (int i = 0; i < maxNumberOfLines; ++i) {
                for (int j = 0; j < numberOfOrderReceived; ++j) {
                    orderReceivedTab[i][j] = data.get(j).getOrders_count();
                }
            }
        }
    }

    private void generateData(ArrayList<OrderReceivedChart> data) {
        List<Line> lines = new ArrayList<>();
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < numberOfLines; ++i) {
            List<PointValue> values = new ArrayList<>();
            for (int j = 0; j < numberOfOrderReceived; ++j) {
                values.add(new PointValue(j, orderReceivedTab[i][j]));
                if (CommonUtility.isNotNull(data) && data.size() > j) {
                    axisValues.add(new AxisValue(j).setLabel(CommonUtility.formatDate(data.get(j).getInvoiceDate())));
                }
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[0]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            line.setHasGradientToTransparent(hasGradientToTransparent);
           /* if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }*/
            lines.add(line);
        }
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
        return OrderReceivedFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (requestCode == AppConstants.CHART_DATA) {
            setChartResponse(response);
        } else if (requestCode == AppConstants.ORDER_DATA) {
            setOrderResponse(response);
        } else if (requestCode == AppConstants.DELIVERY_BOY_DATA) {
            setDeliveryBoyList(response);
        }
    }

    private void setDeliveryBoyList(BaseResponse response) {
        if (CommonUtility.isNotNull(response) && response instanceof DeliveryBoyData) {
            DeliveryBoyData data = (DeliveryBoyData) response;
            if (CommonUtility.isNotNull(data.getData()) && data.getData().size() > 0) {
                deliveryBoyList.clear();
               deliveryBoyList.addAll(data.getData());
                DeliveryBoy deliveryBoy=new DeliveryBoy();
                deliveryBoy.setId(-1);
                deliveryBoy.setName(getResources().getString(R.string.assign_to));
                deliveryBoyList.add(deliveryBoy);
            }
        }
    }

    private void setOrderResponse(BaseResponse response) {
        if (CommonUtility.isNotNull(response) && response instanceof OrderReceivedData) {
            OrderReceivedData data = (OrderReceivedData) response;
            if (CommonUtility.isNotNull(data.getData()) && data.getData().size() > 0) {
                orderReceivedList.addAll(data.getData());
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    private void setChartResponse(BaseResponse response) {
        if (CommonUtility.isNotNull(response) && response instanceof OrderReceivedChartData) {
            OrderReceivedChartData data = (OrderReceivedChartData) response;
            if (CommonUtility.isNotNull(data.getData()) && data.getData().size() > 0) {
                initializeChartData(data.getData());
            }
        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onOrderStatusClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BundleConstants.DELIVERY_BOY_LIST,deliveryBoyList);
        CommonUtility.showConfirmOrderDialog(getDashboardActivity(), bundle, this);
    }

    @Override
    public void confirmed() {

    }

    @Override
    public void notConfirmed() {

    }

    @Override
    public void headerChangedCalled() {
        getDashboardActivity().setHeaderTitle(getString(R.string.order_received));
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
