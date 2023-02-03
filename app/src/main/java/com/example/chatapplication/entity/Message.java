package com.example.chatapplication.entity;


import androidx.room.Entity;

@Entity(tableName ="chatMessage_table" )
public class Message {


    private final String message;


    public Message(String message) {
        this.message = message;
    }
}
