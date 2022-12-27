package com.example.chatapplication.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.chatapplication.dao.ContactDao;
import com.example.chatapplication.entity.Contact;


@Database(entities = {Contact.class},version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    private static ContactDatabase instance;

    public abstract ContactDao contactDao();

    public static synchronized  ContactDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDatabase.class,"contact_database")
                    .fallbackToDestructiveMigration()
                        .build();
        }
      return instance;
    }

    private static RoomDatabase.Callback roomCallBack=new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };


}
