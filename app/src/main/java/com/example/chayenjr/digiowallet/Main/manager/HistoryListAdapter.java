package com.example.chayenjr.digiowallet.Main.manager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.chayenjr.digiowallet.Main.view.HistoryView;

/**
 * Created by topza on 8/7/2559.
 */
public class HistoryListAdapter extends BaseAdapter {

    String[] logpost_info;
    String[] logpost_date;
    String[] logpost_amount;
    int size;

    @Override
    public int getCount() {
        if (size == 0)
            return 0;
        else
            return size;
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HistoryView view = new HistoryView(parent.getContext());

        view.setTextInfoTransfer(logpost_info[position]);
        view.setTextDateTransfer(logpost_date[position]);
        view.setTextCountTransfer(logpost_amount[position]);
        return view;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setLogpostInfo(String[] logpost_info) {
        this.logpost_info = logpost_info;
    }

    public void setLogpost_date(String[] logpost_date) {
        this.logpost_date = logpost_date;
    }

    public void setLogpost_amount(String[] logpost_amount) {
        this.logpost_amount = logpost_amount;
    }
}
