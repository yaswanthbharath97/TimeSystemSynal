package com.example.chatapplication.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.chatapplication.R;
import com.example.chatapplication.dao.ContactDao;
import com.example.chatapplication.dao.MessageDao;
import com.example.chatapplication.dao.UserDao;
import com.example.chatapplication.entity.Contact;
import com.example.chatapplication.entity.Message;
import com.example.chatapplication.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@Database(entities = {Contact.class, Message.class, User.class},version =1,exportSchema = false)
public abstract class   ContactDatabase extends RoomDatabase
{

    @SuppressLint("StaticFieldLeak")
    private static ContactDatabase instance;

    @SuppressLint("StaticFieldLeak")
    private static  Context activity;
    public abstract UserDao userDao();

    public abstract ContactDao contactDao();

    public abstract MessageDao messageDao();

    public static synchronized  ContactDatabase getInstance(Context context)
    {
        activity=context.getApplicationContext();
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ContactDatabase.class,"contact_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback roomCallBack=new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };


    private static  class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>
    {

        private final ContactDao contactDao;

        private PopulateDbAsyncTask(@NonNull ContactDatabase contactDatabase)
        {
            contactDao= contactDatabase.contactDao();
        }



        @Override
        protected Void doInBackground(Void... voids)
        {
            contactDao.insert(new Contact("Animsh","6854712391","https://picsum.photos/200/300 ",13));
            contactDao.insert(new Contact("roy","6854712391","https://source.unsplash.com/user/c_v_r/1900x800",14));
            contactDao.insert(new Contact("Joi","5855684558","https://source.unsplash.com/user/c_v_r/100x100",15));
            fillWithStartingData(activity);
            return null;
        }
    }

    private static  void  fillWithStartingData(Context context)
    {
        ContactDao contactDao=getInstance(context).contactDao();
        JSONArray contacts=loadJSONArray(context);
        try{
            if(contacts!=null) {

                for (int i = 0; i < contacts.length(); i++)
                {
                    JSONObject contact = contacts.getJSONObject(i);
                    String contactName = contact.getString("name");
                    String PhoneNumber = contact.getString("phone");
                    String Images=contact.getString("images");
                    int Id= contact.getInt("id");
                    contactDao.insert(new Contact(contactName, PhoneNumber,Images,Id));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    private static JSONArray loadJSONArray(Context context)
    {
        StringBuilder builder=new StringBuilder();
        InputStream in=context.getResources().openRawResource(R.raw.contact_list);
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        String line;

        try {
            while ((line= reader.readLine())!=null)
            {
                builder.append(line);
            }

            JSONObject jsonObject=new JSONObject(builder.toString());
            return jsonObject.getJSONArray("contacts");

        }
        catch (IOException |JSONException e)
        {
            e.printStackTrace();

        }

        return null;
    }

}
