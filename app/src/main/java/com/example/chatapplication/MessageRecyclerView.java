package com.example.chatapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.entity.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageRecyclerView extends RecyclerView.Adapter<MessageRecyclerView.ViewHolder> {

      private  Context context;
       private  List<Message> mMessageList=new ArrayList<Message>();

    public MessageRecyclerView(Context context,List<Message> mMessageList) {

        this.context = context;
        this.mMessageList=mMessageList;
    }


    @NonNull
    @Override
    public MessageRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageRecyclerView.ViewHolder holder, int position)
    {


    }

    @Override
    public int getItemCount()
    {

        return 0;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText editText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            editText=itemView.findViewById(R.id.entermessage);

        }
    }
}
