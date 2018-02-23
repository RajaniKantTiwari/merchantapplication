/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.app.merchant.injector.module;

import android.support.v7.app.AppCompatActivity;


import com.app.merchant.injector.scope.PerActivity;
import com.app.merchant.network.Repository;
import com.app.merchant.presenter.CommonPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class CommonModule {

    private AppCompatActivity mActivity;

    public CommonModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }


    //to get presenter object
    @Provides
    @PerActivity
    CommonPresenter provideProfilePresenter(Repository repository) {
        return new CommonPresenter(repository);
    }
}
