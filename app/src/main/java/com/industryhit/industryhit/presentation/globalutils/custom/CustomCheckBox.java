package com.industryhit.industryhit.presentation.globalutils.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

/**
 * Created by Administrator on 10-11-2017.
 */

public class CustomCheckBox extends AppCompatCheckBox {

    public CustomCheckBox(Context context) {
        super(context);
    }

    public CustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public CustomCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
//        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
//                "fonts/Montserrat-Regular.ttf");
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/Montserrat-Light_0.ttf");
        setTypeface(tf);
    }
}
