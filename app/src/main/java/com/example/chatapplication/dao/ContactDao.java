package com.example.chatapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.chatapplication.entity.Contact;

import java.util.List;

@Dao
public interface ContactDao
{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("SELECT * FROM contact_table")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT id FROM contact_table WHERE id = :id")
    Long getContactById(long id);

}
