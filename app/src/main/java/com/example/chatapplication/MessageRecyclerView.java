package com.example.chatapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.dao.MessageDao;
import com.example.chatapplication.entity.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageRecyclerView extends RecyclerView.Adapter<MessageRecyclerView.ViewHolder> {

      private static final String Tag="MessageRecyclerAdapter";


      private  List<Message> mMessageList=new ArrayList<Message>();







    public MessageRecyclerView(LiveData<List<Message>> allMessages) {
    }


    @NonNull
    @Override
    public MessageRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_item_message, null);
        return new MessageRecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageRecyclerView.ViewHolder holder, int position)
    {
     Message message=mMessageList.get(position);

     holder.textView.setText(message.getMessage());


    }


    @Override
    public int getItemCount()
    {

        return mMessageList.size();
    }
@SuppressLint({ "NotifyDataSetChanged"})
  public void setMessage( List<Message> mMessageList)
  {
      this.mMessageList=mMessageList;
      notifyDataSetChanged();
  }

    public static class ViewHolder extends RecyclerView.ViewHolder {
      TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.senderText);

        }
    }
}
