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
import com.app.merchant.databinding.FragmentOrderReceivedBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.chartdata.OrderReceived;
import com.app.merchant.network.response.dashboard.chartdata.OrderReceivedChart;
import com.app.merchant.network.response.dashboard.chartdata.OrderReceivedChartData;
import com.app.merchant.network.response.dashboard.chartdata.OrderReceivedData;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.adapter.OrderReceivedAdapter;
import com.app.merchant.ui.dialogfrag.ConfirmOrderDialogFragment;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
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
    private ArrayList<OrderReceived> orderReceivedList = new ArrayList<>();
    //for chart
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfOrderReceived;
    float[][] orderReceivedTab = new float[maxNumberOfLines][numberOfOrderReceived];

    private boolean hasAxes = true;
    private boolean hasAxesNames = false;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private boolean pointsHaveDifferentColor;
    private boolean hasGradientToTransparent = false;
    private OrderReceivedAdapter mAdapter;
    //End Chart

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_received, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.order_received));
        return mBinding.getRoot();
    }


    @Override
    public void initializeData() {
        getPresenter().getOrderReceivedChart(getDashboardActivity());
        getPresenter().getOrderReceived(getDashboardActivity());
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

        generateData();

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
        if(CommonUtility.isNotNull(data))
        {
            numberOfOrderReceived =data.size();
            orderReceivedTab = new float[maxNumberOfLines][numberOfOrderReceived];
            for (int i = 0; i < maxNumberOfLines; ++i) {
                for (int j = 0; j < numberOfOrderReceived; ++j) {
                    orderReceivedTab[i][j] = data.get(j).getOrders_count();
                }
            }
        }
    }

    private void generateData() {

        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfOrderReceived; ++j) {
                values.add(new PointValue(j, orderReceivedTab[i][j]));
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
            if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        mBinding.lineChart.setLineChartData(data);

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
        if (requestCode == AppConstants.ORDER_RECEIVED_CHART) {
            setChartResponse(response);
        } else if (requestCode == AppConstants.ORDER_RECEIVED) {
            setOrderResponse(response);
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
        CommonUtility.showConfirmOrderDialog(getDashboardActivity(), bundle, this);
    }

    @Override
    public void confirmed() {

    }

    @Override
    public void notConfirmed() {

    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
