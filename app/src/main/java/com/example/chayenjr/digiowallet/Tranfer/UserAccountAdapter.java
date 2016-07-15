package com.example.chayenjr.digiowallet.Tranfer;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;

import com.example.chayenjr.digiowallet.R;

import java.util.List;

/**
 * Created by Chayenjr on 13/7/2559.
 */
public class UserAccountAdapter extends BaseAdapter {

    String[] account_number;
    Integer[] image_bank_account;

    Integer[] ic_bank_ID = {
            R.drawable.ic_kbank,
            R.drawable.ic_ktb,
            R.drawable.ic_scb,
            R.drawable.ic_krungsri,
            R.drawable.ic_tbank,
            R.drawable.ic_digio
    };
    private boolean CheckToken;
    private int mSelected = -1;

    public int getCount() {
        if (account_number == null)
            return 0;
        else
            return account_number.length ;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        AccountRadioList accountRadioList;

        if (convertView == null)
            accountRadioList = new AccountRadioList(parent.getContext());
        else
            accountRadioList = (AccountRadioList) convertView;

        accountRadioList.setTextAccount(account_number[position]);
        Log.d("Position", "" + position);

        return accountRadioList;

    }

    public void setAccount_number(String[] account_number) {
        this.account_number = account_number;
    }

    public void setImage_bank_account(int position) {
        //use position for show bank_icon account, but in mocked-up use digio for default
        image_bank_account[account_number.length - 1] = R.drawable.ic_digio;
    }

    public static String getTextRadio() {
        return AccountRadioList.getTextRadio();
    }

}
