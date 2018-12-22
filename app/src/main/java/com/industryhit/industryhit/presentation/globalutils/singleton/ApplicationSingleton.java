package com.industryhit.industryhit.presentation.globalutils.singleton;

/**
 * Created by Administrator on 06-10-2017.
 */

public class ApplicationSingleton {

    private static String sessionId="";
    private static ApplicationSingleton mInstance;

    public static ApplicationSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new ApplicationSingleton();
        }
        return mInstance;
    }

    public String getSessionId() {
      //  return BaseActivity.mCustomerDataShared.getString("sessionId", sessionId);
        return "";
    }

    public void setSessionId(String sessionId) {
//        BaseActivity.mCustomerDataSharedEditor.putString("sessionId", sessionId);
//        BaseActivity.mCustomerDataSharedEditor.apply();
        ApplicationSingleton.sessionId = sessionId;
    }

}
