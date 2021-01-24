package com.example.brikouli.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.brikouli.R;
import com.example.brikouli.model.User;
import com.example.brikouli.view.HomeActivity;
import com.example.brikouli.view.InfoLoginActivity;
import com.example.brikouli.view.LoginActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.TimeUnit;

public class LoginViewModel extends AndroidViewModel {


    private Activity activity;

    FirebaseAuth mAuth;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            e.printStackTrace();
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);


        }
    };

    public FacebookCallback<LoginResult> getFacebookCallback() {
        return facebookCallback;
    }

    FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.d("FacebookLogin", "facebook:onSuccess:" + loginResult);

            handleFacebookAccessToken(loginResult.getAccessToken());

            signUpUserFacebookData(loginResult.getAccessToken(),1);
        }

        @Override
        public void onCancel() {
            Log.d("FacebookLogin", "facebook:onCancel");
        }

        @Override
        public void onError(FacebookException error) {

            error.printStackTrace();
        }

    };

    private void handleFacebookAccessToken(AccessToken token) {

        System.out.println("qqqqqqqqqqq");
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        if (token.getToken()==null)
        {
            System.out.println("token null");
        }else{

            System.out.println("token not null");
        }

        /*
        mAuth.signInWithCredential(credential).addOnCompleteListener(getLoginActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("FacebookLogin", "signInWithCredential:success ");

                        }else {
                            Log.w("FacebookLogin", "signInWithCredential:failure", task.getException());
                            Toast.makeText(getApplication().getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }

                }


        );*/

    }

    public void onClickButton()
    {

        FirebaseAuth.getInstance().setLanguageCode("fr");

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+21655317899")       // Phone number to verify
                        .setTimeout(10L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(getActivity())                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }



    private void signUpUserFacebookData(final AccessToken token,int navTo)
    {

        GraphRequest request = GraphRequest.newMeRequest(
                token,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {

                            String name = "";
                            String email = "";
                            String firstName = "";
                            String lastName = "";
                            String gender = "";
                            String birthday = "";
                            String picUrl = "";

                            User connectedUser = new User();

                            if(response.getJSONObject().has("email"))
                            {
                                 email = response.getJSONObject().getString("email");
                            }

                            if(response.getJSONObject().has("first_name"))
                            {
                                 firstName = response.getJSONObject().getString("first_name");
                                 connectedUser.setFirstName(firstName);

                            }

                            if(response.getJSONObject().has("last_name"))
                            {
                                 lastName = response.getJSONObject().getString("last_name");
                                 connectedUser.setLastName(lastName);
                            }

                            if(response.getJSONObject().has("gender"))
                            {
                                 gender = response.getJSONObject().getString("gender");
                                 connectedUser.setGender(gender);
                            }

                            if(response.getJSONObject().has("birthday"))
                            {
                                 birthday = response.getJSONObject().getString("birthday");
                                 connectedUser.setBirthday(birthday);
                            }

                            if(response.getJSONObject().has("name"))
                            {
                                 name = response.getJSONObject().getString("name");
                                 connectedUser.setName(name);
                            }

                            if(response.getJSONObject().has("picture"))
                            {
                                 picUrl = response.getJSONObject().getJSONObject("picture")
                                        .getJSONObject("data").getString("url");
                                connectedUser.setPicUrl(picUrl);

                            }

                            if(navTo==1)
                            {
                                navigateTo(connectedUser,navTo);

                            }else if (navTo == 2){

                                navigateTo(connectedUser,navTo);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name,gender,birthday,name,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }


    public void navigateTo(User user,int navTo)
    {
        Intent intent =null;
        if (navTo ==1)
        {
             intent= new Intent(getApplication().getApplicationContext(), InfoLoginActivity.class);

        }else if(navTo == 2)
        {
             intent = new Intent(getApplication().getApplicationContext(), HomeActivity.class);
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user",user);
        getApplication().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_bottom);

    }



    public void checkForFacebookLoginStatus()
    {
        AccessToken token = AccessToken.getCurrentAccessToken();

        if(token == null)
        {

            Intent intent = new Intent(getApplication().getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);

            System.out.println("fb login null");

        }else{

            System.out.println("fb login not null");

            signUpUserFacebookData(token,2);
        }
    }


}
