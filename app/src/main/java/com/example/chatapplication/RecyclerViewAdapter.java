package com.example.chatapplication;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.chatapplication.entity.Contact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Contact> contacts;
    private final Context context;




    public RecyclerViewAdapter( Context context,List<Contact>contacts)
    {
        super();
        this.context = context;
        this.contacts=contacts;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Contact contact = contacts.get(position);
        holder.name.setText(contact.getName());
        holder.phoneNo.setText(contact.getNumber());
        if(!Picasso.get().isLoggingEnabled())
        {
            Picasso.get().setLoggingEnabled(true);
            Picasso.get().setIndicatorsEnabled(true);
        }

        Picasso.get()
                .load(contact.getImages()).placeholder(R.drawable.defaultprofile)
                .error(R.drawable.defaultprofile).
                fit()
                .into(holder.imageview);

        holder.imageview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, UserImageView.class);
                intent.putExtra("Url", contact.getImages());
                intent.putExtra("title",contact.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(context,MasterPage.class);
                intent.putExtra("contactId",contact.getId());
                intent.putExtra("Url", contact.getImages());
                intent.putExtra("title",contact.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount()
    {

        return contacts.size();

    }



    @SuppressLint("NotifyDataSetChanged")
    public void setContacts(List<Contact>newContacts)
    {

        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(new ContactsDiffUtilCallback(contacts, newContacts));
        contacts.clear();
        contacts.addAll(newContacts);
        diffResult.dispatchUpdatesTo(this);

    }

    public static class ContactsDiffUtilCallback extends DiffUtil.Callback {
        private final List<Contact> oldContacts;
        private final List<Contact> newContacts;

        public ContactsDiffUtilCallback(List<Contact> oldContacts, List<Contact> newContacts) {
            this.oldContacts = oldContacts;
            this.newContacts = newContacts;
        }

        @Override
        public int getOldListSize() {
            return oldContacts.size();
        }

        @Override
        public int getNewListSize() {
            return newContacts.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldContacts.get(oldItemPosition).getId() == newContacts.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldContacts.get(oldItemPosition).equals(newContacts.get(newItemPosition));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        CircleImageView imageview;
        TextView name;
        TextView phoneNo;
        RelativeLayout layout;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView)
        {

            super(itemView);
            imageview = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.list_text);
            phoneNo = itemView.findViewById(R.id.phone1);
            layout = itemView.findViewById(R.id.parent_view);
            progressBar=itemView.findViewById(R.id.progress);

        }


    }

}