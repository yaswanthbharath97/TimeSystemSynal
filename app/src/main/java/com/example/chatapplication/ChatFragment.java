package com.example.chatapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ChatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    RecyclerViewAdapter adapter;
    RecyclerView recyclerView1;
    ContactViewModel contactViewModel;

    List<Contact> contacts = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView1 = view.findViewById(R.id.recyclerview);
        Context context = requireContext();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView1.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(context, contacts);
        recyclerView1.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        contactViewModel.getAllContacts().observe(requireActivity(), new Observer<List<Contact>>()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override

            public void onChanged(List<Contact> contacts) {
                adapter.setContacts(contacts);
                adapter.notifyDataSetChanged();
            }

        });


    }


}