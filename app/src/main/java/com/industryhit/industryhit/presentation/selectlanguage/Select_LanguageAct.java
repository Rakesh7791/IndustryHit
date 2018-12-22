package com.industryhit.industryhit.presentation.selectlanguage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextView;
import com.industryhit.industryhit.presentation.home.activity.HomeActivity;
import com.industryhit.industryhit.presentation.login.activity.BaseActivity;
import com.industryhit.industryhit.presentation.splashscreen.SplashScreen_Act;

public class Select_LanguageAct extends BaseActivity {


    @Override
    protected int setLayoutResuourse() {
        return R.layout.activity_select__language;
    }

    @Override
    protected void initGUI() {

        actionEvents();

    }

    private void actionEvents() {

        ((CustomTextView)findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ((CustomTextView)findViewById(R.id.telugu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateLanguage(getResources().getString(R.string.telugu));
            }
        });

        ((CustomTextView)findViewById(R.id.english)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLanguage(getResources().getString(R.string.english));

            }
        });
    }

    private void updateLanguage(String selected_Language) {
        if (selected_Language.equalsIgnoreCase(getResources().getString(R.string.telugu))){

            GlobalMethods.setSelectedLanguage(Select_LanguageAct.this,0);
        }else {
            GlobalMethods.setSelectedLanguage(Select_LanguageAct.this,1);
        }

        GlobalMethods.callForWordActivity(Select_LanguageAct.this,HomeActivity.class,null,true,true);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        callBackforwaAcivity();
    }

    private void callBackforwaAcivity() {

        GlobalMethods.callFinishForBackWordActivity(Select_LanguageAct.this, false);
    }

}
