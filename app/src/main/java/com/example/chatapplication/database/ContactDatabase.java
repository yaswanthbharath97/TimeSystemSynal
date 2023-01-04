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
import com.example.chatapplication.entity.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@Database(entities = {Contact.class},version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    @SuppressLint("StaticFieldLeak")
    private static ContactDatabase instance;

    @SuppressLint("StaticFieldLeak")
    private static  Context activity;

    public abstract ContactDao contactDao();

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


        private ContactDao contactDao;

        private PopulateDbAsyncTask(@NonNull ContactDatabase contactDatabase)
        {
            contactDao= contactDatabase.contactDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            contactDao.insert(new Contact("Animsh","6854712391","https://squar.github.io/picasso/static/sample.png "));
            contactDao.insert(new Contact("roy","6854712391","https://square.github.io/picasso/static/sample.png "));
            contactDao.insert(new Contact("Joi","5855684558","https://square.github.io/picasso/static/sample.png   "));
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

                for (int i = 0; i < contacts.length(); i++) {

                    JSONObject contact = contacts.getJSONObject(i);

                    String contactName = contact.getString("name");
                    String PhoneNumber = contact.getString("phone");
                    String Images=contact.getString("images");
                    contactDao.insert(new Contact(contactName, PhoneNumber,Images));

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
          while ((line= reader.readLine())!=null){
              builder.append(line);
          }

          JSONObject jsonObject=new JSONObject(builder.toString());
          return jsonObject.getJSONArray("contacts");

      }
      catch (IOException |JSONException e) {
          e.printStackTrace();

      }

     return null;

    }

}
