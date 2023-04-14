package com.example.chatapplication.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.chatapplication.dao.ContactDao;
import com.example.chatapplication.dao.MessageDao;
import com.example.chatapplication.database.ContactDatabase;
import com.example.chatapplication.entity.Contact;
import com.example.chatapplication.entity.Message;

import java.util.List;

public class MessageRepository {

    private final ContactDao contactDao;
    private final MessageDao messageDao;
    private final LiveData<List<Message>> allMessages;

    public MessageRepository(Application application) {
        ContactDatabase database = ContactDatabase.getInstance(application);
        messageDao = database.messageDao();
        contactDao= database.contactDao();
        allMessages = messageDao.getAll();

    }
    public LiveData<List<Message>> getMessagesBySenderId(long senderId)
    {
        return messageDao.getMessagesBySenderId(senderId);
    }
    public void insert(Message message,long senderId)
    {
        new InsertMessageAsyncTask(messageDao,senderId,contactDao).execute(message);
    }
    public  void delete(Message message)
    {
        new DeleteMessageAsyncTask(messageDao).execute(message);
    }
    public LiveData<List<Message>> getAllMessages() {
        return allMessages;
    }

    private static class InsertMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        private final MessageDao messageDao;
        private final long senderId;

        private final ContactDao contactDao;
        public InsertMessageAsyncTask(MessageDao messageDao, long senderId, ContactDao contactDao) {
            this.messageDao = messageDao;
            this.senderId=senderId;
            this.contactDao = contactDao;
        }

        protected Void doInBackground(Message... messages)
        {
            Long contactId = contactDao.getContactById(senderId);
            if (contactId!= null)
            {
                messages[0].setSenderId(contactId);
                messageDao.insert(messages[0]);
            }
            return null;
        }


    }

    private static class DeleteMessageAsyncTask extends AsyncTask<Message,Void,Void>
    {
        private final MessageDao messageDao;


        public DeleteMessageAsyncTask(MessageDao messageDao) {
            this.messageDao = messageDao;
        }
        @Override
        protected Void doInBackground(Message... messages)
        {
            long contactId = messages[0].getSenderId();
            messageDao.deleteMessagesForContact(contactId);
            return null;
        }
    }
}
