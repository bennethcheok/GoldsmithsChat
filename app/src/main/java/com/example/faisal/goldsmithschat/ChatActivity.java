package com.example.faisal.goldsmithschat;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatActivity extends Activity
{
    public static String message;

    public static MyBot bot;
    public static EditText text;
    public static TextView serverText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
        StrictMode.setThreadPolicy(tp);

        message = "";
        text = (EditText) findViewById(R.id.UserText);

        serverText = (TextView) findViewById(R.id.ServerText);
        serverText.setMovementMethod(new ScrollingMovementMethod());

        button = (Button) findViewById(R.id.sendButton);

        init();
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
        }

        bot.joinChannel("#cs5");
    }

    public void sendMessage(View v)
    {
        String word = text.getText().toString();

        if(!word.equals(""))
        {
            System.out.println(word);
            bot.sendMessage("#cs5", word);
            serverText.append(bot.getName() + ": " + word + "\n");
            text.setText("");
            text.clearFocus();
        }
    }

    //static ui handler.
    public static Handler UIHandler;

    static
    {
        UIHandler = new Handler(Looper.getMainLooper());
    }

    public static void runOnUi(Runnable runnable)
    {
        UIHandler.post(runnable);
    }

    @Override
    protected void onPause()
    {
        bot.quitServer();
        super.onPause();
        finish();
    }
}