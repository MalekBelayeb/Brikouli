package com.example.brikouli.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.brikouli.R;
import com.example.brikouli.viewmodel.LoginViewModel;

public class SplashScreenActivity extends AppCompatActivity {

    final int SPLASH_TIME = 1500;
    LoginViewModel loginActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginActivityViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginActivityViewModel.setActivity(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loginActivityViewModel.checkForFacebookLoginStatus();

            }
        },SPLASH_TIME);

    }
}