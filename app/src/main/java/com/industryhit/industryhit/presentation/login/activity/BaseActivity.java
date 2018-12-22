package com.industryhit.industryhit.presentation.login.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public abstract class BaseActivity extends AppCompatActivity {

    private SharedPreferences mCustomerDataShared;
    private SharedPreferences.Editor mCustomerDataSharedEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(setLayoutResuourse());
        mCustomerDataShared = getSharedPreferences("customerPreference", MODE_PRIVATE);
        mCustomerDataSharedEditor = mCustomerDataShared.edit();

        initGUI();
        initData();
        
        
    }
    protected abstract int setLayoutResuourse();
    protected abstract void initGUI();
    protected abstract void initData();



}
