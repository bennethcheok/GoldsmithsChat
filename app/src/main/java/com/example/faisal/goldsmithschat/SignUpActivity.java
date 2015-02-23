package com.hmkcode.android.sign;

import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.widget.*;

public class SignUpActivity extends Activity {

    private static final String signIn = "http://doc.gold.ac.uk/~ma301sc/softwareproject/php/DB_Functions.php";
    Button button = (Button) findViewById(R.id.btnSingIn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

            }
        });
        };

    public void sendMessage()
    {

    }
}