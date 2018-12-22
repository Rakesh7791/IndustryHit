package com.industryhit.industryhit.presentation.navigationscreens;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.adapter.ViewPagerAdapter;

public class Videos_Fragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_videos, container, false);
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
        adapter.addFragment(new News_Fragment(), getContext().getResources().getString(R.string.trailers));
        adapter.addFragment(new News_Fragment(), getContext().getResources().getString(R.string.songs));
        adapter.addFragment(new News_Fragment(), getContext().getResources().getString(R.string.events));
        adapter.addFragment(new News_Fragment(), getContext().getResources().getString(R.string.pressmeets));
        adapter.addFragment(new News_Fragment(), getContext().getResources().getString(R.string.others));
        viewPager.setAdapter(adapter);
    }

}
