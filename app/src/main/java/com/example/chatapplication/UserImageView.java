package com.example.chatapplication;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.res.Resources;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class UserImageView extends AppCompatActivity {

    private static final String TAG = "UserImageView";

     Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_image_view);
        toolbar=findViewById(R.id.imageToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getIncomingIntent();
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
        ProgressBar progressBar=findViewById(R.id.progress);
        TextView textView=findViewById(R.id.ImageUserName);
        ImageView image=findViewById(R.id.userImage);
        textView.setText(title);
        Picasso.get().
                 load(Imageurl)
                .placeholder(R.drawable.placeholderimage).resize(500,400)
                 .into(image,new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onError(Exception e) {
                        progressBar.setVisibility(View.VISIBLE);

                    }
                });

       /* image.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                int screenwidth= Resources.getSystem().getDisplayMetrics().widthPixels;
                int screenheight= Resources.getSystem().getDisplayMetrics().heightPixels;
                int imagewidth=image.getDrawable().getIntrinsicWidth();
                int imageheight=image.getDrawable().getIntrinsicHeight();
                float scale=Math.min((float) screenwidth/imagewidth,(float)screenheight/imageheight);

                int x=(int)((screenwidth-imagewidth*scale)/2);
                int y=(int)((screenheight-imageheight*scale)/2);

                image.setTranslationX(x);
                image.setTranslationY(y);
                image.setScaleX(scale);
                image.setScaleY(scale);

                image.getViewTreeObserver().removeOnGlobalLayoutListener(this);


            }
        });*/


    }

}