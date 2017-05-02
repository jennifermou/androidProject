package com.example.jennifer.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


/**
 * Created by jennifer on 30/04/2017.
 */

public class ChatIO {

    private static ChatIO instance;

    private ChatIO() {}

    public static ChatIO getInstance() {
        if (instance == null) instance = new ChatIO();
        return instance;
    }

    public Message fetchMessage(String server, String queue, int id) throws Exception {
        String urlString = "http://localhost:2017/android/0";

        // create the url
        URL url = new URL(urlString);

        // open the url stream, wrap it an a few "readers"
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        // write the output to stdout
        String line;
        String json = "";

        while ((line = reader.readLine()) != null)
        {
            json = line;
        }

        // close our reader
        reader.close();
        return Message.fromJSON(json);

    }


}
