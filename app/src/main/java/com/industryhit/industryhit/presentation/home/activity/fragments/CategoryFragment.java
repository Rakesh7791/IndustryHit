package com.industryhit.industryhit.presentation.home.activity.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.industryhit.industryhit.R;
import com.industryhit.industryhit.businesslogic.model.CategoryData;
import com.industryhit.industryhit.presentation.globalutils.adapter.CategoryAdapter;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.home.activity.activities.CategoryActivity;
import com.industryhit.industryhit.webaccess.OnVolleyResponseListener;
import com.industryhit.industryhit.webaccess.VolleyConnectionGET;
import com.industryhit.industryhit.webaccess.WebServiceList;

import java.lang.reflect.Type;
import java.util.List;


public class CategoryFragment extends BaseFragment implements OnVolleyResponseListener {

    private int category_id;
    private ProgressDialog progressDialog;
    private RecyclerView category_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_category, container, false);;

        initViews(view);
        readBundle();
        return view;


    }

    private void initViews(View view) {

        category_list=(RecyclerView)view.findViewById(R.id.category_list);
    }

    private void readBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("category_id")) {
                Log.e("category_id",bundle.getInt("category_id")+"");
                category_id=bundle.getInt("category_id");

                getCategoryData(category_id);
            }

        }
    }

    private void getCategoryData(int category_id) {
        progressDialog = GlobalMethods.showProgress(getActivity());

        new VolleyConnectionGET(getActivity(), this).execute(WebServiceList.POST_CATEGORIES_LABEL,category_id+"");


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

        CategoryAdapter menuListAdapter = new CategoryAdapter(getActivity(),  categoryData);

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        category_list.setLayoutManager(horizontalLayoutManagaer);
        category_list.setAdapter(menuListAdapter);
        category_list.setNestedScrollingEnabled(false);
    }
}
