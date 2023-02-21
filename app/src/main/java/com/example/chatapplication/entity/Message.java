package com.example.chatapplication.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "chatMessage_table")
public class Message {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public long timestamp;

    public String message;

    public long sender_id;


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }



    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @ColumnInfo(index = true)
    public int contactId;



    public Message(String message) {

        this.message = message;

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }


}
