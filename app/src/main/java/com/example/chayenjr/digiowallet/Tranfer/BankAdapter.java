package com.example.chayenjr.digiowallet.Tranfer;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by golfyzzz on 7/9/2016.
 */
public class BankAdapter extends BaseAdapter {

    private Context context;
    private int itemBackground;
    private Integer[] imageIDs;
    Gallery gallery;
    public BankAdapter(Context c,Integer[] imageIDsGray,Gallery gallery){
        context = c;
        this.imageIDs = imageIDsGray;
        this.gallery = gallery;
        // sets a grey background; wraps around the images

    }

    public BankAdapter(TransferInfoFragment transferInfoFragment) {
    }

    @Override
    public int getCount() {
        return imageIDs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int densityDpi = (int)(metrics.density * 70f);

        final ImageView imageView = new ImageView(context);
        imageView.setImageResource(imageIDs[position]);

        imageView.setLayoutParams(new Gallery.LayoutParams(densityDpi, densityDpi));

        return imageView;

    }

}
