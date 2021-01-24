package com.example.brikouli.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.brikouli.R;
import com.example.brikouli.databinding.ActivityInfoLoginBinding;
import com.example.brikouli.viewmodel.InfoLoginViewModel;

public class InfoLoginActivity extends AppCompatActivity {

    InfoLoginViewModel infoLoginViewModel;
    ActivityInfoLoginBinding activityInfoLoginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_login);

        infoLoginViewModel = ViewModelProviders.of(this).get(InfoLoginViewModel.class);
        infoLoginViewModel.setInfoLoginActivity(this);
        activityInfoLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_info_login);
        activityInfoLoginBinding.setViewModel(infoLoginViewModel);
        activityInfoLoginBinding.setLifecycleOwner(this);

        infoLoginViewModel.initInfo();

    }



}