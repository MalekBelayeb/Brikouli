package com.example.brikouli.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.brikouli.model.User;
import com.example.brikouli.util.DownloadImageTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class HomeViewModel extends AndroidViewModel {


    Activity activity;
    private User user;
    private MutableLiveData<String> name = new MutableLiveData<String>();
    private MutableLiveData<Drawable> picProfile = new MutableLiveData<Drawable>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<Drawable> getPicProfile() {
        return picProfile;
    }

    public void setPicProfile(MutableLiveData<Drawable> picProfile) {
        this.picProfile = picProfile;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(MutableLiveData<String> name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void initView()
    {
     user = (User)getActivity().getIntent().getSerializableExtra("user");
        if(user!=null)
        {
            name.setValue(user.getName());
            try {

                Bitmap pic = new DownloadImageTask().execute(user.getPicUrl()).get();
                picProfile.setValue(new BitmapDrawable(getActivity().getResources(),pic));

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }else{

            name.setValue("");
        }

    }

    public void getAndShowProfileImg()
    {
        try {
            URL picUrl = new URL(user.getPicUrl());
            try {

                Bitmap picBitmap = BitmapFactory.decodeStream(picUrl.openStream());
                picProfile.setValue(new BitmapDrawable(getActivity().getResources(),picBitmap));

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
