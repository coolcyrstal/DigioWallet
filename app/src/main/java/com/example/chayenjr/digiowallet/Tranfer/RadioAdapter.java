package com.example.chayenjr.digiowallet.Tranfer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by topza on 14/7/2559.
 */
public class RadioAdapter extends BaseAdapter {

    String[] account_number;

    @Override
    public int getCount() {
        if (account_number == null)
            return 0;
        else
            return account_number.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CustomViewGroupTemplate list;

        if(view == null)
            list = new CustomViewGroupTemplate(viewGroup.getContext());
        else list = (CustomViewGroupTemplate) view;

        list.setRadioText(account_number[i]);

        return list;
    }

    public void setAccount_number(String[] account_number) {
        this.account_number = account_number;
    }

    public String getTextRadio (int position) {
        return account_number[position];
    }

}
