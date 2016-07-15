package com.example.chayenjr.digiowallet.Tranfer;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Checkable;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chayenjr.digiowallet.Main.view.BaseCustomViewGroup;
import com.example.chayenjr.digiowallet.R;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;

/**
 * Created by Chayenjr on 13/7/2559.
 */
public class AccountRadioList extends BaseCustomViewGroup implements Checkable{

    private static RadioButton radio_bank_account;
    private TextView textView2;

    public AccountRadioList(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public AccountRadioList(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
    }

    public AccountRadioList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
    }

    private void initInflate() {
        inflate(getContext(), R.layout.radio_select_account, this);
    }

    private void initInstances() {
        // findViewById here
        radio_bank_account = (RadioButton) findViewById(R.id.radioButton);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        BundleSavedState savedState = new BundleSavedState(superState);
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    public void setTextAccount(String text){
        Log.d("radio_bank_account", text);
        radio_bank_account.setText(text);
    }

    public static String getTextRadio(){
        String s = "" + radio_bank_account.getText();
        return s ;
    }

    @Override
    public void setChecked(boolean checked) {
        radio_bank_account.setChecked(checked);
    }

    @Override
    public boolean isChecked() {
        return radio_bank_account.isChecked();
    }

    @Override
    public void toggle() {
        radio_bank_account.toggle();
    }

}
