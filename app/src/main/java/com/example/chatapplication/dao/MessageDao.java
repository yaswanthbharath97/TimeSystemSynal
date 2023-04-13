package com.example.chatapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;

import androidx.room.Insert;

import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import androidx.room.Transaction;
import androidx.room.Update;


import com.example.chatapplication.entity.Contact;
import com.example.chatapplication.entity.Message;

import java.util.List;

@Dao
public interface MessageDao {


    @Transaction
    @Query("SELECT * FROM chatMessage_table")
    LiveData<List<Message>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Message message);

    @Update
    void update(Message message);


    @Query("DELETE FROM chatMessage_table WHERE sender_id = :senderId")
    void deleteMessagesForContact(long senderId);

    @Query("SELECT * FROM chatMessage_table WHERE sender_id = :sender_Id")
    LiveData<List<Message>> getMessagesBySenderId(long sender_Id);

}

