package com.example.chatapplication.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private final String name;

    private final String number;

    private final String images;




    public Contact(String name, String number,String images) {
        this.name = name;
        this.number = number;
        this.images=images;
    }

    public String getImages()
    {
        return images;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
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

