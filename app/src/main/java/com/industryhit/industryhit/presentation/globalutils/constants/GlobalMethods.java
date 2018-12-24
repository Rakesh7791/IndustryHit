package com.industryhit.industryhit.presentation.globalutils.constants;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.singleton.SingletonClass;
import com.industryhit.industryhit.presentation.globalutils.utills.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;



/**
 * Created by Administrator on 21-09-2017.
 */

public class GlobalMethods {

    public static boolean isOnline(Context context) throws Exception {
        try {
            return SingletonClass.getInstance().isDeviceOnline(context);
        } catch (Exception e) {
            throw e;
        }

    }

    public static boolean isNull(String data) {

        boolean isnull = false;
        if (data != null) {
            if (!data.equals("") && !data.equals("null") && data != null
                    && !data.equals("-1") && !data.equalsIgnoreCase("NULL")) {
                isnull = true;
            }
        }

        return isnull;
    }

    public static boolean isNullCheckWithZero(String data) {

        boolean isnull = false;
        if (data != null) {
            if (!data.equals("") && !data.equals("null") && data != null
                    && !data.equals("-1") && !data.equals("0")) {
                isnull = true;
            }
        } else {
            isnull = false;
        }

        return isnull;
    }


    public static boolean isValidJson(String test) {

        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            return false;
        }
        return true;
    }

    public static boolean isValidJsonArray(String test) {

        try {
            new JSONArray(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...

            return false;
        }
        return true;
    }

    public static ProgressDialog showProgress(Context context) {

        ProgressDialog progress = ProgressDialog.show(context, null, null);
//        progress.setMessage(message);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setContentView(
                R.layout.progress_layout);
        progress.setCancelable(false);
        progress.show();
        return progress;


    }

    public static ProgressDialog showProgressWithCancelable(Context context) {

        ProgressDialog progress = ProgressDialog.show(context, null, null);
//        progress.setMessage(message);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setContentView(R.layout.progress_layout);
        progress.setCancelable(true);
        progress.show();
        return progress;


    }

    public static void hideProgress(ProgressDialog dialog) {

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * documentation
     *
     * @param activity   represents activity reference
     * @param messageRef is string reference id
     * @param duration   paramenter indicates show the duration of the toast this values as follows Configuration.DURATION_LONG,Configuration.DURATION_SHORT,Configuration.DURATION_LINFINITE
     */

    public static void showToast(Activity activity, int messageRef,  int duration) throws Exception {
        try {
            //SingletonClass.getInstance().shotToast(activity, messageRef, style, view, duration, isFromTabActivity);
            if (duration == 0) {
                duration = Toast.LENGTH_LONG;
            }
            Toast.makeText(activity, activity.getResources().getString(messageRef), duration).show();
        } catch (Exception e) {
            throw e;
        }

    }

    public static void showNormalToast(Activity activity, int messageRef, ViewGroup view, int duration, boolean isFromTabActivity) throws Exception {
        try {
            //SingletonClass.getInstance().shotToast(activity, messageRef, duration, isFromTabActivity);
            if (duration == 0) {
                duration = Toast.LENGTH_LONG;
            }
            Toast.makeText(activity, activity.getResources().getString(messageRef), duration).show();
        } catch (Exception e) {
            throw e;
        }

    }


    /**
     * documentation
     *
     * @param activity represents activity reference
     * @param message  is string message in String formate
     * @param duration paramenter indicates show the duration of the toast this values as follows Configuration.DURATION_LONG,Configuration.DURATION_SHORT,Configuration.DURATION_LINFINITE
     */

    public static void showToast(Activity activity, String message,  int duration) {
        try {
            // SingletonClass.getInstance().shotToast(activity, message, style, view, duration, isFromTabActivity);

            if (duration == 0) {
                duration = Toast.LENGTH_LONG;
            }
            Toast.makeText(activity, message, duration).show();
        } catch (Exception e) {
            throw e;
        }

    }


    public static String getDeviceUUID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getStateName(Context context, Location location) {
        try {
            Geocoder gcd = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String countryName = "";
            if (addresses.size() > 0) {
                countryName = addresses.get(0).getAdminArea();
            }
            return countryName;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getCountryName(Context context, Location location) {
        try {
            Geocoder gcd = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String countryName = "";
            if (addresses.size() > 0) {
                countryName = addresses.get(0).getCountryName();
            }
            return countryName;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getDeviceID(Context applicationContext) {
        if (applicationContext != null) {
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
                return telephonyManager.getDeviceId();
            } else {
                return "Permission Not Granted";
            }
        } else {
            return "";
        }

    }

    public static String getDeviceSimNo(Context applicationContext) {
        if (applicationContext != null) {
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
                return telephonyManager.getSimSerialNumber();
            } else {
                return "Permission Not Granted";
            }
        } else {
            return "";
        }
    }

    public static String getDeviceMobileNumber(Context applicationContext) {
        if (applicationContext != null) {
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
                return telephonyManager.getLine1Number();
            } else {
                return "Permission Not Granted";
            }
        } else {
            return "";
        }
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }


    public static String getCurrentDate() {
        // TODO Auto-generated method stub
        SimpleDateFormat smdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date(System.currentTimeMillis());
        return smdf.format(d);
    }

    public static String getCurrentDateSpecifiedFormate(String fomate) {
        // TODO Auto-generated method stub
        SimpleDateFormat smdf = new SimpleDateFormat(fomate,Locale.US);
        Date d = new Date(System.currentTimeMillis());
        return smdf.format(d);
//        Date date;
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//
//        try {
//            date = df.parse(String.valueOf(_time.getTime()));
//        } catch (ParseException e) {
//            throw new RuntimeException("Failed to parse date: ", e);
//        }
//
//        return date.getTime();
    }

    public static long getCurrentTimeInMilliSeconds() {
        return System.currentTimeMillis();
    }


    public static void setStatusBarColor(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

    }

//    public static boolean checkPermistion(Context applicationContext, String accessCoarseLocation) {
//        if (applicationContext != null) {
//            if (Build.VERSION.SDK_INT >= 23) {
//                return ContextCompat.checkSelfPermission(applicationContext, accessCoarseLocation) != PackageManager.PERMISSION_GRANTED;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//
//    }

    public static void callForWordActivity(Context context, Class forwardClass, Bundle bundle, boolean isActivityFinish, boolean isForwardAnimation) {
        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        context.startActivity(myIntent);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in,
                    R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right,
                    R.anim.slide_right_in);
        }
        if (isActivityFinish)
            ((Activity) context).finish();
    }


    public static void callForWordActivityFromAdapter(Context context, Class forwardClass, Bundle bundle, boolean isActivityFinish, boolean isForwardAnimation) {
        Intent myIntent = new Intent(context, forwardClass);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        context.startActivity(myIntent);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in,
                    R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right,
                    R.anim.slide_right_in);
        }
        if (isActivityFinish)
            ((Activity) context).finish();
    }


    public static void callForWordActivityWithClearTop(Context context, Class forwardClass, Bundle bundle, boolean isActivityFinish, boolean isForwardAnimation) {
        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(myIntent);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in,
                    R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right,
                    R.anim.slide_right_in);
        }
        if (isActivityFinish)
            ((Activity) context).finish();
    }

    public static void callBackWordActivity(Context context, Class forwardClass, Bundle bundle, boolean isActivityFinish, boolean isForwardAnimation) {
        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        context.startActivity(myIntent);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in,
                    R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right,
                    R.anim.slide_right_in);
        }
        if (isActivityFinish)
            ((Activity) context).finish();
    }

    public static void callFinishForBackWordActivity(Context context, boolean isForwardAnimation) {
        try {

            ((Activity) context).finish();
            if (isForwardAnimation) {
                ((Activity) context).overridePendingTransition(R.anim.slide_left_in,
                        R.anim.slide_out_left);
            } else {
                ((Activity) context).overridePendingTransition(R.anim.slide_out_right,
                        R.anim.slide_right_in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void callForWordActivityForResult(Context context, Class forwardClass, Bundle bundle, int ResultCode, boolean isForwardAnimation) {

        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        ((Activity) context).startActivityForResult(myIntent, ResultCode);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in,
                    R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right,
                    R.anim.slide_right_in);
        }

    }

    public static void callBackWordActivityForResult(Context context, Bundle bundle, int ResultCode, boolean isForwardAnimation) {

        Intent myIntent = new Intent();
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        ((Activity) context).setResult(Activity.RESULT_OK, myIntent);
        ((Activity) context).finish();
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in,
                    R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right,
                    R.anim.slide_right_in);
        }


    }

    public static void loadImage(final Context context, final ProgressBar progressBar, final String url, final ImageView viewById, final int isFromBanner) {

        Glide.with(context).load(url)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        if (progressBar != null)
//                            progressBar.setVisibility(View.GONE);
//                        switch (isFromBanner) {
//                            case Constants.IS_FROM_RHB_FRAG_BANNER_IMAG_LOAD:
//                                Constants.IS_RHB_FRAG_BANNER_IMAG_LOAD = true;
//                                break;
//                            case Constants.IS_FROM_RHP_FRAG_BANNER_IMAG_LOAD:
//                                Constants.IS_RHP_FRAG_BANNER_IMAG_LOAD = true;
//                                break;
//                            case Constants.IS_FROM_RHS_FRAG_BANNER_IMAG_LOAD:
//                                Constants.IS_RHS_FRAG_BANNER_IMAG_LOAD = true;
//                                break;
//                            case Constants.IS_FROM_THB_FRAG_BANNER_IMAG_LOAD:
//                                Constants.IS_THB_FRAG_BANNER_IMAG_LOAD = true;
//                                break;
//                            case Constants.IS_FROM_THC_FRAG_BANNER_IMAG_LOAD:

//                                Constants.IS_THC_FRAG_BANNER_IMAG_LOAD = true;
//                                break;
//                            case Constants.IS_FROM_THS_FRAG_BANNER_IMAG_LOAD:
//                                Constants.IS_THS_FRAG_BANNER_IMAG_LOAD = true;
//                                break;
//
//                        }
                        Glide.with(context).load(url).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(viewById);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        switch (isFromBanner) {

                        }
                        return false;
                    }
                })
                .crossFade()
                .into(viewById);
    }


    public static void loadImageWithOutCentCrop(final Context context, final ProgressBar progressBar, final String url, final ImageView viewById, final int isFromBanner) {

        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        if (progressBar != null)
//                            progressBar.setVisibility(View.GONE);
//                        switch (isFromBanner) {
//                            case Constants.IS_FROM_RHB_FRAG_BANNER_IMAG_LOAD:
//                                Constants.IS_RHB_FRAG_BANNER_IMAG_LOAD = true;
//                                break;
//                            case Constants.IS_FROM_RHP_FRAG_BANNER_IMAG_LOAD:
//                                Constants.IS_RHP_FRAG_BANNER_IMAG_LOAD = true;
//                                break;
//                            case Constants.IS_FROM_RHS_FRAG_BANNER_IMAG_LOAD:
//                                Constants.IS_RHS_FRAG_BANNER_IMAG_LOAD = true;
//                                break;
//                            case Constants.IS_FROM_THB_FRAG_BANNER_IMAG_LOAD:
//                                Constants.IS_THB_FRAG_BANNER_IMAG_LOAD = true;
//                                break;
//                            case Constants.IS_FROM_THC_FRAG_BANNER_IMAG_LOAD:

//                                Constants.IS_THC_FRAG_BANNER_IMAG_LOAD = true;
//                                break;
//                            case Constants.IS_FROM_THS_FRAG_BANNER_IMAG_LOAD:
//                                Constants.IS_THS_FRAG_BANNER_IMAG_LOAD = true;
//                                break;
//
//                        }
                        Glide.with(context).load(url).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(viewById);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);

                        return false;
                    }
                })
                .crossFade()
                .into(viewById);
    }

    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static boolean isGPSON(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    public static void convertEditTextAsTextView(EditText editText) {
        editText.setLongClickable(false);
        editText.setFocusable(false);
        editText.setCursorVisible(false);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
    }

    public static CharSequence setEditextError(Context context, String str, EditText editext) {

        editext.requestFocus();
        editext.setError(str);

        return "";
    }

    public static CharSequence setEditextError(Context context, String str, AppCompatEditText editext) {

        editext.requestFocus();
        editext.setError(str);

        return "";
    }

    public static CharSequence setEditextError(Context context, String str, TextView editext) {
        editext.requestFocus();
        editext.setError(str);

        return "";
    }

    public static Drawable changeDrawableColor(Context context, int icon, int newColor) {
        Drawable mDrawable = ContextCompat.getDrawable(context, icon).mutate();
        // mDrawable.setColorFilter(new PorterDuffColorFilter(newColor, PorterDuff.Mode.SRC_IN));
        mDrawable.setColorFilter(newColor, PorterDuff.Mode.MULTIPLY);
        return mDrawable;
    }

    public static int getPixToDP(Context context, int paddingPixel) {
        float density = context.getResources().getDisplayMetrics().density;
        int paddingDp = (int) (paddingPixel * density);
        return paddingDp;
    }









    public static void showNormalToast(Activity activity, String message, int lengthLong) {
        try {
            // GlobalMethods.showNormalToast(activity, message, lengthLong);

            try {
                //SingletonClass.getInstance().shotToast(activity, messageRef, duration, isFromTabActivity);
                if (lengthLong == 0) {
                    lengthLong = Toast.LENGTH_LONG;
                }
                Toast.makeText(activity, message, lengthLong).show();
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {

        }
    }

    public static String GetDeviceWidth(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        return width + "";


    }

    public static Float convertAmountToFloat(String price) {
        if (isNull(price)) {
            String formatedPriceArray[] = StringUtils.split(price, " ");
            if (formatedPriceArray.length > 1) {
                return Float.parseFloat(formatedPriceArray[1].trim());
            } else {
                return Float.parseFloat(price);
            }

        } else {
            return 0.00f;
        }

    }

    public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    public static String getAmountCurrnecy(String price) {
        if (isNull(price)) {
            String formatedPriceArray[] = StringUtils.split(price, " ");
            if (formatedPriceArray.length > 1) {
                return formatedPriceArray[0];
            } else {
                return "";
            }

        } else {
            return "";
        }

    }

    public static String formateToTwoDecimal1231(Float fomatedTotalPrice) {
        return String.format("%.2f%n", Math.floor(fomatedTotalPrice * 100) / 100).trim();
    }

    public static String getCurrntMonth() {

        try {
            java.util.Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
            return theMonth(month);
        } catch (Exception e) {

        }
        return "";
    }

    public static String theMonth(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }

    public static int getCurrnetYear() {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return currentYear;
    }










    public static String getMobileNumber(String mobileNo) {
        if (GlobalMethods.isNull(mobileNo)) {
            if (mobileNo.trim().length() == 10) {
                return mobileNo;
            } else if (mobileNo.trim().length() > 10) {
                return mobileNo.trim().substring(mobileNo.length() - 10);
            } else {
                throw new IllegalArgumentException("word has less than 10 characters!");
            }
        } else {
            return "";
        }
    }

    public static String visableAmountFormate(BigDecimal s) {

        String formatedAmount = IndianFormat(s);
        if (GlobalMethods.isNull(formatedAmount) && !formatedAmount.contains(",") && Double.parseDouble(formatedAmount) < 1) {
            return "Nu. 0" + formatedAmount;
        } else {
            return "Nu. " + formatedAmount;
        }
    }

    public static String visableAmountFormate(String s) {
        // return "Nu. " + IndianFormat(new BigDecimal(s));
        String formatedAmount = IndianFormat(new BigDecimal(s));
        if (GlobalMethods.isNull(formatedAmount) && !formatedAmount.contains(",") && Double.parseDouble(formatedAmount) < 1) {
            return "Nu. 0" + formatedAmount;
        } else {
            return "Nu. " + formatedAmount;
        }
    }

    public static AppCompatImageView generateImageView11(Context applicationContext) {
        AppCompatImageView imageview = new AppCompatImageView(applicationContext);

        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(getPixToDP(applicationContext, 35), getPixToDP(applicationContext, 35));
        params.setMargins(10, 0, 10, 0);

        // Add image path from drawable folder.
        //imageview.setImageResource(R.drawable.circle);
        imageview.setLayoutParams(params);
        return imageview;

    }

    public static int getColorCode(String label) {
        switch (label) {
            case "Blue":
                return R.color.blue;
            case "Brown":
                return R.color.brown;
            case "White":
                return R.color.white;

            case "Red":
                return R.color.red;
            case "Green":
                return R.color.green;
            case "Yellow":
                return R.color.Yellow;

            case "Black":
                return R.color.black;
            case "Gray":
                return R.color.grey_1;

            case "Lavender":
                return R.color.Lavender;

            case "Orange":
                return R.color.Orange;
            case "Purple":
                return R.color.Purple;
            default:
                return R.color.grey;


        }
    }


    public static void callTopActivityForResult(Context context, Class forwardClass, Bundle bundle, int ResultCode, boolean isForwardAnimation) {

        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        ((Activity) context).startActivityForResult(myIntent, ResultCode);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_in_up,
                    R.anim.stay);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.stay,
                    R.anim.slide_down);
        }

    }


    public static void callDownActivityForResult(Context context, Bundle bundle, int ResultCode, boolean isForwardAnimation) {

        Intent myIntent = new Intent();
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        ((Activity) context).setResult(Activity.RESULT_OK, myIntent);
        ((Activity) context).finish();
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_in_up,
                    R.anim.stay);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.stay,
                    R.anim.slide_down);
        }


    }

    public static ArrayList<String> getRecentlySearchedItems() {
        ArrayList<String> allRecentSearchedItems = new ArrayList<>();
        allRecentSearchedItems.add("TShirts");
        allRecentSearchedItems.add("Shirts");
        allRecentSearchedItems.add("Pants");
        return allRecentSearchedItems;

    }


    public static String visableDateFromate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");
        String dateconverted = null;
        try {
            Date d = dateFormat.parse(dateString);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd MMM yyyy hh:mm aa");
            dateconverted = dateFormat1.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateconverted;
    }

    @SuppressLint("SimpleDateFormat")
    public static String savedDateFormate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        String dateconverted = null;
        try {
            Date d = dateFormat.parse(dateString);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
            dateconverted = dateFormat1.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateconverted;

    }

    public static String visableFinalPricFormat(String balance) {
        if (balance.equalsIgnoreCase("0")) {
            return "Nu. 0.00";
        } else {
            return visableAmountFormate(GlobalMethods.round(GlobalMethods.convertAmountToFloat(balance), 2));
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    public static String replaceComma(String amount) {
        return amount.replaceAll(",", "");
    }

    public static String generateRandomNumber() {

        Random r = new Random();
        List<Integer> codes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int x = r.nextInt(9999);
            while (codes.contains(x))
                x = r.nextInt(9999);
            codes.add(x);
        }
        return String.format("%04d", codes.get(0));

    }

    public static String converAmountWithTwoDecimals(String amount) {
        if (GlobalMethods.isNull(amount)) {
            DecimalFormat df2 = new DecimalFormat(".##");
            Double formatedAmt = Double.parseDouble(amount);
//            return df2.format(formatedAmt);

            return String.format("%.2f", formatedAmt);
        } else {
            return "";
        }


    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Bitmap getCircleBitmap(Bitmap scaleBitmapImage) {
        int targetWidth = 110;
        int targetHeight = 110;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), new Paint(Paint.FILTER_BITMAP_FLAG));
        return targetBitmap;
    }

    public static String get8DigitMobileNumber(String mobileNo) {

        if (GlobalMethods.isNull(mobileNo)) {
            if (mobileNo.trim().length() == 8) {
                return mobileNo;
            } else if (mobileNo.trim().length() > 8) {
                // return mobileNo.trim().substring(mobileNo.length() - 8);
                return mobileNo.substring(0, mobileNo.length() - 8);
            } else {
                throw new IllegalArgumentException("word has less than 10 characters!");
            }
        } else {
            return "";
        }
    }

    public static String randomDigits(int digits) {
        if (digits <= 0 || digits > 18) {
            throw new IllegalArgumentException("A long can store the random of 18 full digits, you required: " + digits);
        }

        // use SecureRandom instead for truly random values
        final Random r = new Random();
        long randomNumber = r.nextInt(9) + 1;
        for (int i = 1; i < digits; i++) {
            randomNumber = randomNumber * 10L + (long) r.nextInt(10);
        }
        return randomNumber + "";
    }

    public static String callRMAServices(Context applicationContext, String request) {

        BufferedReader reader = null;
        try {


            // Defined URL  where to send data
            URL url = new URL(request);

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            // wr.write(data);
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }


            return sb.toString();
        } catch (Exception ex) {
            Log.v("", "");

        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

        return "";
    }

    public static String getNoMonthsBackDateFromCurrrentDate(int i) {

        SimpleDateFormat smdf = new SimpleDateFormat(Constants.DATE_FORMATE_YYYY_MM_DD);
        Date d = new Date(System.currentTimeMillis());
//        d.setMonth(Calendar.MONTH - i);
//        return smdf.format(d);


        Calendar now = Calendar.getInstance();
        System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1) + "-"
                + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR));

        now = Calendar.getInstance();
        now.add(Calendar.MONTH, -i);
//        yyyy-MM-dd
        int month = (now.get(Calendar.MONTH) + 1);
        String monthOnString = null;
        if (month < 10) {
            monthOnString = "0" + month;
        } else {
            monthOnString = month + "";
        }

        int date = now.get(Calendar.DATE);
        String dateOnString = null;
        if (date < 10) {
            dateOnString = "0" + date;
        } else {
            dateOnString = date + "";
        }


        return
                now.get(Calendar.YEAR) + "-" + monthOnString + "-" + dateOnString;
//        return  CalenderUtils.getDateAfterMonths(d,1);
    }

    public static String IndianFormat(BigDecimal n) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        //we never reach double digit grouping so return
        if (n.doubleValue() < 100000) {
            return formatter.format(n.setScale(2, 1).doubleValue());
        }
        StringBuffer returnValue = new StringBuffer();
        //Spliting integer part and decimal part
        String value = n.setScale(2, 1).toString();
        String intpart = value.substring(0, value.indexOf("."));
        String decimalpart = value.substring(value.indexOf("."), value.length());
        //switch to double digit grouping
        formatter.applyPattern("#,##");
        returnValue.append(formatter.format(new BigDecimal(intpart).doubleValue() / 1000)).append(",");
        //appending last 3 digits and decimal part
        returnValue.append(intpart.substring(intpart.length() - 3, intpart.length())).append(decimalpart);
        //returning complete string
        return returnValue.toString();
    }


    public static String getAppVersion(Context homeActivity) {

        String versionName = "";
        int versionCode = -1;
        try {
            PackageInfo packageInfo = homeActivity.getPackageManager().getPackageInfo(homeActivity.getPackageName(), 0);
            versionName = packageInfo.versionName;
            // versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }







    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake(final Context context) {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession arg1) {
                    HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
//                    if(VersionControls.getVersionControls(context).getRMAUrl().contains(hostname)&&VersionControls.getVersionControls(context).getUrl().contains(hostname)&&VersionControls.getVersionControls(context).getPAYMENT_BASE_URL().contains(hostname)) {
//
//                        return true;
//                    } else {
//                        return false;
//                    }
//                    return  true;
                    //   return hv.verify(VersionControls.getVersionControls(context).getRMAUrl(), arg1);
                    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE_1_1;
                }
            });
        } catch (Exception ignored) {
        }
    }

    public static String getDeviceType() {
        return "Android";

    }

    public static int getPercentage(String specialPrice, String price) {
        try {
            if (price.contains(",") && specialPrice.contains(",")) {
                // float specialPriceLong = Float.parseFloat(specialPrice);
                float specialPriceLong = Float.parseFloat(specialPrice.replace(",", ""));
                float priceLong = Float.parseFloat(price.replace(",", ""));
                return round1(((specialPriceLong / priceLong) * 100), 2);
            } else if (price.contains(",")) {
                float specialPriceLong = Float.parseFloat(specialPrice);
                float priceLong = Float.parseFloat(price.replace(",", ""));
                return round1(((specialPriceLong / priceLong) * 100), 2);
            } else if (specialPrice.contains(",")) {
                float specialPriceLong = Float.parseFloat(specialPrice.replace(",", ""));
                float priceLong = Float.parseFloat(price);
                return round1(((specialPriceLong / priceLong) * 100), 2);
            } else {
                float specialPriceLong = Float.parseFloat(specialPrice);
                float priceLong = Float.parseFloat(price);
                return round1(((specialPriceLong / priceLong) * 100), 2);
            }

        } catch (Exception e) {
            return -1;
        }


    }

    public static int round1(float value, int scale) {
//        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
        return Math.round(value);
    }

    public static boolean isValidEmail(String target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    public static String getFormattedDate(String dateString) {

//        2018-04-30 20:27:56
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(dateString);
            System.out.println(date);


            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            //2nd of march 2015
            int day = cal.get(Calendar.DATE);

            if (!((day > 10) && (day < 19)))
                switch (day % 10) {
                    case 1:
                        return new SimpleDateFormat("d'st' MMMM yyyy").format(date);
                    case 2:
                        return new SimpleDateFormat("d'nd' MMMM yyyy").format(date);
                    case 3:
                        return new SimpleDateFormat("d'rd' MMMM yyyy").format(date);
                    default:
                        return new SimpleDateFormat("d'th' MMMM yyyy").format(date);
                }
            return new SimpleDateFormat("d'th' MMMM yyyy").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateString;
        }
    }


    public static String getFormattedDate(String dateString, String gettingDatePattern) {

//        2018-04-30 20:27:56
        SimpleDateFormat format = new SimpleDateFormat(gettingDatePattern);
        try {
            Date date = format.parse(dateString);
            System.out.println(date);


            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            //2nd of march 2015
            int day = cal.get(Calendar.DATE);

            if (!((day > 10) && (day < 19)))
                switch (day % 10) {
                    case 1:
                        return new SimpleDateFormat("d'st' MMMM yyyy").format(date);
                    case 2:
                        return new SimpleDateFormat("d'nd' MMMM yyyy").format(date);
                    case 3:
                        return new SimpleDateFormat("d'rd' MMMM yyyy").format(date);
                    default:
                        return new SimpleDateFormat("d'th' MMMM yyyy").format(date);
                }
            return new SimpleDateFormat("d'th' MMMM yyyy").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateString;
        }
    }

    public static String getTime(String transaction_at) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(transaction_at);
            System.out.println(date);


            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            //2nd of march 2015
            int day = cal.get(Calendar.DATE);


            return new SimpleDateFormat("h:mm a").format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return transaction_at;
        }
    }

    public static String camelText(String action) {
        StringBuilder builder = new StringBuilder(action);
        // Flag to keep track if last visited character is a
        // white space or not
        boolean isLastSpace = true;

        // Iterate String from beginning to end.
        for (int i = 0; i < builder.length(); i++) {
            char ch = builder.charAt(i);

            if (isLastSpace && ch >= 'a' && ch <= 'z') {
                // Character need to be converted to uppercase
                builder.setCharAt(i, (char) (ch + ('A' - 'a')));
                isLastSpace = false;
            } else if (ch != ' ')
                isLastSpace = false;
            else
                isLastSpace = true;
        }

        return builder.toString();
    }


    public static String conrtTimeTo12HoursFormate(String booking_time, String gettingTimeFromate) {
        //"HH:mm:ss"
        DateFormat df = new SimpleDateFormat(gettingTimeFromate);
        //Date/time pattern of desired output date
        DateFormat outputformat = new SimpleDateFormat("hh:mm aa", Locale.US);
        Date date = null;
        String output = null;
        try {
            //Conversion of input String to date
            date = df.parse(booking_time);
            //old date format to new date format
            output = outputformat.format(date);
            System.out.println(output);
            return output;
        } catch (ParseException pe) {
            return booking_time;
        }

//        String strDateFormat = "HH:mm a";
//        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
//        System.out.println(sdf.format(date));


    }

    public static String getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android SDK: " + sdkVersion + " (" + release +")";
    }

    public static int getAppVersionCode(Context homeActivity) {

        int versionCode = -1;
        try {
            PackageInfo packageInfo = homeActivity.getPackageManager().getPackageInfo(homeActivity.getPackageName(), 0);
            //versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getCurrnetTime() {

        SimpleDateFormat smdf = new SimpleDateFormat("hh:mm aa");
        Date d = new Date(System.currentTimeMillis());
        return smdf.format(d);
    }

    public static void setSelectedLanguage(Context context, int selection_position) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.MY_PREFERENCE_NAME, context.MODE_PRIVATE).edit();
        editor.putInt(Constants.LANGUAGE_SELECTION, selection_position);

        editor.apply();
    }

    public static int getSelectedLanguage(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(Constants.LANGUAGE_SELECTION, 1);

    }
}
