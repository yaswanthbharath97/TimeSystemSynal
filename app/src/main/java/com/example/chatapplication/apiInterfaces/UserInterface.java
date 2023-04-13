package com.example.chatapplication.apiInterfaces;

import com.example.chatapplication.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserInterface {

    @GET("users")
    Call<List<User>> getUsers();

    Call<List<User>>getUserWithId(int userId);

}
