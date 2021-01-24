package com.example.brikouli.viewmodel;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.brikouli.R;
import com.example.brikouli.model.User;
import com.example.brikouli.repository.local.database.AppDatabase;
import com.example.brikouli.view.HomeActivity;
import com.example.brikouli.view.InfoLoginActivity;

public class InfoLoginViewModel extends AndroidViewModel {

    private InfoLoginActivity infoLoginActivity;
    private AppDatabase _database;
    User user = new User();
    private MutableLiveData<String> name = new MutableLiveData<String>();
    private MutableLiveData<String> firstName= new MutableLiveData<String>();
    private MutableLiveData<String> lastName= new MutableLiveData<String>();

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setName(MutableLiveData<String> name) {
        this.name = name;
    }

    public MutableLiveData<String> getFirstName() {
        return firstName;
    }

    public void setFirstName(MutableLiveData<String> firstName) {
        this.firstName = firstName;
    }

    public MutableLiveData<String> getLastName() {
        return lastName;
    }

    public void setLastName(MutableLiveData<String> lastName) {
        this.lastName = lastName;
    }

    public InfoLoginViewModel(@NonNull Application application) {
        super(application);


        _database = AppDatabase.getInstance(application.getApplicationContext());


    }

    public InfoLoginActivity getInfoLoginActivity() {
        return infoLoginActivity;
    }

    public void setInfoLoginActivity(InfoLoginActivity infoLoginActivity) {
        this.infoLoginActivity = infoLoginActivity;

    }

    public void initInfo()
    {
        user = (User)getInfoLoginActivity().getIntent().getSerializableExtra("user");
        if(user!=null)
        {
            name.setValue(user.getName());
            firstName.setValue(user.getFirstName());
            lastName.setValue(user.getLastName());

        }else{

            name.setValue("");
            firstName.setValue("");
            lastName.setValue("");
        }

    }

    public void navigateToHome(User user)
    {

        Intent intent = new Intent(getApplication().getApplicationContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user",user);
        getApplication().startActivity(intent);
        getInfoLoginActivity().overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_bottom);

    }


    public void onClickConfirmer()
    {
        User connectedUser = new User();

        connectedUser.setName(name.getValue());
        connectedUser.setFirstName(firstName.getValue());
        connectedUser.setLastName(lastName.getValue());
        connectedUser.setPicUrl(user.getPicUrl());

        navigateToHome(connectedUser);
        //_database.userDao().deleteAll();
        //_database.userDao().insertOne(connectedUser);
        //System.out.println(_database.userDao().getAll().size());


    }


}
