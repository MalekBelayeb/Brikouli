package com.example.brikouli.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.brikouli.R;
import com.example.brikouli.databinding.ActivityHomeBinding;
import com.example.brikouli.viewmodel.HomeViewModel;
import com.example.brikouli.viewmodel.LoginViewModel;
import com.facebook.login.LoginManager;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    HomeViewModel homeViewModel;
    ActivityHomeBinding activityHomeBinding;
    NavigationView drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.setActivity(this);

        activityHomeBinding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        activityHomeBinding.setViewModel(homeViewModel);
        activityHomeBinding.setLifecycleOwner(this);
        homeViewModel.initView();

        drawer = findViewById(R.id.nav_view);
        drawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id =item.getItemId();

                switch (id)
                {
                    case R.id.nav_leave_0:
                    {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {



                                new AlertDialog.Builder(HomeActivity.this)
                                        .setTitle("Se déconnecter")
                                        .setMessage("Voulez-vous vraiment vous déconnecter")
                                        .setCancelable(false)
                                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                dialog.cancel();

                                            }
                                        })
                                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                LoginManager.getInstance().logOut();
                                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                                startActivity(intent);
                                            }
                                        }).show();

                            }
                        });

                        break;
                    }
                }

                return true;
            }
        });


    }
}