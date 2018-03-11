package com.app.merchant.network.response.dashboard.chartdata.orderreceived;

/**
 * Created by rajnikant on 11/03/18.
 */

public class OrderReceived {
    private String id;
    private String invoiceNumber;
    private String createdAt;
    private String totaldue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTotaldue() {
        return totaldue;
    }

    public void setTotaldue(String totaldue) {
        this.totaldue = totaldue;
    }
}
