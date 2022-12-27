package com.example.chatapplication.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.chatapplication.dao.ContactDao;
import com.example.chatapplication.database.ContactDatabase;
import com.example.chatapplication.entity.Contact;

import java.util.List;

public class ContactRepository {

    private ContactDao contactDao;
    private final LiveData<List<Contact>>allContacts;

    public ContactRepository(Application application)
    {
        ContactDatabase database=ContactDatabase.getInstance(application);
        contactDao= database.contactDao();
        allContacts= contactDao.getAllContact();


    }
    public void insert(Contact contact)
    {
        new InsertContactAsyncTask(contactDao).execute(contact);

    }
    public void delete(Contact contact)
    {
        new DeleteContactAsyncTask(contactDao).execute(contact);
    }

    public LiveData<List<Contact>> getAllContacts()
    {
        return allContacts;
    }


//Insert
    private static class InsertContactAsyncTask extends AsyncTask<Contact,Void,Void>
    {
        private ContactDao contactDao;

        public InsertContactAsyncTask(ContactDao contactDao)
        {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts)
        {
            contactDao.insert(contacts[0]);
            return null;
        }
    }

    private static class DeleteContactAsyncTask extends AsyncTask<Contact,Void,Void>
    {
        private ContactDao contactDao;

        public DeleteContactAsyncTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.delete(contacts[0]);
            return null;
        }
    }
}
