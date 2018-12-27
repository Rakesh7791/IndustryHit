package com.industryhit.industryhit.presentation.home.activity.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.industryhit.industryhit.R;
import com.industryhit.industryhit.businesslogic.model.CategoryData;
import com.industryhit.industryhit.presentation.globalutils.adapter.CategoryAdapter;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextView;
import com.industryhit.industryhit.presentation.login.activity.BaseActivity;
import com.industryhit.industryhit.webaccess.OnVolleyResponseListener;
import com.industryhit.industryhit.webaccess.VolleyConnectionGET;
import com.industryhit.industryhit.webaccess.WebServiceList;

import java.lang.reflect.Type;
import java.util.List;

public class CategoryActivity extends BaseActivity implements OnVolleyResponseListener {

    private ProgressDialog progressDialog;
    private RecyclerView category_list;

    @Override
    protected int setLayoutResuourse() {
        return R.layout.activity_category;
    }

    @Override
    protected void initGUI() {
        initViews();

        readBundle();
        actionEvents();
    }


    private void initViews() {
        category_list=(RecyclerView)findViewById(R.id.category_list);
    }

    private void readBundle() {

        Bundle  bundle = getIntent().getExtras();
        if (bundle!=null){

            if (bundle.containsKey("category_name")){
                ((CustomTextView)findViewById(R.id.header_name)).setText(bundle.getString("category_name"));
            }

            if (bundle.containsKey("category_id")){
                int category_Id=bundle.getInt("category_id");

                if (GlobalMethods.isNull(String.valueOf(category_Id))){
                    callPostCategoriesService(category_Id);
                }
            }
        }
    }

    private void actionEvents() {
        ((ImageView)findViewById(R.id.ic_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackActivity();
            }
        });
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
       // Log.e("category_Id",category_Id+"");
        progressDialog = GlobalMethods.showProgress(CategoryActivity.this);

        new VolleyConnectionGET(CategoryActivity.this, this).execute(WebServiceList.POST_CATEGORIES_LABEL,category_Id+"");

    }

    @Override
    public void onResponse(String response, String methodName) {

        Log.e("", "methodName");
        final Gson gson = new Gson();

        if (methodName.equalsIgnoreCase(WebServiceList.POST_CATEGORIES_LABEL)) {
            //     Log.e("response", response);
            GlobalMethods.hideProgress(progressDialog);
            if (!response.equalsIgnoreCase("")) {
                if (GlobalMethods.isValidJsonArray(response)) {

                    List<CategoryData> categoryData;
                    Type listType = new TypeToken<List<CategoryData>>() {
                    }.getType();
                    categoryData= new Gson().fromJson(response, listType);

                    if (categoryData!=null&&categoryData.size()>0){
                        addCategoryData(categoryData);
                    }


                }
            }

        }
    }

    private void addCategoryData(List<CategoryData> categoryData) {
        CategoryAdapter menuListAdapter = new CategoryAdapter(CategoryActivity.this,  categoryData);

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(CategoryActivity.this, LinearLayoutManager.VERTICAL, false);

        category_list.setLayoutManager(horizontalLayoutManagaer);
        category_list.setAdapter(menuListAdapter);
        category_list.setNestedScrollingEnabled(false);
    }
}
