package com.example.chatapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.chatapplication.entity.Contact;
import com.example.chatapplication.repository.ContactRepository;
import java.util.List;

public class ContactViewModel extends AndroidViewModel
{

    private final ContactRepository contactRepository;
    private final LiveData<List<Contact>>allContacts;


    public ContactViewModel(@NonNull Application application)
    {
        super(application);
        this.contactRepository=new ContactRepository(application);
        this.allContacts=this.contactRepository.getAllContacts();
    }


    public void insert(Contact contact)
    {
        contactRepository.insert(contact);
    }
    public  void delete(Contact contact)
    {
        contactRepository.delete(contact);
    }

    public LiveData<List<Contact>> getAllContacts()
    {
        return allContacts;
    }

}
