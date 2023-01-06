package com.example.chatapplication;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class UserImageView extends AppCompatActivity {

    private static final String TAG = "UserImageView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_image_view);
        getIncomingIntent();
        ActionBar actionBar = getSupportActionBar();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



    }

    private void getIncomingIntent()
    {
        Log.d(TAG,"getIncomingIntent:checking for incoming intent");
        if(getIntent().hasExtra("Url")&&getIntent().hasExtra("title"))
        {
            Log.d(TAG,"getIncomingIntent:found incoming intent");
            String image=getIntent().getStringExtra("Url");
            String Title=getIntent().getStringExtra("title");
            setImage(image,Title);

        }
    }
    private void setImage(String Imageurl,String title)
    {
        ImageView image=findViewById(R.id.userImage);
        Picasso.get().
                load(Imageurl)
                .placeholder(R.drawable.image).fit()
                .into(image);


    }

}