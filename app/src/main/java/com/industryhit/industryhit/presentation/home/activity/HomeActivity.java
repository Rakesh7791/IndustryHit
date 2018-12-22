package com.industryhit.industryhit.presentation.home.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextView;
import com.industryhit.industryhit.presentation.login.activity.BaseActivity;
import com.industryhit.industryhit.presentation.navigationscreens.BoxOffice_Fragment;
import com.industryhit.industryhit.presentation.navigationscreens.Exclusives_Fragment;
import com.industryhit.industryhit.presentation.navigationscreens.Galleries_Fragment;
import com.industryhit.industryhit.presentation.navigationscreens.Gossips_Fragment;
import com.industryhit.industryhit.presentation.navigationscreens.HomeFragment;
import com.industryhit.industryhit.presentation.navigationscreens.Interviews_Fragment;
import com.industryhit.industryhit.presentation.navigationscreens.News_Fragment;
import com.industryhit.industryhit.presentation.navigationscreens.Reviews_Fragment;
import com.industryhit.industryhit.presentation.navigationscreens.Videos_Fragment;
import com.industryhit.industryhit.presentation.selectlanguage.Select_LanguageAct;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    int backButtonClick = 1;

    @Override
    protected void initData() {

    }

    @Override
    protected void initGUI() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       /* CustomTextView selected_language=(CustomTextView)navigationView.findViewById(R.id.selected_language);

        selected_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callSelect_LanguageAct();
            }
        });
*/




    }

    private void callSelect_LanguageAct() {

        GlobalMethods.callForWordActivity(HomeActivity.this,Select_LanguageAct.class,null,true,true);

    }

    @Override
    protected int setLayoutResuourse() {
        return R.layout.activity_home;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {
            // Handle the camera action
            replaceFragment( new HomeFragment());
        } else if (id == R.id.nav_news) {
            ((CustomTextView)findViewById(R.id.header_name)).setText(getResources().getString(R.string.news));
            replaceFragment( new News_Fragment());

        } else if (id == R.id.nav_reviews) {
            ((CustomTextView)findViewById(R.id.header_name)).setText(getResources().getString(R.string.reviews));
            replaceFragment( new Reviews_Fragment());

        } else if (id == R.id.nav_gossips) {
            ((CustomTextView)findViewById(R.id.header_name)).setText(getResources().getString(R.string.gossips));
            replaceFragment( new Gossips_Fragment());
        }
        else if (id == R.id.nav_exclusives) {
            ((CustomTextView)findViewById(R.id.header_name)).setText(getResources().getString(R.string.exclusives));
             replaceFragment( new Exclusives_Fragment());

        }else if (id == R.id.nav_boxoffice) {
            ((CustomTextView)findViewById(R.id.header_name)).setText(getResources().getString(R.string.exclusives));
            replaceFragment( new BoxOffice_Fragment());

        }else if (id == R.id.nav_interviews) {
            ((CustomTextView)findViewById(R.id.header_name)).setText(getResources().getString(R.string.interviews));
            replaceFragment( new Interviews_Fragment());
        }else if (id == R.id.nav_galleries) {
            ((CustomTextView)findViewById(R.id.header_name)).setText(getResources().getString(R.string.galleries));
            replaceFragment( new Galleries_Fragment());
        }
        else if (id == R.id.nav_videos) {
            ((CustomTextView)findViewById(R.id.header_name)).setText(getResources().getString(R.string.videos));
            replaceFragment( new Videos_Fragment());
        }



//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {

        Bundle bundle=new Bundle();
        try {

            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();

            Bundle b = new Bundle();
            mFragmentTransaction.replace(R.id.containerViews, fragment);
            mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            mFragmentTransaction.commit();

            closeDrawer();


        } catch (Exception e) {
            // TODO: handle exception
            Log.e("excep",e.toString());
        }

    }

    private void closeDrawer() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
