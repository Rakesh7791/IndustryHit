package com.industryhit.industryhit.presentation.login.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.industryhit.industryhit.R;
import com.industryhit.industryhit.presentation.globalutils.constants.GlobalMethods;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomEditText;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextView;
import com.industryhit.industryhit.presentation.globalutils.custom.CustomTextViewBold;
import com.industryhit.industryhit.presentation.home.activity.NewHomeActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_Activity extends BaseActivity implements
        View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {

    private CustomEditText email_or_phone,password;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton btnSignIn;
    private static final int RC_SIGN_IN = 007;
    private ProgressDialog mProgressDialog;
    private static final String TAG = Login_Activity.class.getSimpleName();

    @Override
    protected int setLayoutResuourse() {
        return R.layout.activity_login;
    }

    @Override
    protected void initGUI() {

        initViews();

        initGoogleLogin();


        actionEvents();
    }



    private void initViews() {

        email_or_phone=(CustomEditText)findViewById(R.id.email_or_phone);
        password=(CustomEditText)findViewById(R.id.password);
        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);

    }
    private void initGoogleLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // Customizing G+ button
        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setScopes(gso.getScopeArray());
        btnSignIn.setOnClickListener(this);
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

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
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
        else if (!validateEmailOrMobile(email_or_phone.getText().toString())){
            GlobalMethods.setEditextError(Login_Activity.this, getString(R.string.please_enter_valid_email_or_phonenumber), email_or_phone);
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


    private void callRegistrationAct() {
        GlobalMethods.callForWordActivity(Login_Activity.this,RegistrationAct.class,null,false,true);
    }


    private void callHomeActivity() {
        GlobalMethods.callForWordActivity(Login_Activity.this,NewHomeActivity.class,null,false,true);

    }




    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
       // Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);

//            txtName.setText(personName);
//            txtEmail.setText(email);
//            Glide.with(getApplicationContext()).load(personPhotoUrl)
//                    .thumbnail(0.5f)
//                    .crossFade()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(imgProfilePic);

            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_sign_in:
                signIn();
                break;

//            case R.id.btn_sign_out:
//                signOut();
//                break;
//
//            case R.id.btn_revoke_access:
//                revokeAccess();
//                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

  /*  @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }*/

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);
       //     btnSignOut.setVisibility(View.VISIBLE);
        //    btnRevokeAccess.setVisibility(View.VISIBLE);
          //  llProfileLayout.setVisibility(View.VISIBLE);
        } else {
            btnSignIn.setVisibility(View.VISIBLE);
           // btnSignOut.setVisibility(View.GONE);
           // btnRevokeAccess.setVisibility(View.GONE);
           // llProfileLayout.setVisibility(View.GONE);
        }
    }

}
