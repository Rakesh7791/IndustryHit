package com.industryhit.industryhit.presentation.globalutils.constants;

import android.content.Context;

public class VersionControls {

    public static boolean isUpdated = false;

    private static VersionControls instance = null;

    private String englishUrl = EnglishURL.PRE_Production.getURL();
    private String telugulUrl = TelugulUrl.PRE_Production.getRMAURLS();


    private int SyncFreq = 5;

    public static VersionControls getVersionControls(Context context) {
        if (isUpdated || instance == null)
            instance = new VersionControls(context);
        return instance;
    }

    public VersionControls(Context context) {

    }

    public String getTelugulUrl() {
        return telugulUrl;
    }

    public void setTelugulUrl(String telugulUrl) {
        this.telugulUrl = telugulUrl;
    }

    public String getEnglishUrl() {
        return englishUrl;
    }

    public void setEnglishUrl(String englishUrl) {
        this.englishUrl = englishUrl;
    }


    static enum EnglishURL {
        //        http://35.187.232.245/
        //http://35.186.151.92/ changed to http://35.187.232.245/ on 16-12-2017
        //35.186.157.60        chharodev.bob.bt
        //http://35.198.255.206/==dev
        //http://35.185.101.252/==new instance

        //old prodution url : https://chharouat.bob.bt/


        Development(
                "http://35.200.150.70/"), Staging(
                "http://35.200.134.211/"), PRE_Production(
                "http://35.200.200.118/"),Production(
                "https://chharouat.bob.bt/");
        //
//        ?amount=1&currency=inr&quoteId=3423
        private String URL;

        EnglishURL(String URL) {
            this.URL = URL;
        }


        public void setURL(String URL) {
            this.URL = URL;
        }

        public String getURL() {
            return this.URL;
        }
    }


    static enum TelugulUrl {
        //        http://35.187.232.245/
        //http://35.186.151.92/ changed to http://35.187.232.245/ on 16-12-2017
        Development(
                "http://uatbfssecure.rma.org.bt:8080/"), Staging(
                "http://uatbfssecure.rma.org.bt:8080/"),PRE_Production(
                "https://bfssecure.rma.org.bt/"), Production(
                "https://bfssecure.rma.org.bt/");
        //
//        ?amount=1&currency=inr&quoteId=3423
        private String RMAURLS;

        TelugulUrl(String URL) {
            this.RMAURLS = URL;
        }


        public void setRMAURLS(String URL) {
            this.RMAURLS = URL;
        }

        public String getRMAURLS() {
            return this.RMAURLS;
        }
    }


    //http://172.16.0.135:1224/#/dashboard/ --old url
    static enum PaymentGateWayURLS {
        Development(
                "http://35.200.150.70/"), Staging(
                "http://35.200.134.211/"), PRE_Production(
                "http://35.200.200.118/"), Production(
                "https://chharouat.bob.bt/");


        //  Log.e("load 11-", s_URL);

        //

        public String getPayment_base_url() {
            return payment_base_url;
        }

        public void setPayment_base_url(String payment_base_url) {
            this.payment_base_url = payment_base_url;
        }

        private String payment_base_url;

        PaymentGateWayURLS(String payment_base_url) {
            this.payment_base_url = payment_base_url;
        }


    }



    /**
     * @return the syncFreq
     */
    public int getSyncFreq() {
        return SyncFreq;
    }

    /**
     * @param syncFreq the syncFreq to set
     */
    public void setSyncFreq(int syncFreq) {
        SyncFreq = syncFreq;
    }





}
