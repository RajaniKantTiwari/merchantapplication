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
import com.app.merchant.databinding.FragmentOrderDeliveredBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.chartdata.orderdelivered.OrderDelivered;
import com.app.merchant.network.response.dashboard.chartdata.orderdelivered.OrderDeliveredChart;
import com.app.merchant.network.response.dashboard.chartdata.orderdelivered.OrderDeliveredChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderdelivered.OrderDeliveredData;
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceivedData;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.AssignNewDeliveryFragment;
import com.app.merchant.ui.dashboard.home.adapter.OrderDeliveredAdapter;
import com.app.merchant.ui.dialogfrag.DeliveryBoyDialogFragment;
import com.app.merchant.ui.dialogfrag.RatingDialogFragment;
import com.app.merchant.utility.AppConstants;
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

public class OrderDeliveredFragment extends DashboardFragment implements
        DeliveryBoyDialogFragment.DeliveryBoyDialogListener, RatingDialogFragment.RatingDialogListener {

    private FragmentOrderDeliveredBinding mBinding;
    private OrderDeliveredAdapter mAdapter;
    private ArrayList<OrderDelivered> deliveredList;
    //for chart
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfOrderDelivered;
    float[][] orderDeliveredTab;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    /*private boolean pointsHaveDifferentColor;*/
    private boolean hasGradientToTransparent = false;
    //End Chart

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_delivered, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.order_delivered));
        deliveredList = new ArrayList<>();
        return mBinding.getRoot();
    }


    @Override
    public void initializeData() {
        getPresenter().getOrderDeliveredChart(getDashboardActivity());
        getPresenter().getOrderDelivered(getDashboardActivity());
        initializeOrderData();
    }

    private void initializeOrderData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvOrder.setLayoutManager(layoutManager);
        mAdapter = new OrderDeliveredAdapter(getDashboardActivity(), deliveredList);
        mBinding.rvOrder.setAdapter(mAdapter);
    }

    private void initializeChartData(ArrayList<OrderDeliveredChart> data) {
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
        v.right = numberOfOrderDelivered - 1;
        mBinding.lineChart.setMaximumViewport(v);
        mBinding.lineChart.setCurrentViewport(v);
    }

    private void generateValues(ArrayList<OrderDeliveredChart> data) {
        if (CommonUtility.isNotNull(data)) {
            numberOfOrderDelivered = data.size();
            orderDeliveredTab = new float[maxNumberOfLines][numberOfOrderDelivered];
            for (int i = 0; i < maxNumberOfLines; ++i) {
                for (int j = 0; j < numberOfOrderDelivered; ++j) {
                    orderDeliveredTab[i][j] = data.get(j).getOrders_count();
                }
            }
        }
    }

    private void generateData(ArrayList<OrderDeliveredChart> data) {

        List<Line> lines = new ArrayList<>();
        List<AxisValue> axisValues = new ArrayList<>();

        for (int i = 0; i < numberOfLines; ++i) {
            List<PointValue> values = new ArrayList<>();
            for (int j = 0; j < numberOfOrderDelivered; ++j) {
                values.add(new PointValue(j, orderDeliveredTab[i][j]));
                if (CommonUtility.isNotNull(data) && data.size() > j) {
                    axisValues.add(new AxisValue(j).setLabel(CommonUtility.formatDate(data.get(j).getInvoiceDate())));
                }
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[3]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            line.setHasGradientToTransparent(hasGradientToTransparent);
           /* if (pointsHaveDifferentColor){
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
        return OrderDeliveredFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == AppConstants.CHART_DATA) {
                setChartResponse(response);
            } else if (requestCode == AppConstants.ORDER_DATA) {
                setOrderResponse(response);
            }
        }
    }

    private void setOrderResponse(BaseResponse response) {
        OrderDeliveredData data = (OrderDeliveredData) response;
        if (CommonUtility.isNotNull(data.getData()) && data.getData().size() > 0) {
            deliveredList.clear();
            deliveredList.addAll(data.getData());
            mAdapter.notifyDataSetChanged();
        }
    }

    private void setChartResponse(BaseResponse response) {
        OrderDeliveredChartData data = (OrderDeliveredChartData) response;
        if (CommonUtility.isNotNull(data.getData()) && data.getData().size() > 0) {
            initializeChartData(data.getData());
        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void newDeliveryBoy() {
        Bundle bundle = new Bundle();
        getDashboardActivity().addFragmentInContainer(new AssignNewDeliveryFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
    }

    @Override
    public void submit(int id, float rating, String feedback) {
        getDashboardActivity().showToast("Rating Submitted");
    }

    @Override
    public void headerChangedCalled() {

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
