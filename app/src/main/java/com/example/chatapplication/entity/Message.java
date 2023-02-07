package com.example.chatapplication.entity;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName ="chatMessage_table" )
public class Message {

    @PrimaryKey (autoGenerate = true)
    public int id;

    public void setMessage(String message) {
        this.message = message;
    }

    private  String message;



    private  int senderId;

    private int recieverId;

    public Message(String message, int senderId) {
        this.message = message;
        this.senderId = senderId;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setRecieverId(int recieverId) {
        this.recieverId = recieverId;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getRecieverId() {
        return recieverId;
    }
}
