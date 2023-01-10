package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Login extends AppCompatActivity {

    TextInputLayout username ,password;
    Button btn;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username=findViewById(R.id.usernameLogin);
        password=findViewById(R.id.passwordLogin);
        btn=findViewById(R.id.loginButton);


        myDB=new DBHelper(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user= Objects.requireNonNull(username.getEditText()).getText().toString();
                String pass= Objects.requireNonNull(password.getEditText()).getText().toString();
                if( user.equals("")||pass.equals(""))
                {

                    Toast.makeText(Login.this, "please enter \n Credentials ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                   Boolean result=myDB.checkusernamePassword(user,pass);
                   if(result==true)
                   {
                      Intent intent=new Intent(Login.this,Dashboard.class);
                      startActivity(intent);
                      finish();
                   }
                   else
                   {

                       Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                   }
                }


            }
        });




    }



    public void register(View view)
    {
        Intent intent=new Intent(Login.this,Register.class);
        startActivity(intent);
        finish();

    }
}