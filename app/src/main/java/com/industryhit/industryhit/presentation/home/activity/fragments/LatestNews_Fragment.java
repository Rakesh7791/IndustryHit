package com.industryhit.industryhit.presentation.home.activity.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.industryhit.industryhit.R;


public class LatestNews_Fragment extends HomeBaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest_news, container, false);
    }


    @Override
    public void onTabSelected() {

    }
}
