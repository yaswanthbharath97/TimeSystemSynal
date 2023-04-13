package com.example.chatapplication.entity;


import androidx.lifecycle.LiveData;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;


@Entity(tableName = "contact_table")
public class Contact
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    public Contact( String name, String number, String images,int jid) {

        this.name = name;
        this.number = number;
        this.images = images;
        this.jid = jid;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    private int jid;

    private final String name;

    private final String number;

    private final String images;



    public String getImages()
    {
        return images;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public LiveData<List<Contact>> getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getNumber()
    {
        return number;
    }

}

