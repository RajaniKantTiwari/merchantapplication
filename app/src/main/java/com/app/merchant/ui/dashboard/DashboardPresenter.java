package com.app.merchant.ui.dashboard;


import android.app.Activity;

import com.app.merchant.network.DefaultApiObserver;
import com.app.merchant.network.Repository;
import com.app.merchant.network.request.dashboard.ProductRequest;
import com.app.merchant.network.request.dashboard.cart.CartListRequest;
import com.app.merchant.network.request.dashboard.cart.CategoryRequest;
import com.app.merchant.network.request.dashboard.cart.CheckoutRequest;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.network.response.dashboard.cart.CategoryResponse;
import com.app.merchant.network.response.dashboard.cart.ProductDetailsData;
import com.app.merchant.network.response.dashboard.cart.ProductFullInformationData;
import com.app.merchant.network.response.dashboard.chartdata.orderconfirmed.OrderConfirmedChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderconfirmed.OrderConfirmedData;
import com.app.merchant.network.response.dashboard.chartdata.orderdelivered.OrderDeliveredChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderdelivered.OrderDeliveredData;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDeliveryChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderoutfordelivery.OrderOutForDeliveryData;
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceivedChartData;
import com.app.merchant.network.response.dashboard.chartdata.orderreceived.OrderReceivedData;
import com.app.merchant.network.response.dashboard.deliveryboy.DeliveryBoyData;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.ui.base.Presenter;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.LogUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.app.merchant.utility.AppConstants.VIEW_CART;


public class DashboardPresenter implements Presenter<MvpView> {


    private MvpView mView;
    private Repository mRepository;


    @Override
    public void attachView(MvpView view) {
        mView = view;
    }

    @Inject
    public DashboardPresenter(Repository repository) {
        this.mRepository = repository;
    }

   public void addForCartList(DashBoardActivity activity, CartListRequest request, MvpView mView) {
       if (mView == null) {
           activity.showProgress();
       } else {
           mView.showProgress();
       }

       mRepository.addForCartList(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
           @Override
           public void onResponse(BaseResponse response) {

               if (mView == null) {
                   activity.hideProgress();
                   activity.onSuccess(response, AppConstants.CARTADDED);
               } else {
                   mView.hideProgress();
                   mView.onSuccess(response, AppConstants.CARTADDED);
               }

           }

           @Override
           public void onError(Throwable call, BaseResponse baseResponse) {

               if (mView == null) {
                   activity.hideProgress();
                   activity.onError(baseResponse.getMsg(), AppConstants.CARTADDED);
               } else {
                   mView.hideProgress();
                   mView.onError(baseResponse.getMsg(), AppConstants.CARTADDED);
               }

           }
       });
   }
    public void getCategory(Activity activity, CategoryRequest categoryRequest) {
        mView.showProgress();
        LogUtils.LOGD("", "Repos==" + mRepository);
        mRepository.getCategory(categoryRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<CategoryResponse>(activity) {
            @Override
            public void onResponse(CategoryResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, 1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), 1);
            }
        });
    }
    public void viewCart(Activity activity) {
        mView.showProgress();
        mRepository.viewCart().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ProductDetailsData>(activity) {
            @Override
            public void onResponse(ProductDetailsData response) {
                mView.hideProgress();
                mView.onSuccess(response, VIEW_CART);
            }
            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), VIEW_CART);
            }
        });
    }
    public void checkout(Activity activity, CheckoutRequest checkoutRequest) {
        mView.showProgress();
        mRepository.checkout(checkoutRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, 2);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), 2);
            }
        });
    }
    public void getProductDetails(Activity activity, ProductRequest request) {
        mView.showProgress();
        mRepository.getProductDetail(request).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ProductFullInformationData>(activity) {
            @Override
            public void onResponse(ProductFullInformationData response) {
                mView.hideProgress();
                mView.onSuccess(response, 2);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), 2);
            }
        });
    }

    public void getOrderReceivedChart(Activity activity) {
        mView.showProgress();
        mRepository.getOrderReceivedChart().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<OrderReceivedChartData>(activity) {
            @Override
            public void onResponse(OrderReceivedChartData response) {
                mView.hideProgress();
                mView.onSuccess(response, AppConstants.CHART_DATA);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), AppConstants.CHART_DATA);
            }
        });
    }

    public void getOrderReceived(Activity activity) {
        mView.showProgress();
        mRepository.getOrderReceived().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<OrderReceivedData>(activity) {
            @Override
            public void onResponse(OrderReceivedData response) {
                mView.hideProgress();
                mView.onSuccess(response, AppConstants.ORDER_DATA);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), AppConstants.ORDER_DATA);
            }
        });
    }

    public void getOrderConfirmedChart(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.getOrderConfirmedChart().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<OrderConfirmedChartData>(activity) {
            @Override
            public void onResponse(OrderConfirmedChartData response) {
                mView.hideProgress();
                mView.onSuccess(response, AppConstants.CHART_DATA);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), AppConstants.CHART_DATA);
            }
        });
    }

    public void getOrderConfirmed(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.getOrderConfirmed().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<OrderConfirmedData>(activity) {
            @Override
            public void onResponse(OrderConfirmedData response) {
                mView.hideProgress();
                mView.onSuccess(response, AppConstants.ORDER_DATA);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), AppConstants.ORDER_DATA);
            }
        });
    }

    public void getOrderOutForDeliveryChart(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.getOrderOutForDeliveryChart().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<OrderOutForDeliveryChartData>(activity) {
            @Override
            public void onResponse(OrderOutForDeliveryChartData response) {
                mView.hideProgress();
                mView.onSuccess(response, AppConstants.CHART_DATA);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), AppConstants.CHART_DATA);
            }
        });
    }

    public void getOrderOutForDelivery(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.getOrderOutForDelivery().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<OrderOutForDeliveryData>(activity) {
            @Override
            public void onResponse(OrderOutForDeliveryData response) {
                mView.hideProgress();
                mView.onSuccess(response, AppConstants.ORDER_DATA);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), AppConstants.ORDER_DATA);
            }
        });
    }

    public void getDeliveryBoyList(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.getDeliveryBoyList().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<DeliveryBoyData>(activity) {
            @Override
            public void onResponse(DeliveryBoyData response) {
                mView.hideProgress();
                mView.onSuccess(response, AppConstants.DELIVERY_BOY_DATA);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), AppConstants.DELIVERY_BOY_DATA);
            }
        });
    }


    public void getOrderDeliveredChart(Activity activity) {
        mView.showProgress();
        mRepository.getOrderDeliveredChart().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<OrderDeliveredChartData>(activity) {
            @Override
            public void onResponse(OrderDeliveredChartData response) {
                mView.hideProgress();
                mView.onSuccess(response, AppConstants.CHART_DATA);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), AppConstants.CHART_DATA);
            }
        });
    }

    public void getOrderDelivered(Activity activity) {
        mView.showProgress();
        mRepository.getOrderDelivered().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<OrderDeliveredData>(activity) {
            @Override
            public void onResponse(OrderDeliveredData response) {
                mView.hideProgress();
                mView.onSuccess(response, AppConstants.ORDER_DATA);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), AppConstants.ORDER_DATA);
            }
        });
    }
}
