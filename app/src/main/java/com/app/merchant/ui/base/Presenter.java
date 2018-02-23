package com.app.merchant.ui.base;


/**
 *
 */
public interface Presenter<T extends MvpView> {
    void attachView(T view);
}
