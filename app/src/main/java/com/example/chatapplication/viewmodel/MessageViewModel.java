package com.example.chatapplication.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.chatapplication.database.ContactDatabase;
import com.example.chatapplication.entity.Message;
import com.example.chatapplication.repository.MessageRepository;

import java.util.List;

public class MessageViewModel extends AndroidViewModel
{
    private final MessageRepository messageRepository;
    private final LiveData<List<Message>> allMessages;



    public MessageViewModel(Application application)
    {
        super(application);
        messageRepository = new MessageRepository(application);
        allMessages = this.messageRepository.getAllMessages();
    }

    public LiveData<List<Message>> getMessagesBySenderId(long senderId) {
        return messageRepository.getMessagesBySenderId(senderId);
    }
    public void insert(Message message,long senderId)
    {
        messageRepository.insert(message,senderId);
    }

    public void delete(Message message)
    {
       messageRepository.delete(message);
    }

    public LiveData<List<Message>>getAllMessages()
    {
        return allMessages;
    }



}