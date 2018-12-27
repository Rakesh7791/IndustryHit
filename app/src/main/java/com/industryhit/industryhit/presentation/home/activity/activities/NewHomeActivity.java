package com.industryhit.industryhit.presentation.home.activity.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.industryhit.industryhit.R;
import com.industryhit.industryhit.businesslogic.CategoryList_Data;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextView;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextViewMedium;
import com.industryhit.industryhit.presentation.home.activity.fragments.TabFragment;
import com.industryhit.industryhit.presentation.login.activity.BaseActivity;
import com.industryhit.industryhit.presentation.login.activity.RegistrationAct;
import com.industryhit.industryhit.presentation.selectlanguage.Select_LanguageAct;
import com.industryhit.industryhit.webaccess.OnVolleyResponseListener;
import com.industryhit.industryhit.webaccess.VolleyConnectionGET;
import com.industryhit.industryhit.webaccess.WebServiceList;

import java.lang.reflect.Type;
import java.util.List;

public class NewHomeActivity extends BaseActivity implements OnVolleyResponseListener {


    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    private MaterialMenuDrawable mToolbarMorphDrawable;
    public ProgressDialog progressDialog;

    @Override
    protected int setLayoutResuourse() {
        return R.layout.activity_new_home;
    }

    @Override
    protected void initGUI() {
        initDrawaerView();
        initSelectedLanguage();
        actionEvents();

    }


    private void actionEvents() {
        ((LinearLayout) findViewById(R.id.language_layout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSelectLanguageAct();
            }
        });

        ((CustomTextViewMedium)findViewById(R.id.signup)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callRegistrationAct();
            }
        });

        ((LinearLayout)findViewById(R.id.selected_language_layout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSelectLanguageAct();
            }
        });
    }


    private void initSelectedLanguage() {

       // Log.e("Languag",GlobalMethods.getSelectedLanguage(NewHomeActivity.this)+"");

        if (GlobalMethods.getSelectedLanguage(NewHomeActivity.this)==1){
            ((ImageView)findViewById(R.id.img_telugu)).setImageDrawable(getResources().getDrawable(R.drawable.telugu_red));
            ((ImageView)findViewById(R.id.img_english)).setImageDrawable(getResources().getDrawable(R.drawable.english_black));
            ((ImageView)findViewById(R.id.img_selected_language)).setImageDrawable(getResources().getDrawable(R.drawable.telugu_black));

        }else {
            ((ImageView)findViewById(R.id.img_telugu)).setImageDrawable(getResources().getDrawable(R.drawable.telugu_black));
            ((ImageView)findViewById(R.id.img_english)).setImageDrawable(getResources().getDrawable(R.drawable.english_red));
            ((ImageView)findViewById(R.id.img_selected_language)).setImageDrawable(getResources().getDrawable(R.drawable.english_black));

        }
    }

    private void callRegistrationAct() {
        GlobalMethods.callForWordActivity(NewHomeActivity.this, RegistrationAct.class, null, false, true);

    }

    private void callSelectLanguageAct() {
        GlobalMethods.callForWordActivityForResult(NewHomeActivity.this, Select_LanguageAct.class, null, 100, true);
    }

    @Override
    protected void initData() {
        callCategoriesService();

    }

    private void callCategoriesService() {
        progressDialog = GlobalMethods.showProgress(NewHomeActivity.this);
        new VolleyConnectionGET(NewHomeActivity.this, this).execute(WebServiceList.CATEGORIES_LIST_LABEL);

    }

    private void initDrawaerView() {
        ((AppCompatImageView) findViewById(R.id.ic_back)).setVisibility(View.GONE);

        //  ((LinearLayout) findViewById(R.id.title_layout)).setPadding(0, 0, GlobalMethods.getPixToDP(getApplicationContext(), 60), 0);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        try {
            if (mFragmentTransaction != null) {
                TabFragment tab = new TabFragment();

                mFragmentTransaction.replace(R.id.containerViews, tab).commit();
            }
        } catch (Exception e) {
            // Log.e("", "");
        }
//
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        final ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                mToolbarMorphDrawable.animateIconState(MaterialMenuDrawable
                        .IconState.BURGER);
                super.onDrawerClosed(drawerView);


            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(false);
//        setSupportActionBar(toolbar);
//       final Drawable drawable = ResourcesCompat.getDrawable(getResources(),   R.drawable.ic_nav_icon, getApplication().getTheme());
//        final Drawable drawable_back = ResourcesCompat.getDrawable(getResources(),   R.drawable.ic_back, getApplication().getTheme());

        mToolbarMorphDrawable = new MaterialMenuDrawable(this, R.color.grey,
                MaterialMenuDrawable.Stroke.REGULAR);


        mToolbarMorphDrawable.setIconState(MaterialMenuDrawable.IconState.BURGER);

        mDrawerToggle.setHomeAsUpIndicator(mToolbarMorphDrawable);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
            }
        });
        mDrawerToggle.syncState();
    }

    private void closeDrawer() {
        if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
            mToolbarMorphDrawable.animateIconState(MaterialMenuDrawable
                    .IconState.BURGER);
            // mDrawerToggle.setHomeAsUpIndicator(drawable);

            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            //mDrawerToggle.setHomeAsUpIndicator(drawable_back);
            mToolbarMorphDrawable.animateIconState(MaterialMenuDrawable
                    .IconState.ARROW);
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }

        switch (requestCode) {
            case (100):
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        if (bundle.containsKey("selectedLangualge")) {
                            initSelectedLanguage();
                            callCategoriesService();
                           // ((CustomTextView) findViewById(R.id.selected_language)).setText(bundle.getString("selectedLangualge"));
                        }

                    }


                    break;
                }
        }
    }

    @Override
    public void onResponse(String response, String methodName) {
     //   Log.e("", "methodName");
        final Gson gson = new Gson();

        if (methodName.equalsIgnoreCase(WebServiceList.CATEGORIES_LIST_LABEL)) {
       //     Log.e("response", response);
            GlobalMethods.hideProgress(progressDialog);
            if (!response.equalsIgnoreCase("")) {
                if (GlobalMethods.isValidJsonArray(response)) {

                    List<CategoryList_Data> categoryList_data;
                    Type listType = new TypeToken<List<CategoryList_Data>>() {
                    }.getType();
                    categoryList_data= new Gson().fromJson(response, listType);

                    if (categoryList_data!=null&&categoryList_data.size()>0){
                        addCategoryList(categoryList_data);
                    }


                }
            }

        }
    }

    private void addCategoryList(final List<CategoryList_Data> categoryList_data) {
        ((LinearLayout) findViewById(R.id.category_layout)).removeAllViews();
        for (int i=0;i<categoryList_data.size();i++){
            final View view = getLayoutInflater().inflate(R.layout.category_item_layout, ((LinearLayout) findViewById(R.id.category_layout)), false);
            CustomTextView category_name = view.findViewById(R.id.category_name);
            if (GlobalMethods.isNull(categoryList_data.get(i).getName())){
                category_name.setText(categoryList_data.get(i).getName());
            }

            category_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callCategoryActivity(categoryList_data.get((Integer) view.getTag()).getName(),categoryList_data.get((Integer) view.getTag()).getId());

                }
            });

             view.setTag(i);
            ((LinearLayout) findViewById(R.id.category_layout)).addView(view);

        }
    }

    private void callCategoryActivity(String name,int id) {

        Bundle bundle=new Bundle();
        bundle.putInt("category_id",id);
        bundle.putString("category_name",name);

        GlobalMethods.callForWordActivity(NewHomeActivity.this,CategoryActivity.class,bundle,false,true);
    }
}
