package com.app.merchant.ui.chat;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;


import com.app.merchant.R;
import com.app.merchant.databinding.ActivityChatBinding;
import com.app.merchant.network.request.ChatMessage;
import com.app.merchant.network.response.BaseResponse;
import com.app.merchant.ui.base.BaseActivity;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;
import com.app.merchant.utility.PreferenceUtils;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatActivity extends BaseActivity implements View.OnClickListener {
    private ActivityChatBinding mBinding;
    private List<ChatMessage> messageList;
    Firebase reference1, reference2;
    private MessageAdapter mAdapter;
    private LinearLayoutManager layoutManager;
    private String otherUserMobileNumber;
    private String otherUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        Firebase.setAndroidContext(this);
        messageList = new ArrayList<>();
        initializeData();
        getChat();
        setListener();
    }

    private void initializeData() {
        Intent intent = getIntent();
        if (CommonUtility.isNotNull(intent)) {
            Bundle bundle = intent.getExtras();
            if (CommonUtility.isNotNull(bundle)) {
                otherUserMobileNumber = bundle.getString(AppConstants.CHAT_WITH);
                otherUserName = bundle.getString(AppConstants.CHAT_USER_NAME);

            }
        }
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(otherUserName);
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtility.getColor(this, R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
        layoutManager = new LinearLayoutManager(this);
        mBinding.rvChat.setLayoutManager(layoutManager);
        mAdapter = new MessageAdapter(this, messageList);
        mBinding.rvChat.setAdapter(mAdapter);
    }

    private void getChat() {
        reference1 = new Firebase(AppConstants.FIREBASE_BASE_URL + AppConstants.FIREBASE_MESSAGE + PreferenceUtils.getUserMono() + "_" + otherUserMobileNumber);
        reference2 = new Firebase(AppConstants.FIREBASE_BASE_URL + AppConstants.FIREBASE_MESSAGE + otherUserMobileNumber + "_" + PreferenceUtils.getUserMono());
    }

    private void setListener() {
        mBinding.ivSend.setOnClickListener(this);
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addMessageInList(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void addMessageInList(DataSnapshot dataSnapshot) {
        Map map = dataSnapshot.getValue(Map.class);
        String userName = map.get("user").toString();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUserName(userName);
        chatMessage.setMessage(map.get("message").toString());
        if (userName.equals(PreferenceUtils.getUserMono())) {
            chatMessage.setMessageType(AppConstants.VIEW_TYPE_USER_MESSAGE);
        } else {
            chatMessage.setMessageType(AppConstants.VIEW_TYPE_OTHERS_MESSAGE);
            //addMessageBox(UserDetails.chatWith + ":-\n" + message, 2);
        }
        messageList.add(chatMessage);
        mAdapter.notifyDataSetChanged();
        layoutManager.scrollToPosition(messageList.size() - 1);

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.ivSend) {
            sendMessage();
        } else if (view == mBinding.layoutHeader.ivBack) {
            finish();
        }
    }

    private void sendMessage() {
        String messageText = mBinding.editWriteMessage.getText().toString();
        if (!messageText.equals("")) {
            Map<String, String> map = new HashMap<>();
            map.put("message", messageText);
            map.put("user", PreferenceUtils.getUserMono());
            reference1.push().setValue(map);
            reference2.push().setValue(map);
            mBinding.editWriteMessage.setText("");
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void attachView() {

    }
}