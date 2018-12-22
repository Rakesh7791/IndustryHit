package com.industryhit.industryhit.presentation.globalutils.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;

/**
 * Created by Administrator on 10-11-2017.
 */

public class CustomEditText extends AppCompatEditText {
    private String blockCharacterSet = "~#^|$%&*!'";
    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
//        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
//                "fonts/Montserrat-Regular.ttf");
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/Montserrat-Light_0.ttf");
        setTypeface(tf);
      //  this.setFilters(new InputFilter[] { filter });
    }

    InputFilter filter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };
}
