package com.example.chatapplication;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity
{

    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        textView=findViewById(R.id.textView);
        textView.setSystemUiVisibility(TextView.SYSTEM_UI_FLAG_HIDE_NAVIGATION|TextView.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION );
        int splash_timeout = 4000;
        new Handler().postDelayed(() -> {
            Intent intent=new Intent(SplashScreen.this, Login.class);
            startActivity(intent);
            finish();

        }, splash_timeout);


        Animation animation= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.splashanimation);
        textView.startAnimation(animation);


    }
}
