package com.industryhit.industryhit.presentation.login.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomEditText;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextView;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextViewBold;
import com.industryhit.industryhit.presentation.home.activity.NewHomeActivity;
import com.industryhit.industryhit.presentation.splashscreen.SplashScreen_Act;

public class Login_Activity extends BaseActivity implements
        View.OnClickListener{

    private CustomEditText email_or_phone,password;
    private GoogleApiClient mGoogleApiClient;
    @Override
    protected int setLayoutResuourse() {
        return R.layout.activity_login;
    }

    @Override
    protected void initGUI() {

        initViews();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        actionEvents();
    }

    private void initViews() {

        email_or_phone=(CustomEditText)findViewById(R.id.email_or_phone);
        password=(CustomEditText)findViewById(R.id.password);
    }


    private void actionEvents() {
        ((CustomTextView)findViewById(R.id.login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkMandatoryFields()) {
                    callHomeActivity();
                }
            }
        });

        ((CustomTextViewBold)findViewById(R.id.signup)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegistrationAct();
            }
        });
    }



    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {

    }

    private boolean checkMandatoryFields() {
        if (!GlobalMethods.isNull(email_or_phone.getText().toString())) {
            GlobalMethods.setEditextError(Login_Activity.this, getString(R.string.this_field_required), email_or_phone);
            return false;
        }

        else if (!GlobalMethods.isNull(password.getText().toString())) {
            GlobalMethods.setEditextError(Login_Activity.this, getString(R.string.this_field_required), password);

            return false;
        }
        else {
            return true;
        }
    }
    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    private void callRegistrationAct() {
        GlobalMethods.callForWordActivity(Login_Activity.this,RegistrationAct.class,null,false,true);
    }


    private void callHomeActivity() {
        GlobalMethods.callForWordActivity(Login_Activity.this,NewHomeActivity.class,null,false,true);

    }

    @Override
    public void onClick(View v) {

    }
}
