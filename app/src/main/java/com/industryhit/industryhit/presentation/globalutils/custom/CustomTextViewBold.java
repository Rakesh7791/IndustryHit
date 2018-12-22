package com.industryhit.industryhit.presentation.globalutils.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 30-09-2017.
 */

public class CustomTextViewBold extends AppCompatTextView {


    public CustomTextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewBold(Context context) {
        super(context);
        init();
    }

    private void init() {
//        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
//                "fonts/Montserrat-Bold.ttf");
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/Montserrat-Medium_0.ttf");
        setTypeface(tf);
    }

}
