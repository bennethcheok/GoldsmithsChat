package com.example.faisal.goldsmithschat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends Activity implements View.OnClickListener
{
    private String nick;

    Button button;

    EditText emailF;
    EditText passwordF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        button = (Button) findViewById(R.id.btnSignIn);

        emailF = (EditText) findViewById(R.id.email);
        passwordF = (EditText) findViewById(R.id.pass);

        button.setOnClickListener(this);

        emailF.setOnKeyListener(new View.OnKeyListener() {
                               @Override
                               public boolean onKey(View v, int keyCode, KeyEvent event) {
                                   if((event.getAction() == event.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                                   {
                                       passwordF.requestFocus();
                                       return true;
                                   }

                                   return false;
                               }
                           }
        );

        passwordF.setOnKeyListener(new View.OnKeyListener() {
                                @Override
                                public boolean onKey(View v, int keyCode, KeyEvent event) {
                                    if((event.getAction() == event.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                                    {
                                        button.performClick();
                                        return true;
                                    }

                                    return false;
                                }
                            }
        );
    }

    @Override
    public void onClick(View v)
    {
        v.setEnabled(false);

        switch(v.getId())
        {
            case R.id.btnSignIn:
                String email = emailF.getText().toString();
                String password = passwordF.getText().toString();

                System.out.println(email);
                System.out.println(password);

                nick = InitialConnection.signIn(email, password);
            break;
        }

        if(nick!=null)
        {
            Intent i = new Intent(this, com.example.faisal.goldsmithschat.ChatActivity.class);

            Bundle b = new Bundle();
            b.putString("nick", nick);
            i.putExtras(b);

            startActivity(i);
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert");
            builder.setCancelable(false);

            builder.setMessage("Sign in unsuccessful.");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    dialog.cancel();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });

            AlertDialog alert = builder.create();
            alert.show();

            System.out.println("User is not registered.");
        }

        v.setEnabled(true);
    }
}