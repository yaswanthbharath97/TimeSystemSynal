package com.example.chatapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Relation;
import androidx.room.Transaction;

import com.example.chatapplication.entity.Contact;

import java.util.List;

public interface MessageDao {

    @Insert
    void insert(Message message);

   @Transaction
    @Query("SELECT * FROM chatMessage_table")
    LiveData<List<Message>> getAll();


    @Query("SELECT * FROM chatMessage_table WHERE senderId = :userId")
    List<Message> getMessagesForUser(int userId);

    class Message{

        @Embedded
        public Contact contact;
        @Relation(parentColumn = "id", entityColumn = "userId", entity = Message.class)
        public List<Message>messages;
    }


}
