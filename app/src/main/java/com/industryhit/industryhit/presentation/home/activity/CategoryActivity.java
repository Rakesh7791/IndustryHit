package com.industryhit.industryhit.presentation.home.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.login.activity.BaseActivity;
import com.industryhit.industryhit.webaccess.OnVolleyResponseListener;
import com.industryhit.industryhit.webaccess.VolleyConnectionGET;
import com.industryhit.industryhit.webaccess.WebServiceList;

public class CategoryActivity extends BaseActivity implements OnVolleyResponseListener {


    @Override
    protected int setLayoutResuourse() {
        return R.layout.activity_category;
    }

    @Override
    protected void initGUI() {

        readBundle();

    }

    private void readBundle() {

        Bundle  bundle = getIntent().getExtras();
        if (bundle!=null){

            if (bundle.containsKey("category_Id")){
                int category_Id=bundle.getInt("category_Id");

                if (GlobalMethods.isNull(String.valueOf(category_Id))){
                    callPostCategoriesService(category_Id);
                }
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        callBackActivity();
    }

    private void callBackActivity() {
        GlobalMethods.callFinishForBackWordActivity(CategoryActivity.this, false);

    }

    private void callPostCategoriesService(int category_Id) {
        Log.e("category_Id",category_Id+"");
        new VolleyConnectionGET(CategoryActivity.this, this).execute(WebServiceList.POST_CATEGORIES_LABEL);

    }

    @Override
    public void onResponse(String response, String methodName) {

        Log.e("", "methodName");
        final Gson gson = new Gson();
        if (methodName.equalsIgnoreCase(WebServiceList.CATEGORIES_LIST_LABEL)) {
            Log.e("response", response);

            if (!response.equalsIgnoreCase("")) {
                if (GlobalMethods.isValidJsonArray(response)) {
                }
            }
        }
    }
}
