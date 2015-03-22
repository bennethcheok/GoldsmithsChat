package com.example.faisal.goldsmithschat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends Activity implements View.OnClickListener
{
    private String err;

    EditText nicknameF;
    EditText emailF;
    EditText passwordF;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);


        button = (Button) findViewById(R.id.btnSignUp);

        nicknameF = (EditText) findViewById(R.id.nickname);
        emailF = (EditText) findViewById(R.id.email);
        passwordF = (EditText) findViewById(R.id.pass);

        button.setOnClickListener(this);
        /*
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

            }
        });
        };*/
    }

    @Override
    public void onClick(View v)
    {
        v.setEnabled(false);

        switch (v.getId())
        {
            case R.id.btnSignUp:
                String nickname = nicknameF.getText().toString();
                String email = emailF.getText().toString();
                String password = passwordF.getText().toString();

                err = "";

                if(nickname.equals("") || email.equals("") || password.equals(""))
                {
                    err = "EmptyField";
                    break;
                }

                if(nickname.length() > 20 || email.length() > 50 || password.length() > 20)
                {
                    err = "ExceededLength";
                    break;
                }

                if(!email.contains("@"))
                {
                    err = "NotEmail";
                    break;
                }

                String [] val = email.split("@", 2);

                if(val.length < 2)
                {
                    err = "NotEmail";
                    break;
                }
                else
                {
                    for(int i = 0; i < val.length; i++)
                    {
                        if(val[i].equals(""))
                        {
                            err = "NotEmail";
                            break;
                        }
                    }
                }

                if(err.equals(""))
                {
                    if (!InitialConnection.signUp(nickname, email, password)) {
                        err = "InputRejected";
                    }
                }

            break;
        }

        String alertMessage = "";
        DialogInterface.OnClickListener affirmative = null;

        switch(err)
        {
            case "EmptyField":
                alertMessage = "Please ensure that none of the fields are empty.";
                affirmative = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                };

                System.out.println("Error: " + err);
            break;

            case "ExceededLength":
                alertMessage = "Please be informed that username and password have to be within 20 characters and email has to be within 50 characters";
                affirmative = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                };

                System.out.println("Error: " + err);
            break;

            case "NotEmail":
                alertMessage = "Please ensure that a valid email address is provided.";
                affirmative = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                };

                System.out.println("Error: " + err);
            break;

            case "InputRejected":
                alertMessage = "Sign up error. Please try a different username or email.";
                affirmative = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                };

                System.out.println("Error: " + err);
            break;

            case "":
                alertMessage = "You have successfully signed up. Please remember your email and password for login purpose.";
                affirmative = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                        finish();
                    }
                };
            break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert");
        builder.setCancelable(false);
        builder.setMessage(alertMessage);
        builder.setPositiveButton("OK", affirmative);

        AlertDialog alert = builder.create();
        alert.show();
    }
}