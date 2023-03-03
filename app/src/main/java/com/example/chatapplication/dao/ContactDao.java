package com.example.chatapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.chatapplication.entity.Contact;

import java.util.List;

@Dao
public interface ContactDao
{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact contact);

    @Delete
    void delete(Contact contact);

   // we will call it for showing in recyclerview
    @Query("SELECT * FROM contact_table ORDER BY name ASC")
    LiveData<List<Contact>> getAllContact();



}
