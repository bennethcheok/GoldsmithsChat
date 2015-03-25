package com.example.faisal.goldsmithschat;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubChat extends Fragment implements View.OnClickListener
{
    public static String message;

    public static TextView channel;
    public static String channelName;

    public static EditText text;
    public static TextView serverText;
    Button button;

    private static Bundle b;

    View rootView;

    @Override
    public void onStart()
    {
        super.onStart();

        StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
        StrictMode.setThreadPolicy(tp);

        message = "";
        text = (EditText) getView().findViewById(R.id.UserText);

        channel = (TextView) getView().findViewById(R.id.ChatSection);
        channel.setText(getArguments().getString("ChatSection"));

        serverText = (TextView) getView().findViewById(R.id.ServerText);
        serverText.setMovementMethod(new ScrollingMovementMethod());

        button = (Button) getView().findViewById(R.id.sendButton);
        button.setOnClickListener(this);

        ChatActivity.bot.joinChannel("#" + getArguments().getString("ChatSection"));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_subchat);

        rootView = inflater.inflate(R.layout.activity_subchat, container, false);

        return rootView;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState)
    {

    }*/

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.sendButton:
                String word = text.getText().toString();

                if(!word.equals(""))
                {
                    System.out.println(word);
                    ChatActivity.bot.sendMessage("#" + getArguments().getString("ChatSection"), word);
                    serverText.append(ChatActivity.bot.getName() + ": " + word + "\n");
                    text.setText("");
                }
            break;
        }
    }

    /*public void switchChat(View v)
    {
        Intent i = new Intent(this, com.example.faisal.goldsmithschat.SubChat.class);
        startActivity(i);
    }*/

    public void onPause()
    {
        super.onPause();
        ChatActivity.bot.partChannel("#" + getArguments().getString("ChatSection"));
        //getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }

    public void onBackPressed()
    {

    }

    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);

        b = new Bundle();
        b.putString("ServerText", serverText.getText().toString());
    }

    public void onResume()
    {
        super.onResume();

        if(b != null)
        {
            if(channelName.equals(channel.getText().toString()))
                serverText.setText(b.getString("ServerText"));
        }

        channelName = channel.getText().toString();
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
}