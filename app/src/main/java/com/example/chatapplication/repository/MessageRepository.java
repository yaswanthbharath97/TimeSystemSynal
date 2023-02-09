package com.example.chatapplication.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.chatapplication.dao.MessageDao;
import com.example.chatapplication.database.ContactDatabase;
import com.example.chatapplication.entity.Message;

import java.util.List;

public class MessageRepository {

    private final MessageDao messageDao;
    private final LiveData<List<Message>> allMessages;

    public MessageRepository(Application application) {
        ContactDatabase database = ContactDatabase.getInstance(application);
        messageDao = database.messageDao();
        allMessages = messageDao.getAll();

    }

    public void insert(Message message) {
        new InsertMessageAsyncTask(messageDao).execute(message);
    }

    public LiveData<List<Message>> getAllMessages() {
        return allMessages;
    }

    private static class InsertMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        private final MessageDao messageDao;


        public InsertMessageAsyncTask(MessageDao messageDao) {
            this.messageDao = messageDao;
        }

        protected Void doInBackground(Message... messages) {
            messageDao.insert(messages[0]);
            return null;
        }


    }
}


