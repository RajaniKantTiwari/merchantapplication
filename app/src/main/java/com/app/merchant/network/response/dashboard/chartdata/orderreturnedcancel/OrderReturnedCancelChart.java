package com.app.merchant.network.response.dashboard.chartdata.orderreturnedcancel;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderReturnedCancelChart {
    private int orders_count;
    private String invoiceDate;

    public int getOrders_count() {
        return orders_count;
    }

    public void setOrders_count(int orders_count) {
        this.orders_count = orders_count;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
