package com.industryhit.industryhit.presentation.splashscreen;

import android.os.Handler;
import android.view.WindowManager;

import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.home.activity.activities.HomeActivity;
import com.industryhit.industryhit.presentation.home.activity.activities.NewHomeActivity;
import com.industryhit.industryhit.presentation.login.activity.BaseActivity;
import com.industryhit.industryhit.presentation.login.activity.Login_Activity;

public class SplashScreen_Act extends BaseActivity {

    public static final int MULTIPLE_PERMISSIONS = 1245;
    private final int SPLASH_TIME_OUT = 5000;


    @Override
    protected int setLayoutResuourse() {
        setTheme(R.style.SplashTheme);
        return R.layout.activity_splash_screen;
    }
    @Override
    protected void initData() {
        callNextActivity();
    }


    @Override
    protected void initGUI() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }



    private void callNextActivity() {
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                GlobalMethods.callForWordActivity(SplashScreen_Act.this,NewHomeActivity.class,null,true,true);
            }
        }, SPLASH_TIME_OUT);
    }
}
