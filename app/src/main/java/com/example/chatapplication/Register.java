package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


import android.widget.Toast;

import com.example.chatapplication.dao.UserDao;
import com.example.chatapplication.database.ContactDatabase;
import com.example.chatapplication.entity.User;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Register extends AppCompatActivity {


    TextInputLayout username,password,phone_number,emailId;
    Button btn;

  private  ContactDatabase contactDatabase;
 private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        contactDatabase=ContactDatabase.getInstance(this);
        username= findViewById(R.id.userId);
        password= findViewById(R.id.password);
        phone_number= findViewById(R.id.Phone_number);
        emailId= findViewById(R.id.emailId);
        btn=(Button) findViewById(R.id.login);


       btn.setOnClickListener(v -> {
           userDao=contactDatabase.userDao();
           String user1= Objects.requireNonNull(username.getEditText()).getText().toString();
           String pass= Objects.requireNonNull(password.getEditText()).getText().toString();
           String phone= Objects.requireNonNull(phone_number.getEditText()).getText().toString();
           String email= Objects.requireNonNull(emailId.getEditText()).getText().toString();

           if(user1.equals("")||pass.equals("")||phone.equals("")||email.equals(""))
           {
               Toast.makeText(Register.this, "Fill all the Fields", Toast.LENGTH_SHORT).show();
           }
           else
           {
                   new InsertUserTask().execute(phone, email, user1, pass);

           }


       });


    }
    @SuppressLint("StaticFieldLeak")
    private class InsertUserTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String phone = params[0];
            String email = params[1];
            String user1 = params[2];
            String pass = params[3];

            userDao=contactDatabase.userDao();
            int userCount = userDao.countUsersWithPhoneNo(phone);
            if(userCount > 0) {
                return false;
            } else {
                User user = new User(phone, email, user1, pass);
                userDao.insertUser(user);
                return true;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(result) {
                Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Register.this, "username already exists", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //for login page navigation

    public void login(View view)
    {
        Intent intent=new Intent(Register.this,Login.class);
        startActivity(intent);
        finish();

    }
}