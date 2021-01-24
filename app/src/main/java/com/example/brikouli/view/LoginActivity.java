package com.example.brikouli.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.example.brikouli.R;
import com.example.brikouli.databinding.ActivityLoginBinding;
import com.example.brikouli.viewmodel.LoginViewModel;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    LoginViewModel loginActivityViewModel;
    ActivityLoginBinding activityLoginBinding;

    CallbackManager mCallbackManager;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginActivityViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginActivityViewModel.setActivity(this);

        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        activityLoginBinding.setViewModel(loginActivityViewModel);
        activityLoginBinding.setLifecycleOwner(this);


        mCallbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");

        loginButton.registerCallback(mCallbackManager, loginActivityViewModel.getFacebookCallback());


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode,resultCode,data);

    }


}