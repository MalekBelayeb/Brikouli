package com.example.brikouli.repository.local.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.brikouli.model.User;

import java.util.List;

@Dao
public interface IUserDao {

@Query("select * from t_user")
List<User> getAll();

@Query("select * from t_user where id = :id")
User get(String id);

@Query("delete from t_user")
void deleteAll();

@Insert
void insertOne(User user);

@Update
public void updateOne(User user);

@Query("delete from t_user where id=:id")
void deleteOne(String id);

}
