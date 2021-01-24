package com.example.brikouli.repository.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import com.example.brikouli.model.User;
import com.example.brikouli.repository.local.dao.IUserDao;


@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

        private static AppDatabase instance;

        public abstract IUserDao userDao();

        public static AppDatabase getInstance(Context context)
        {

            if(instance==null)
            {

                instance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"userDB").allowMainThreadQueries().build();

            }

            return instance;
        }



}
