package com.app.merchant.ui.dashboard;


import com.app.merchant.network.Repository;
import com.app.merchant.ui.base.MvpView;
import com.app.merchant.ui.base.Presenter;

import javax.inject.Inject;



public class DashboardInsidePresenter implements Presenter<MvpView> {
    private MvpView mView;
    private Repository mRepository;


    @Override
    public void attachView(MvpView view) {
        mView = view;
    }

    @Inject
    public DashboardInsidePresenter(Repository repository) {
        this.mRepository = repository;
    }


}
