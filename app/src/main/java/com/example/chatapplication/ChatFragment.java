package com.example.chatapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatapplication.entity.Contact;
import com.example.chatapplication.viewmodel.ContactViewModel;

import java.util.List;
import java.util.Objects;


public class ChatFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    RecyclerViewAdapter adapter;
    RecyclerView recyclerView1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView1=(RecyclerView)view.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView1.setLayoutManager(layoutManager);
        adapter=new RecyclerViewAdapter(getActivity());
        recyclerView1.setAdapter(adapter);
        ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        contactViewModel.getAllContacts().observe(requireActivity(), new Observer<List<Contact>>()
        {
            @Override
            public void onChanged(List<Contact> contacts)
            {
                adapter.setContacts(contacts);
            }
        });


    }


}