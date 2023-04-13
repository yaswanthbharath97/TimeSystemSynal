package com.example.chatapplication.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.chatapplication.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM users WHERE phoneNo = :phoneNo AND password = :password")
    User getUser(String phoneNo, String password);

    @Insert
    void insertUser(User user);

    @Query("SELECT COUNT(*) FROM users WHERE phoneNo = :phoneNo")
    int countUsersWithPhoneNo(String phoneNo);
}
