package com.industryhit.industryhit.presentation.login.activity;

import android.view.View;
import android.widget.ImageView;

import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomEditText;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextView;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextViewBold;
import com.industryhit.industryhit.presentation.home.activity.activities.HomeActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationAct extends BaseActivity {

    private CustomEditText name,email_or_phone,password;

    @Override
    protected int setLayoutResuourse() {
        return R.layout.activity_registration;
    }

    @Override
    protected void initGUI() {

        initViews();
        actionEvents();
    }

    private void initViews() {
        name=(CustomEditText)findViewById(R.id.name);
        email_or_phone=(CustomEditText)findViewById(R.id.email_or_phone);
        password=(CustomEditText)findViewById(R.id.password);
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

        ((CustomTextViewBold)findViewById(R.id.login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callLogin_Activity();

            }
        });

        ((CustomTextView)findViewById(R.id.signup)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkMandatoryFields()) {
                    callHomeActivity();
                }
            }
        });
    }


    private void callLogin_Activity() {
        GlobalMethods.callForWordActivity(RegistrationAct.this,Login_Activity.class,null,false,true);

    }

    private void callHomeActivity() {
        GlobalMethods.callForWordActivity(RegistrationAct.this,HomeActivity.class,null,false,true);

    }

    private boolean checkMandatoryFields() {
        if (!GlobalMethods.isNull(name.getText().toString())) {
            GlobalMethods.setEditextError(RegistrationAct.this, getString(R.string.this_field_required), name);
            return false;
        }

        else if (!GlobalMethods.isNull(email_or_phone.getText().toString())) {
            GlobalMethods.setEditextError(RegistrationAct.this, getString(R.string.this_field_required), email_or_phone);
            return false;
        }
        else if (!validateEmailOrMobile(email_or_phone.getText().toString())){
            GlobalMethods.setEditextError(RegistrationAct.this, getString(R.string.please_enter_valid_email_or_phonenumber), email_or_phone);
            return false;
        }

        else if (!GlobalMethods.isNull(password.getText().toString())) {
            GlobalMethods.setEditextError(RegistrationAct.this, getString(R.string.this_field_required), password);
            return false;
        }
        else {
            return true;
        }
    }

    private boolean validateEmailOrMobile(String email_or_phone) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email_or_phone);
        if (email_or_phone.length()==10&&!GlobalMethods.isInteger(email_or_phone)){
            return false;
        }else if(!mat.matches()){
            return false;
        }
        else {
            return true;
        }
    }
    @Override
    public void onBackPressed() {
        callBackActivity();

    }

    private void callBackActivity() {
        GlobalMethods.callFinishForBackWordActivity(RegistrationAct.this, false);

    }
}
