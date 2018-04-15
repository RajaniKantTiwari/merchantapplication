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

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import me.leolin.shortcutbadger.ShortcutBadger;


public class FirebaseMessageService extends FirebaseMessagingService {
    private static final String TAG = FirebaseMessageService.class.getSimpleName();
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage!=null){
            ShortcutBadger.applyCount(getApplicationContext(), 5);
            //ShortcutBadger.removeCount(getApplicationContext());
            Map<String, String> dataMsg = remoteMessage.getData();
            /*if(dataMsg!=null){
                Set<String> keySet = dataMsg.keySet();
                if(keySet!=null){
                    String badge=dataMsg.get(Constants.BADGE);
                    if(badge!=null){
                        ShortcutBadger.with(getApplicationContext()).count(Integer.parseInt(badge));
                    }
                    String message=dataMsg.get(Constants.MESSAGE);
                    String notificationType=dataMsg.get(Constants.NOTIFICATION_TYPE);
                    PreferenceUtil.setPrefBadgeCount(badge);

                    // Create Notification
                    //TODO check notification and send to particular Activity
                    Intent intent = new Intent();
                    intent.putExtra(Constants.NOTIFICATION_TYPE,notificationType);
                    Log.e("LoginNoti",""+PreferenceUtil.isLogin());
                    if(PreferenceUtil.isLogin()){
                        if(PreferenceUtil.getPrefRollId() == Constants.ASSOCIATE_ROLL_ID){
                            // Associate
                            intent.setClass(this, DashBoardActivity.class);
                        }else if(PreferenceUtil.getPrefRollId()==Constants.STOREMANAGER_ROLL_ID){
                            // Manager
                            intent.setClass(this, ManagerDashBoardActivity.class);
                        }
                    }else{
                        intent.setClass(this, LoginActivity.class);
                    }

                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    PendingIntent pendingIntent = PendingIntent.getActivity(this, 1410, intent,
                            PendingIntent.FLAG_ONE_SHOT);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(getApplication().getString(R.string.app_name))
                            .setContentText(message)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);

                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    notificationManager.notify(14, notificationBuilder.build());

                }
            }*/

        }

    }

   /* private String notificationType(String type) {
        String notificationType=null;
        switch (type) {
            case "1" :
                notificationType = Constants.ADHOC;
                break;
            case "2" :
                notificationType = Constants.LEAVE;
                break;
            case "3" :
                notificationType = Constants.EARLY_CHECK_IN;
                break;
            case "4" :
                notificationType = Constants.LATE_CHECK_IN;
                break;
            case "5" :
                notificationType = Constants.FEED_NOT_ACKNOWLEDGE;
                break;
            case "6" :
                notificationType = Constants.POWER_HOUR;
                break;
            default:
                break;

        }
        return notificationType;

    }*/

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
