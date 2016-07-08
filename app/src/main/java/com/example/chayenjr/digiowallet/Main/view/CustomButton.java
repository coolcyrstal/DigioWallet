package com.example.chayenjr.digiowallet.Main.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chayenjr.digiowallet.R;

import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class CustomButton extends BaseCustomViewGroup {

    private int iconId ;
    private String settext;
    private ImageView iconImage;
    private TextView buttonText;

    public CustomButton(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.button_layout, this);
    }

    private void initInstances() {
        // findViewById here
        iconImage = (ImageView) findViewById(R.id.iconImage);
        buttonText = (TextView) findViewById(R.id.buttonText);

    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomButton,
                defStyleAttr, defStyleRes);

        try {
            iconId = a.getResourceId(R.styleable.CustomButton_setIcon,0);
            settext = a.getString(R.styleable.CustomButton_setText);
        } finally {
            a.recycle();
        }

        iconImage.setImageResource(iconId);
        buttonText.setText(settext);


    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

}
