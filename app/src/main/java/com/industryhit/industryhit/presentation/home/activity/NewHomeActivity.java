package com.industryhit.industryhit.presentation.home.activity;

import android.app.Activity;
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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextView;
import com.industryhit.industryhit.presentation.login.activity.BaseActivity;
import com.industryhit.industryhit.presentation.selectlanguage.Select_LanguageAct;
import com.industryhit.industryhit.webaccess.OnVolleyResponseListener;
import com.industryhit.industryhit.webaccess.VolleyConnectionGET;
import com.industryhit.industryhit.webaccess.WebServiceList;

import java.util.List;

public class NewHomeActivity extends BaseActivity implements OnVolleyResponseListener{



    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    private MaterialMenuDrawable mToolbarMorphDrawable;
    @Override
    protected int setLayoutResuourse() {
        return R.layout.activity_new_home;
    }

    @Override
    protected void initGUI() {
        initDrawaerView();
        actionEvents();

    }

    private void actionEvents() {
        ((LinearLayout)findViewById(R.id.language_layout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSelectLanguageAct();
            }
        });
    }

    private void callSelectLanguageAct() {
        GlobalMethods.callForWordActivityForResult(NewHomeActivity.this, Select_LanguageAct.class,null,100,true);
    }

    @Override
    protected void initData() {
        callCategoriesService();

    }

    private void callCategoriesService() {
        new VolleyConnectionGET(NewHomeActivity.this,this).execute(WebServiceList.CATEGORIES_LIST_LABEL);

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

        mToolbarMorphDrawable = new MaterialMenuDrawable(this, Color.WHITE,
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

        switch (requestCode) {
            case (100):
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle=data.getExtras();
                    if(bundle!=null){
                        if(bundle.containsKey("selectedLangualge")){
                            ((CustomTextView)findViewById(R.id.selected_language)).setText(bundle.getString("selectedLangualge"));
                        }

                    }


                    break;
                }
        }
    }

    @Override
    public void onResponse(String response, String methodName) {
        Log.e("","methodName");

    }
}
