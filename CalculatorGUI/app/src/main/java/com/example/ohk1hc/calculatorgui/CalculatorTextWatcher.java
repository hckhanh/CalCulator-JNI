package com.example.ohk1hc.calculatorgui;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by OHK1HC on 11/30/2015.
 */
public class CalculatorTextWatcher implements TextWatcher {

    private OnReceiveResultListener onReceiveResultListener;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (onReceiveResultListener != null)
            onReceiveResultListener.calculate();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void setOnReceiveResultListener(OnReceiveResultListener onReceiveResultListener) {
        this.onReceiveResultListener = onReceiveResultListener;
    }
}
