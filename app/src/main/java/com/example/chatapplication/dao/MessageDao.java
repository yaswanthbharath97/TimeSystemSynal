package com.example.chatapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Relation;
import androidx.room.Transaction;

import com.example.chatapplication.entity.Contact;
import com.example.chatapplication.entity.Message;

import java.util.List;

@Dao
public interface MessageDao {



   @Transaction
    @Query("SELECT * FROM chatMessage_table")
    LiveData<List<Message>>getAll();


   // @Query("SELECT * FROM chatMessage_table WHERE senderId = :userId")
   // List<Message> getMessagesForUser(int userId);
    @Insert
    void insert(Message message);


    static class MessageWithContact{

        @Embedded
        public Contact contact;

        @Relation(parentColumn = "id",entityColumn = "contactId",entity = Message.class)
        public List<Message>messages;
        

    }



}
