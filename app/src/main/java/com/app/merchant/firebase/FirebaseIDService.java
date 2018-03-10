package com.app.merchant.firebase;

/*
 * Copyright (C) 2015, francesco Azzola
 *
 *(http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

import com.app.merchant.utility.LogUtils;
import com.app.merchant.utility.PreferenceUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class FirebaseIDService extends FirebaseInstanceIdService {
    private static final String TAG = FirebaseIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        //LogUtils.LOGE(TAG,tkn);
        PreferenceUtils.setDeviceToken(token);
        LogUtils.LOGE("DevicePushTokenAre", PreferenceUtils.getDeviceToken());
        sendRegistrationToServer(token);

    }

    private void sendRegistrationToServer(String token) {
        //PreferenceUtils.setDeviceToken(token);

        /*UpdateDeviceRequest deviceRequest = new UpdateDeviceRequest();
        if (PreferenceUtil.getPrefUserId() != -1)
            deviceRequest.setUserId(PreferenceUtil.getPrefUserId());
        deviceRequest.setDeviceId(refreshedToken);
        Call<UpdateDeviceResponse> call = RequestController.getInstance().createService(AuthWebServices.class, true, false).
                updateDeviceId(deviceRequest);
        call.enqueue(new Callback<UpdateDeviceResponse>() {
            @Override
            public void onResponse(Call<UpdateDeviceResponse> call, Response<UpdateDeviceResponse> response) {
                LogUtils.LOGD(TAG, "Success");
            }

            @Override
            public void onFailure(Call<UpdateDeviceResponse> call, Throwable t) {
                LogUtils.LOGD(TAG, "Fail");
            }
        });*/
    }
}
