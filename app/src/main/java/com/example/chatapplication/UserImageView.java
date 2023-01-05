package com.example.chatapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class UserImageView extends AppCompatActivity {

    private static final String TAG = "UserImageView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_image_view);
        getIncomingIntent();

    }

    private void getIncomingIntent()
    {
        Log.d(TAG,"getIncomingIntent:checking for incoming intent");
        if(getIntent().hasExtra("Url"))
        {
            Log.d(TAG,"getIncomingIntent:found incoming intent");
            String image=getIntent().getStringExtra("Url");
            setImage(image);

        }
    }
    private void setImage(String Imageurl)
    {
        ImageView image=findViewById(R.id.userImage);
        Picasso.get().
                load(Imageurl)
                .into(image);

    }
}