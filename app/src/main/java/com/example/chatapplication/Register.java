package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {


    TextInputLayout username,password,phone_number,emailId;
    Button btn;
    DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

       username= findViewById(R.id.userId);
        password= findViewById(R.id.password);
        phone_number= findViewById(R.id.Phone_number);
        emailId= findViewById(R.id.emailId);
        btn=(Button) findViewById(R.id.login);

        myDB=new DBHelper(this);

       btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String user=username.getEditText().getText().toString();
                 String pass=password.getEditText().getText().toString();
                 String phone=phone_number.getEditText().getText().toString();
                 String email=emailId.getEditText().getText().toString();

                 if(user.equals("")||pass.equals("")||phone.equals("")||email.equals(""))
                 {
                     Toast.makeText(Register.this, "Fill all the Fields", Toast.LENGTH_SHORT).show();
                 }
                 else {
                     //check username already exists in database
                         Boolean result= myDB.checkusername(user);

                         if(result==false)
                         {
                             //insert all values in the database
                             Boolean insertResult=myDB.insertData(user,pass,phone,email);
                             if(insertResult==true)
                             {
                                 Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                 Intent intent=new Intent(Register.this,Login.class);
                                 startActivity(intent);
                                 finish();
                             }

                             //if not inserted
                             else {
                                 Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                             }

                         }
                         //username already exists
                         else
                         {
                             Toast.makeText(Register.this, "username already exists", Toast.LENGTH_SHORT).show();
                         }
                         

                 }


             }
         });


    }

    //for login page navigation

    public void login(View view)
    {
        Intent intent=new Intent(Register.this,Login.class);
        startActivity(intent);
        finish();

    }
}