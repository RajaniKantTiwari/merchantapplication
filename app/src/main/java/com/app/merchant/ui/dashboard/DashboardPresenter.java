package com.app.merchant.ui.dashboard;


import com.app.merchant.network.Repository;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.ui.base.Presenter;

import javax.inject.Inject;


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

   /* public void getMerchantListBySearch(Activity activity, String search) {
        mView.showProgress();
        mRepository.getMerchantListBySearch(new MerchantSearchRequest(search)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<SearchResponseData>(activity) {
            @Override
            public void onResponse(SearchResponseData response) {
                mView.hideProgress();
                        mView.onSuccess(response,0);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),0);
            }
        });
    }*/


   /* public void getCategory(Activity activity, CategoryRequest categoryRequest) {
        mView.showProgress();
        LogUtils.LOGD("","Repos=="+mRepository);
        mRepository.getCategory(categoryRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<CategoryResponse>(activity) {
            @Override
            public void onResponse(CategoryResponse response) {
                mView.hideProgress();
                mView.onSuccess(response,1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),1);
            }
        });
    }*/

   /* public void addToCart(Activity activity, CartRequest cartRequest) {
        mView.showProgress();
        mRepository.addToCart(cartRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response,1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),1);
            }
        });
    }*/

    /*public void deleteFromCart(Activity activity) {
        mView.showProgress();
        mRepository.deleteCart().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response,1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),1);
            }
        });
    }*/
   /* public void viewCart(Activity activity, CartRequest cartRequest) {
        mView.showProgress();
        mRepository.viewCart(cartRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ProductDetailsData>(activity) {
            @Override
            public void onResponse(ProductDetailsData response) {
                mView.hideProgress();
                mView.onSuccess(response,1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),1);
            }
        });
    }*/



   /* public void setDeviceToken(DashBoardActivity activity, DeviceTokenRequest token) {
        mRepository.setDeviceToken(token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                activity.onSuccess(response,AppConstants.DEVICE_TOKEN_RESPONSE);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                activity.onError(baseResponse.getMsg(),AppConstants.DEVICE_TOKEN_RESPONSE);
            }
        });
    }*/



   /* public void logout(DashBoardActivity activity) {
        activity.showProgress();
        mRepository.logout().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                activity.hideProgress();
                activity.onSuccess(response, AppConstants.LOGOUT);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                activity.hideProgress();
                activity.onError(baseResponse.getMsg(), AppConstants.LOGOUT);
            }
        });
    }*/
}
