package com.example.chatapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MasterPage extends AppCompatActivity {


    private static final String TAG = "ChatView";
    Toolbar toolbar;

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
                load(Imageurl).placeholder(R.drawable.placeholderimage)
                .fit()
                .centerInside()
                .into(imageView);
    }

}