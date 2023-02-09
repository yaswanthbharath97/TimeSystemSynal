package com.example.chatapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.chatapplication.dao.MessageDao;
import com.example.chatapplication.entity.Message;
import com.example.chatapplication.repository.MessageRepository;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {
    private final MessageRepository messageRepository;
    private final LiveData<List<Message>> allMessages;

    public MessageViewModel(@NonNull Application application) {
        super(application);
        this.messageRepository = new MessageRepository(application);
        this.allMessages = this.messageRepository.getAllMessages();
    }
    public void insert(Message message)
    {
     messageRepository.insert(message);
    }


    public LiveData<List<Message>> getAllMessages()
    {
        return  allMessages;
    }


}
