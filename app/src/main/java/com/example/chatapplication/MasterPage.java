package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.entity.Message;
import com.example.chatapplication.viewmodel.MessageViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MasterPage extends AppCompatActivity {

    private MenuItem mDeleteMenuItem;

    private static final String TAG = "ChatView";

    ImageView imageView;

    Toolbar toolbar;

    EditText editText;

    ImageButton imageButton;

    RecyclerView recyclerView;

    private final boolean isKeyboardVisible = false;

    MessageRecyclerView messageAdapter;

    private long sender_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_page);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getIncomingIntent();
        imageView=findViewById(R.id.backbutton);
        editText=findViewById(R.id.entermessage);
        imageButton=findViewById(R.id.sendButton);
        recyclerView=findViewById(R.id.chatlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        int sender=getIntent().getIntExtra("contactId",0);
        sender_id= (long) sender;
        MessageViewModel messageViewModel=new ViewModelProvider(this).get(MessageViewModel.class);
        messageAdapter=new MessageRecyclerView(messageViewModel.getMessagesBySenderId(sender_id));
        recyclerView.setAdapter(messageAdapter);

        messageViewModel.getMessagesBySenderId(sender_id).observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                messageAdapter.setMessage(messages);
                recyclerView.scrollToPosition(messages.size()-1);

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MasterPage.this,Dashboard.class);
                startActivity(intent);
            }
        });

        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r=new Rect();
                recyclerView.getWindowVisibleDisplayFrame(r);
                int screenHeight=recyclerView.getRootView().getHeight();
                int keypadHeight=screenHeight-r.bottom;

                if(keypadHeight>screenHeight * 0.15)
                {
                    int lastItemHeight = 0;
                    if (recyclerView.getLayoutManager() instanceof LinearLayoutManager layoutManager) {
                        View lastItem = layoutManager.findViewByPosition(messageAdapter.getItemCount()-1);
                        if (lastItem != null) {
                            lastItemHeight = lastItem.getHeight();
                        }
                    }

                    // Adjust the height of the RecyclerView to make sure the last item is visible
                    ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
                    params.height = screenHeight - keypadHeight - lastItemHeight;
                    recyclerView.setLayoutParams(params);


                    recyclerView.smoothScrollToPosition(messageAdapter.getItemCount()-1);

                }
                else {
                    // Reset the height of the RecyclerView to its original value
                    ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
                    params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    recyclerView.setLayoutParams(params);
                }
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }

        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {

                if(editText.getText().toString().equals(""))
                {
                    editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                String text = editText.getText().toString().trim();
                                if (text.isEmpty()) {
                                    editText.setError("Background cannot be empty.");
                                }
                            }
                        }
                    });
                }
                else
                {
                    Message message = new Message(editText.getText().toString(),sender_id);
                    message.setMessage(message.getMessage());
                    messageViewModel.insert(message,sender_id);
                    messageAdapter.notifyDataSetChanged();
                    editText.setText("");
                }

            }

        });

    }



    private void getIncomingIntent() {

        Log.d(TAG, "getIncomingIntent:checking for incoming intent");
        if (getIntent().hasExtra("Url") && getIntent().hasExtra("title")) {
            Log.d(TAG, "getIncomingIntent:found incoming intent");
            String image = getIntent().getStringExtra("Url");
            String Title = getIntent().getStringExtra("title");
            setImage(image, Title);

        }
    }

    private void setImage(String Imageurl, String title) {
        TextView username = findViewById(R.id.username);
        CircleImageView imageView = findViewById(R.id.profile_image);
        username.setText(title);
        Picasso.get().
                load(Imageurl)
                .resize(300,300)
                .into(imageView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.masterpage_menu,menu);
        mDeleteMenuItem=menu.findItem(R.id.menu_delete);
        mDeleteMenuItem.setVisible(false);
        return true;
    }
}