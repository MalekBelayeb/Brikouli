package com.example.brikouli.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@SuppressWarnings("ALL")
@Entity(tableName = "t_user")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "firstName")
    String firstName;

    @ColumnInfo(name = "lastName")
    String lastName;

    @ColumnInfo(name = "gender")
    String gender;

    @ColumnInfo(name = "birthday")
    String birthday;

    @ColumnInfo(name = "phoneNumber")
    String phoneNumber;

    String picUrl;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
