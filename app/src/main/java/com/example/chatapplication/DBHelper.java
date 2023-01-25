package com.example.chatapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {


    public DBHelper( Context context)
    {
        super(context,"Login.db",null,1);
    }

    @Override //cretes table
    public void onCreate(SQLiteDatabase myDB)
    {
        myDB.execSQL("create Table users(username Text primary key,password Text,phone_number Text,email Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion)
    {
        myDB.execSQL("drop Table if exists users");
    }

    // this method is used to insert values in table,database
    public Boolean insertData(String username,String password,String phonenumber,String email)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("phone_number", phonenumber);
        contentValues.put("email", email);
        long result = myDB.insert("users", null, contentValues);
        if (result == -1)
        {
            return false;
        } else {
            return true;
        }
    }

            //this method is used to check username already exists in database
        public Boolean checkusername(String username)
        {
            SQLiteDatabase myDB=this.getWritableDatabase();

            Cursor cursor = myDB.rawQuery("select * from users where username = ?",new String[]{username});
            if(cursor.getCount()>0)
                return true;
            else
                return false;

        }

        //this method is used in login page when user entered the values are same or not
        public Boolean checkusernamePassword(String username,String password)
        {
            SQLiteDatabase myDB=this.getWritableDatabase();
            @SuppressLint("Recycle")
            Cursor cursor= myDB.rawQuery("select * from users where username = ? and password=?",new String[]{username,password});
            if(cursor.getCount()>0)
                 return true;
             else
                 return false;


        }











}
