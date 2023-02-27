package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageButton;
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


    private static final String TAG = "ChatView";


    Toolbar toolbar;

    EditText editText;

    ImageButton imageButton;

    RecyclerView recyclerView;



    MessageRecyclerView messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_page);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getIncomingIntent();

        editText=findViewById(R.id.entermessage);
        imageButton=findViewById(R.id.sendButton);
        recyclerView=findViewById(R.id.chatlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        MessageViewModel messageViewModel=new ViewModelProvider(this).get(MessageViewModel.class);
        messageAdapter=new MessageRecyclerView(messageViewModel.getAllMessages());
        recyclerView.setAdapter(messageAdapter);

        messageViewModel.getAllMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                messageAdapter.setMessage(messages);
                recyclerView.scrollToPosition(messages.size()-1);


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

                    recyclerView.smoothScrollToPosition(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() - 1);

                }

            }

        });


 // screen reduces when keyboard appears scrolling

     /*   class SoftKeyboardStateWatcher implements ViewTreeObserver.OnGlobalLayoutListener {

            private View rootView ;
            private final int previousHeight = 0;
            private SoftKeyboardStateListener listener;

            public SoftKeyboardStateWatcher(View rootView, RecyclerView recyclerView) {
                this.rootView = rootView;
                rootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
            }

            public void setListener(SoftKeyboardStateListener listener) {
                this.listener = listener;
            }


            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);

                int screenHeight = rootView.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15)
                {
                    if (listener != null) {
                        listener.onSoftKeyboardOpened(keypadHeight);
                        recyclerView.scrollToPosition(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount()-1);
                    }
                }
                else
                {
                    if (listener != null)
                    {
                        listener.onSoftKeyboardClosed();
                    }
                }
            }

            public interface SoftKeyboardStateListener {
                void onSoftKeyboardOpened(int keyboardHeight);

                void onSoftKeyboardClosed();
            }


        }
*/

       imageButton.setOnClickListener(new View.OnClickListener() {
           @SuppressLint("NotifyDataSetChanged")
           @Override
           public void onClick(View v) {
               Message message=new Message(editText.getText().toString());
               message.setSender_id(message.getSender_id());
               message.setMessage(message.getMessage());
               messageViewModel.insert(message);
               messageAdapter.notifyDataSetChanged();
               editText.setText("");

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
                .fit()
                .into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.masterpage_menu,menu);
        return true;
    }
}