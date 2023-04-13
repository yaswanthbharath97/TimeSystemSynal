package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.example.chatapplication.dao.UserDao;
import com.example.chatapplication.database.ContactDatabase;
import com.example.chatapplication.entity.User;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

    private ContactDatabase contactDatabase;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        btn = findViewById(R.id.loginButton);

        contactDatabase = ContactDatabase.getInstance(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userDao = contactDatabase.userDao();
                String userPhoneNo = Objects.requireNonNull(username.getEditText()).getText().toString();
                String pass = Objects.requireNonNull(password.getEditText()).getText().toString();
                if (userPhoneNo.equals("") || pass.equals("")) {
                    Toast.makeText(Login.this, "please enter \n Credentials ", Toast.LENGTH_SHORT).show();
                } else {


                    new LoginAsyncTask(Login.this, contactDatabase, userPhoneNo, pass).execute();
                }


            }
        });
    }
        private static class LoginAsyncTask extends AsyncTask<Void, Void, User> {

        @SuppressLint("StaticFieldLeak")
        private final Context context;
        private final ContactDatabase database;
        private final String userPhoneNo;
        private final String pass;

        LoginAsyncTask(Context context, ContactDatabase database, String userPhoneNo, String pass) {
            this.context = context;
            this.database = database;
            this.userPhoneNo = userPhoneNo;
            this.pass = pass;
        }

        @Override
        protected User doInBackground(Void... voids) {
            UserDao userDao = database.userDao();
            User result = userDao.getUser(userPhoneNo, pass);
            return result;
        }

        @Override
        protected void onPostExecute(User result) {
            if(result!=null)
            {
                Intent intent=new Intent(context,Dashboard.class);
                context.startActivity(intent);
                ((Login)context).finish();
            }
            else
            {
                Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }

        public void register(View view)
        {
            Intent intent=new Intent(Login.this,Register.class);
            startActivity(intent);
            finish();

        }




}