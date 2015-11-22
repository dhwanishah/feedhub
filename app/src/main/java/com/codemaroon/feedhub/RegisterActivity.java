package com.codemaroon.feedhub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    Button b1;
    EditText ed1,ed2,ed3;
    String userName;
    String password;
    String confirmPassword;

    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SharedPreferences feedMeUser;
        feedMeUser = getApplicationContext().getSharedPreferences("feedMeUser", getApplicationContext().MODE_PRIVATE);
        String uname = feedMeUser.getString("username", "null"); //2
        if (!uname.equals("null")) {
            Intent gotoMainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(gotoMainActivity);
            finish();
        }

        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = ed1.getText().toString();
                password = ed2.getText().toString();
                confirmPassword = ed3.getText().toString();
                String verify = "";
                if (userName.equals("")) verify = "blank_username";
                else if (!(userName.contains("@"))) verify = "invalid_email";
                else if (!(userName.substring(userName.length() - 4, userName.length()).equals(".edu")))
                    verify = "no_edu";
                else if (!(password.equals(confirmPassword))) verify = "invalid_password";
                else if (!(password.length() > 5)) verify = "length_error";

                if (verify.equals("")) {
                    Toast.makeText(getApplicationContext(), "Verifying...", Toast.LENGTH_SHORT).show();
                    ParseUser user = new ParseUser();
                    user.setUsername(userName);
                    user.setPassword(password);
                    user.put("userName", userName);// see if this works
                    user.put("password", password);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(com.parse.ParseException e) {
                            if (e == null) {

                                SharedPreferences feedMeUser;
                                SharedPreferences.Editor editor;
                                feedMeUser = getApplicationContext().getSharedPreferences("feedMeUser", getApplicationContext().MODE_PRIVATE); //1
                                editor = feedMeUser.edit(); //2
                                //editor.putString("userId", currentUserId.toString()); //3
                                editor.putString("username", userName); //3
                                editor.commit(); //4

                                Intent gotoMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(gotoMainActivity);
                                finish();
                            } else {
                                System.out.println(e.getMessage());
                                Toast.makeText(getApplicationContext(), "There was an error signing up.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else {
                    switch (verify) {
                        case "blank_username":
                            Toast.makeText(getApplicationContext(), "Blank username", Toast.LENGTH_SHORT).show();
                            break;
                        case "invalid_email":
                            Toast.makeText(getApplicationContext(), "Invalid email address...missing @", Toast.LENGTH_SHORT).show();
                            break;
                        case "no_edu":
                            Toast.makeText(getApplicationContext(), "Invalid email address...must be .edu", Toast.LENGTH_SHORT).show();
                            break;
                        case "invalid_password":
                            Toast.makeText(getApplicationContext(), "Invalid password confirmation", Toast.LENGTH_SHORT).show();
                            break;
                        case "length_error":
                            Toast.makeText(getApplicationContext(), "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }
}
