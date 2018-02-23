package com.app.merchant.utility;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

/**
 * Created by rajnikant on 24/01/18.
 */

public class Days implements Observable{
    private PropertyChangeRegistry changeRegistry=new PropertyChangeRegistry();
    private String nameOfDays;

    @Bindable
    public String getNameOfDays() {
        return nameOfDays;
    }

    public void setNameOfDays(String nameOfDays) {
        this.nameOfDays = nameOfDays;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        changeRegistry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        changeRegistry.remove(onPropertyChangedCallback);
    }
}
