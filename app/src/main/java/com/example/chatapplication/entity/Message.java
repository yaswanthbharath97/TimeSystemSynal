package com.example.chatapplication.entity;


import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;


@Entity(tableName = "chatMessage_table",
        foreignKeys = @ForeignKey(entity = Contact.class,
                parentColumns = "id",
                childColumns = "sender_id"
                ),
        indices = {@Index("sender_id")})
public class Message {

    @PrimaryKey(autoGenerate = true)

    public int id;

    public long timestamp;


    public String message;
    @ColumnInfo(name = "sender_id")
    public long senderId;



    public long getSenderId()
    {
        return senderId;
    }

    public void setSenderId(long senderId)
    {
        this.senderId = senderId;
    }

    public Message(String message, long senderId) {
        this.message = message;
        this.senderId=senderId;
        this.timestamp=System.currentTimeMillis();

    }


    public long getTimestamp()

    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)

    {
        this.timestamp = timestamp;
    }





    public void setMessage(String message)

    {
        this.message = message;
    }





    public int getId()

    {
        return id;
    }

    public String getMessage()

    {
        return message;
    }


}
