package com.example.faisal.goldsmithschat;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;

public class ChatActivity extends FragmentActivity
{
    public static MyBot bot;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
        StrictMode.setThreadPolicy(tp);

        init();

        Intent i = new Intent(this, com.example.faisal.goldsmithschat.MainChat.class);
        startActivity(i);
    }

    public void init()
    {
        Bundle b = getIntent().getExtras();

        bot = new MyBot(b.getString("nick"));
        bot.setVerbose(false);

        try {
            bot.connect("igor.gold.ac.uk", 8888);
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(MainChat.engaged)
        {
            MainChat.engaged = false;
            bot.quitServer();
            finish();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }
}