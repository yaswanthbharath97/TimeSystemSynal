package com.example.chatapplication;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity
{

private  static int splash_timeout=4000;
TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        textView=findViewById(R.id.textView);
        textView.setSystemUiVisibility(TextView.SYSTEM_UI_FLAG_HIDE_NAVIGATION|TextView.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION );
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
                finish();

            }
        },splash_timeout);


        Animation animation= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.splashanimation);
        textView.startAnimation(animation);


    }
}
