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
import com.app.merchant.databinding.FragmentCancelRequestBinding;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.chartdata.ordercancelrequest.OrderCancelRequest;
import com.app.merchant.network.response.dashboard.chartdata.ordercancelrequest.OrderCancelRequestChart;
import com.app.merchant.network.response.dashboard.chartdata.ordercancelrequest.OrderCancelRequestChartData;
import com.app.merchant.network.response.dashboard.chartdata.ordercancelrequest.OrderCancelRequestData;
import com.app.merchant.network.response.dashboard.chartdata.orderreturnrequest.OrderReturnRequestChart;
import com.app.merchant.network.response.dashboard.chartdata.orderreturnrequest.OrderReturnRequestChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderreturnrequest.OrderReturnRequestData;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.ui.dashboard.DashboardFragment;
import com.app.merchant.ui.dashboard.home.AssignNewDeliveryFragment;
import com.app.merchant.ui.dashboard.home.adapter.OrderCancelRequestAdapter;
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

public class OrderCancelRequestFragment extends DashboardFragment implements
        OrderCancelRequestAdapter.OrderreturnRequestListener,
        DeliveryBoyDialogFragment.DeliveryBoyDialogListener, RatingDialogFragment.RatingDialogListener {
    private FragmentCancelRequestBinding mBinding;
    private ArrayList<OrderCancelRequest> orderCancelList = new ArrayList<>();
    //for chart
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfOrderCancelRequest;
    float[][] orderCancelRequestTab = new float[maxNumberOfLines][numberOfOrderCancelRequest];
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    /*private boolean pointsHaveDifferentColor;*/
    private boolean hasGradientToTransparent = false;
    private OrderCancelRequestAdapter mAdapter;
    //End Chart

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cancel_request, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.cancel_request));
        return mBinding.getRoot();
    }


    @Override
    public void initializeData() {
        getPresenter().getOrderReturnedRequestChart(getDashboardActivity());
        getPresenter().getOrderReturnedRequest(getDashboardActivity());
        initializeOrderData();
    }

    private void initializeOrderData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvOrder.setLayoutManager(layoutManager);
        mAdapter = new OrderCancelRequestAdapter(getDashboardActivity(), orderCancelList, this);
        mBinding.rvOrder.setAdapter(mAdapter);
    }

    private void initializeChartData(ArrayList<OrderCancelRequestChart> data) {
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
        v.right = numberOfOrderCancelRequest - 1;
        mBinding.lineChart.setMaximumViewport(v);
        mBinding.lineChart.setCurrentViewport(v);
    }

    private void generateValues(ArrayList<OrderCancelRequestChart> data) {
        if (CommonUtility.isNotNull(data)) {
            numberOfOrderCancelRequest = data.size();
            orderCancelRequestTab = new float[maxNumberOfLines][numberOfOrderCancelRequest];
            for (int i = 0; i < maxNumberOfLines; ++i) {
                for (int j = 0; j < numberOfOrderCancelRequest; ++j) {
                    orderCancelRequestTab[i][j] = data.get(j).getOrders_count();
                }
            }
        }
    }

    private void generateData(ArrayList<OrderCancelRequestChart> data) {

        List<Line> lines = new ArrayList<>();
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < numberOfLines; ++i) {
            List<PointValue> values = new ArrayList<>();
            for (int j = 0; j < numberOfOrderCancelRequest; ++j) {
                values.add(new PointValue(j, orderCancelRequestTab[i][j]));
                if (CommonUtility.isNotNull(data) && data.size() > j) {
                    axisValues.add(new AxisValue(j).setLabel(CommonUtility.formatDate(data.get(j).getInvoiceDate())));
                }
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[6]);
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
        return OrderCancelRequestFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtility.isNotNull(response)) {
            if (requestCode == AppConstants.CHART_DATA) {
                OrderCancelRequestChartData chartData = (OrderCancelRequestChartData) response;
                if (CommonUtility.isNotNull(chartData)) {
                    initializeChartData(chartData.getData());
                }
            } else if (requestCode == AppConstants.ORDER_DATA) {
                OrderCancelRequestData requestData = (OrderCancelRequestData) response;
                if (CommonUtility.isNotNull(requestData)) {
                    if (CommonUtility.isNotNull(requestData.getData())) {
                        orderCancelList.addAll(requestData.getData());
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
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
    public void onRatingClick(int position) {
        Bundle bundle = new Bundle();
        CommonUtility.showRatingDialog(getDashboardActivity(), bundle, this);
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
