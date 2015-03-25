package com.example.faisal.goldsmithschat;

import org.jibble.pircbot.PircBot;

public class MyBot extends PircBot
{
    public static String record;
    public static boolean incomingMessage;

    public MyBot(String name)
    {
        this.setName(name);
        record = "";
        incomingMessage = false;

    }

    public void onMessage (String channel, final String sender, String login, String
            hostname, final String message) {

        record += message + "\n";
        //final String input = record;

        //System.out.println("A message have been received.");

        /*if (message.equalsIgnoreCase("time")) {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": The time is now " + time);
        }*/

        SubChat.runOnUi(
                new Runnable() {
                    @Override
                    public void run() {
                        SubChat.serverText.append(sender + ": " + message + "\n");
                    }
                });
    }
}
