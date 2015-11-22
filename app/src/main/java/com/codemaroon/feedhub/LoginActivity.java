package com.codemaroon.feedhub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    Button b1,b3;
    EditText ed1,ed2;

    String password;
    String username;

    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences feedMeUser;
        feedMeUser = getApplicationContext().getSharedPreferences("feedMeUser", getApplicationContext().MODE_PRIVATE);
        String uname = feedMeUser.getString("username", "null"); //2
        if (!uname.equals("null")) {
            Intent gotoMainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(gotoMainActivity);
            finish();
        }


        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "TwyMSUuSAQ8PN4k5jN9WlGQuND5GW9qg0nPqphX4", "IB4FLyPQ64W6Nje3O6bRMlYqfjQfq0DNyXDRo4Xw");

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent gotoMainActivity = new Intent(getApplicationContext(), MainActivity.class); //where we send it if logged in
            startActivity(gotoMainActivity);
            finish();
        }

        b1=(Button)findViewById(R.id.button);
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);

        b3=(Button)findViewById(R.id.button3);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = ed1.getText().toString();
                password = ed2.getText().toString();

                if (true) // if the password and username are valid
                {
                    Toast.makeText(getApplicationContext(), "Verifying...", Toast.LENGTH_SHORT).show();
                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, com.parse.ParseException e) {
                            if( user!= null) {
                                currentUserId = ParseUser.getCurrentUser().getObjectId();

                                System.out.println(currentUserId + " useIDDD");

                                SharedPreferences feedMeUser;
                                SharedPreferences.Editor editor;
                                feedMeUser = getApplicationContext().getSharedPreferences("feedMeUser", getApplicationContext().MODE_PRIVATE); //1
                                editor = feedMeUser.edit(); //2
                                //editor.putString("userId", currentUserId.toString()); //3
                                editor.putString("username", username); //3
                                editor.commit(); //4

                                Intent gotoMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(gotoMainActivity);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"There was an error logging in.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegisterActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(gotoRegisterActivity);
            }
        });
    }
}
