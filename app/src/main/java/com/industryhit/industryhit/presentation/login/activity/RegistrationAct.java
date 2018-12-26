package com.industryhit.industryhit.presentation.login.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.home.activity.CategoryActivity;

public class RegistrationAct extends BaseActivity {


    @Override
    protected int setLayoutResuourse() {
        return R.layout.activity_registration;
    }

    @Override
    protected void initGUI() {

        actionEvents();
    }



    @Override
    protected void initData() {

    }


    private void actionEvents() {

        ((ImageView)findViewById(R.id.img_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callBackActivity();
            }
        });

        ((ImageView)findViewById(R.id.img_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        callBackActivity();

    }

    private void callBackActivity() {
        GlobalMethods.callFinishForBackWordActivity(RegistrationAct.this, false);

    }
}
