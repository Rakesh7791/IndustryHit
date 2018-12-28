package com.industryhit.industryhit.presentation.home.activity.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.industryhit.industryhit.R;
import com.industryhit.industryhit.businesslogic.CategoryList_Data;
import com.industryhit.industryhit.presentation.globalutils.adapter.ViewPagerAdapter;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextView;
import com.industryhit.industryhit.presentation.home.activity.activities.NewHomeActivity;
import com.industryhit.industryhit.presentation.navigationscreens.News_Fragment;
import com.industryhit.industryhit.presentation.navigationscreens.Videos_Fragment;
import com.industryhit.industryhit.webaccess.OnVolleyResponseListener;
import com.industryhit.industryhit.webaccess.VolleyConnectionGET;
import com.industryhit.industryhit.webaccess.WebServiceList;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends HomeBaseFragment implements OnVolleyResponseListener {
    private TabLayout tabLayout;
    public ProgressDialog progressDialog;
    public ViewPager viewPager;
    private MyAdapter myAdapter;
    private ArrayList<CategoryList_Data> allProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View x) {

        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);


        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(0);
                tabLayout.setupWithViewPager(viewPager);
                getCategoryList();

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                try {


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

        myAdapter = new MyAdapter(getChildFragmentManager(), new ArrayList<CategoryList_Data>());
        viewPager.setAdapter(myAdapter);
    }



    private void getCategoryList() {
        new VolleyConnectionGET(getActivity(), this).execute(WebServiceList.CATEGORIES_LIST_LABEL);

    }


    @Override
    public void onResponse(String response, String methodName) {
            if (methodName.equalsIgnoreCase(WebServiceList.CATEGORIES_LIST_LABEL)) {
                GlobalMethods.hideProgress(progressDialog);
                if (!response.equalsIgnoreCase("")) {
                    if (GlobalMethods.isValidJsonArray(response)) {

                        List<CategoryList_Data> categoryList_data;
                        Type listType = new TypeToken<List<CategoryList_Data>>() {
                        }.getType();
                        categoryList_data = new Gson().fromJson(response, listType);

                        allProducts = new Gson().fromJson(response, listType);
                        if (allProducts != null && allProducts.size() > 0) {
                            myAdapter.addAll(allProducts);
                        }
                        if (categoryList_data != null && categoryList_data.size() > 0) {
                            addCategoryList(categoryList_data, 0);
                        }
                    }
                }

            }

    }

    private void addCategoryList(List<CategoryList_Data> categoryList_data, int position) {

        try {
            if(tabLayout!=null){
                tabLayout.removeAllTabs();
            }
            for (int j = 0; j < categoryList_data.size(); j++) {
                View tabView;
                tabView = (View) LayoutInflater.from(getActivity()).inflate(R.layout.tab_layout, null);


                ((CustomTextView) tabView.findViewById(R.id.tab)).setText(categoryList_data.get(j).getName());

//                if (j == position) {
//                    ((CustomTextView) tabView.findViewById(R.id.tab)).setTextColor(GlobalMethods.getColor(getContext(), R.color.black));
//                } else {
//                    ((CustomTextView) tabView.findViewById(R.id.tab)).setTextColor(GlobalMethods.getColor(getContext(), R.color.black));
//                }

                if (tabLayout != null) {
                    // Log.e("child",tabLayout.getChildCount()+"");
                    tabLayout.addTab(tabLayout.newTab());
                    tabLayout.getTabAt(j).setCustomView(tabView);

                }
            }
        } catch (Exception e) {

            if (e != null) {

                Log.e("error", e.getMessage());
            }

        }
    }

    @Override
    public void onTabSelected() {

    }
/*
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (reqCode == 100) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    if (bundle.containsKey("selectedLangualge")) {
                      //  initSelectedLanguage();
                        getCategoryList();
                      //  ((CustomTextView) findViewById(R.id.selected_language)).setText(bundle.getString("selectedLangualge"));
                    }

                }
            }
        }

    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        switch (requestCode) {
            case (100):
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        if (bundle.containsKey("selectedLangualge")) {
                            getCategoryList();
                            // ((CustomTextView) findViewById(R.id.selected_language)).setText(bundle.getString("selectedLangualge"));
                        }

                    }


                    break;
                }
        }
    }
    /*private void initSelectedLanguage() {

        if (GlobalMethods.getSelectedLanguage(getActivity())==1){
            ((ImageView)getActivity().findViewById(R.id.img_telugu)).setImageDrawable(getResources().getDrawable(R.drawable.telugu_red));
            ((ImageView)getActivity().findViewById(R.id.img_english)).setImageDrawable(getResources().getDrawable(R.drawable.english_black));
            ((ImageView)getActivity().findViewById(R.id.img_selected_language)).setImageDrawable(getResources().getDrawable(R.drawable.telugu_black));

        }else {
            ((ImageView)getActivity().findViewById(R.id.img_telugu)).setImageDrawable(getResources().getDrawable(R.drawable.telugu_black));
            ((ImageView)getActivity().findViewById(R.id.img_english)).setImageDrawable(getResources().getDrawable(R.drawable.english_red));
            ((ImageView)getActivity().findViewById(R.id.img_selected_language)).setImageDrawable(getResources().getDrawable(R.drawable.english_black));

        }
    }*/

    class MyAdapter extends FragmentPagerAdapter {
        private ArrayList<CategoryList_Data> allCategory;



        public MyAdapter(FragmentManager childFragmentManager, ArrayList<CategoryList_Data> categoryList_data) {
            super(childFragmentManager);
            this.allCategory = categoryList_data;
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            BaseFragment baseFragment;
            Bundle bundle = new Bundle();

            Log.e("Id",allCategory.get(position).getId()+"");
            bundle.putInt("category_id",allCategory.get(position).getId());
            //bundle.putParcelable("product", allProducts.get(position));
            switch (position) {
//                case 0:
//                    return new CategoryFragment();

                default:
                    baseFragment = new CategoryFragment();

                   // return new CategoryFragment();
            }
            baseFragment.setArguments(bundle);
            return baseFragment;
        }

        @Override
        public int getCount() {

            return allCategory.size();

        }
        public void addAll(ArrayList<CategoryList_Data> allProducts) {
            this.allCategory = allProducts;
            notifyDataSetChanged();

        }

        /**
         * This method returns the title of the tab according to the position.
         */

      /*  @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return getResources().getString(R.string.nav_home);
                case 1:
                    return getResources().getString(R.string.shop);
                case 2:
                    return getResources().getString(R.string.scan);
                case 3:
                    return getResources().getString(R.string.book_services);
                case 4:
                    return getResources().getString(R.string.account);
            }
            return null;
        }*/
    }

}
