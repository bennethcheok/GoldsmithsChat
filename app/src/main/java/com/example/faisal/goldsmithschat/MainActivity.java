package com.hmkcode.android.sign;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    Button btnSignIn;
    Button btnSignUp;
    Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (Button) findViewById(R.id.btnSingIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        testButton = (Button) findViewById(R.id.testButton);

        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        testButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i = null;
        switch(v.getId()){
            case R.id.btnSingIn:
                i = new Intent(this,com.hmkcode.android.sign.SignInActivity.class);

                break;
            case R.id.btnSignUp:
                i = new Intent(this,com.hmkcode.android.sign.SignUpActivity.class);
                break;

            case R.id.testButton:
                i = new Intent(this, com.hmkcode.android.sign.ChatActivity.class);
                break;
        }
        startActivity(i);
    }



}