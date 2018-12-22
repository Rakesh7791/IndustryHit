package com.industryhit.industryhit.presentation.navigationscreens;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextView;

public class News_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment]
        View view = inflater.inflate(R.layout.fragment_news_, container, false);

        initViews(view);
        return view;
    }

    private void initViews(View view) {

    }


}
