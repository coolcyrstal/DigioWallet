package com.example.chayenjr.digiowallet.Main.manager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.chayenjr.digiowallet.Main.view.HistoryView;

/**
 * Created by topza on 8/7/2559.
 */
public class HistoryListAdapter extends BaseAdapter {


    @Override
    public int getCount() {
        return 10;
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
        return view;
    }
}
