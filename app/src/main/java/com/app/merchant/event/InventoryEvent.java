package com.app.merchant.event;

import com.app.merchant.network.request.dashboard.home.MyInventory;
import com.app.merchant.network.request.dashboard.home.MyOrder;

import java.util.ArrayList;

/**
 * Created by rajnikant on 24/02/18.
 */

public class InventoryEvent {

    private int orderInventory;
    private ArrayList<MyOrder> myOrderList;
    private ArrayList<MyInventory> myInventoryList;


    public void setOrderInventory(int orderInventory) {
        this.orderInventory = orderInventory;
    }

    public int getOrderInventory() {
        return orderInventory;
    }

    public ArrayList<MyOrder> getMyOrderList() {
        return myOrderList;
    }

    public void setMyOrderList(ArrayList<MyOrder> myOrderList) {
        this.myOrderList = myOrderList;
    }

    public ArrayList<MyInventory> getMyInventoryList() {
        return myInventoryList;
    }

    public void setMyInventoryList(ArrayList<MyInventory> myInventoryList) {
        this.myInventoryList = myInventoryList;
    }
}
