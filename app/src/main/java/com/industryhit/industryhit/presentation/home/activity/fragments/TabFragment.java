package com.industryhit.industryhit.presentation.home.activity.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.industryhit.industryhit.R;


public class TabFragment extends BaseFragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_tab, container, false);;
        initViews(view);
        initalizeTab(view);
        return view;
    }


    private void initViews(final View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        final RelativeLayout rootView = (RelativeLayout) view.findViewById(R.id.main_layout);

        if (rootView != null) {
            rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();

                    if (heightDiff > 400) {
                        if (((LinearLayout) view.findViewById(R.id.tabs_layout)) != null)
                            ((LinearLayout) view.findViewById(R.id.tabs_layout)).setVisibility(View.GONE);
                    } else {
                        if (((LinearLayout) view.findViewById(R.id.tabs_layout)) != null)
                            ((LinearLayout) view.findViewById(R.id.tabs_layout)).setVisibility(View.VISIBLE);
                        Log.e("MyActivity", "keyboard closed");
                    }
                }
            });
        }
    }

    private void initalizeTab(View view) {
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                setupTabIcons();

            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                // Check if this is the page you want.
//                    System.out.println("position "+ position);


            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("dasfasdf", "asdfas");
                try {

//                    HomeBaseFragment baseFragment = (HomeBaseFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
//
//                    if (baseFragment != null)
//                        baseFragment.onTabSelected();

                } catch (IndexOutOfBoundsException e) {
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupTabIcons() {
        try {


            View tabOne = (View) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);

            ((TextView) tabOne.findViewById(R.id.tab)).setText(getString(R.string.home));
            ((ImageView) tabOne.findViewById(R.id.tab_icon)).setImageResource(R.drawable.red_home);
            if (tabLayout != null)
                tabLayout.getTabAt(0).setCustomView(tabOne);

            View tabTwo = (View) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            ((TextView) tabTwo.findViewById(R.id.tab)).setText(getString(R.string.lates_news));
            ((ImageView) tabTwo.findViewById(R.id.tab_icon)).setImageResource(R.drawable.red_latestnews);
            if (tabLayout != null)
                tabLayout.getTabAt(1).setCustomView(tabTwo);

            View tabThree = (View) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            ((TextView) tabThree.findViewById(R.id.tab)).setText(getString(R.string.my_account));
            ((ImageView) tabThree.findViewById(R.id.tab_icon)).setImageResource(R.drawable.red_myaccount);
            if (tabLayout != null)
                tabLayout.getTabAt(2).setCustomView(tabThree);

            View tabfour = (View) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            ((TextView) tabfour.findViewById(R.id.tab)).setText(getString(R.string.notifications));
            ((ImageView) tabfour.findViewById(R.id.tab_icon)).setImageResource(R.drawable.red_notification);
            if (tabLayout != null)
                tabLayout.getTabAt(3).setCustomView(tabfour);



        } catch (Exception e) {

            Log.e("icon error",e.toString());
        }
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new LatestNews_Fragment();
                case 2:
                    return new MyAccount_Fragment();
                case 3:
                    return new Notifications_Fragment();
                default:
                    return new HomeFragment();
            }
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return getResources().getString(R.string.home);
                case 1:
                    return getResources().getString(R.string.lates_news);
                case 2:
                    return getResources().getString(R.string.my_account);
                case 3:
                    return getResources().getString(R.string.notifications);

            }
            return null;
        }
    }
}
