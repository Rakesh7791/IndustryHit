package com.industryhit.industryhit.presentation.globalutils.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 19-07-2018.
 */


    public class CustomRecycleView  extends RecyclerView {

        public CustomRecycleView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public CustomRecycleView(Context context) {
            super(context);
        }

        public CustomRecycleView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }
    }
