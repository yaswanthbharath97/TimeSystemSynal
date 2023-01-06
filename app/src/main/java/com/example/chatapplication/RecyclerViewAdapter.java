package com.example.chatapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.chatapplication.entity.Contact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private List<Contact> contacts = new ArrayList<>();
    private final Context context;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.name.setText(contact.getName());
        holder.phoneno.setText(contact.getNumber());
       if(!Picasso.get().isLoggingEnabled()) {

           Picasso.get().setLoggingEnabled(true);
           Picasso.get().setIndicatorsEnabled(true);

       }

        Picasso.get()
                .load(contact.getImages()).placeholder(R.drawable.roundp)
                .error(R.drawable.roundp).
                 fit()
                .centerCrop()
                .into(holder.imageview);

       holder.imageview.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v)
           {


                   Intent intent = new Intent(context, UserImageView.class);
                   intent.putExtra("Url", contact.getImages());
                   intent.putExtra("title",contact.getName());
                   context.startActivity(intent);


           }
          });

        holder.layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(), "name", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,MasterPage.class);
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
    public void setContacts(List<Contact> contacts)
    {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        CircleImageView imageview;
        TextView name;
        TextView phoneno;
        RelativeLayout layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.list_text);
            phoneno = itemView.findViewById(R.id.phone1);
            layout = itemView.findViewById(R.id.parent_view);

        }

        @Override
        public void onClick(View v)
        {


        }
    }
}
