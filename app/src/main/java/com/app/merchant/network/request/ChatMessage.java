package com.app.merchant.network.request;

import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

/**
 * Created by rajnikant on 09/03/18.
 */

public class ChatMessage implements Observable {
    private String userName;
    private String message;
    private int messageType;
    PropertyChangeRegistry registry=new PropertyChangeRegistry();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.remove(onPropertyChangedCallback);
    }
}
