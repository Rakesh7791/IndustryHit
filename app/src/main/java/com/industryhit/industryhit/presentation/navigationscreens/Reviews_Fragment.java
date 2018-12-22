package com.industryhit.industryhit.presentation.navigationscreens;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class Reviews_Fragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_reviews, container, false);
        View view=inflater.inflate(R.layout.fragment_reviews, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {


        viewPager = (ViewPager)view. findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)view. findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new News_Fragment(), "News");
        adapter.addFragment(new Videos_Fragment(), "Video");
        viewPager.setAdapter(adapter);
    }





}
