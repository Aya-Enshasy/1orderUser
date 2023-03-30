package com.user.order.utils;

import android.text.Editable;

import android.text.TextWatcher;
import android.widget.EditText;

public class PhoneTextWatcher implements TextWatcher {

    private EditText editText;

    public PhoneTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().trim().length() == 1 && s.toString().trim().equals("0"))
            editText.setText("");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}