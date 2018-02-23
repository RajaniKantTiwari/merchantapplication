package com.app.merchant.ui.base;


import io.reactivex.Observable;

/**
 *
 */
public interface UseCase<T> {
    Observable<T> execute();
}
