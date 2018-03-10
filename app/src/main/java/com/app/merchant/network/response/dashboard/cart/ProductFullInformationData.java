package com.app.merchant.network.response.dashboard.cart;


import com.app.merchant.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 15/02/18.
 */

public class ProductFullInformationData extends BaseResponse {
   private ArrayList<ProductData>  info;

   public ArrayList<ProductData> getInfo() {
      return info;
   }

   public void setInfo(ArrayList<ProductData> info) {
      this.info = info;
   }
}
