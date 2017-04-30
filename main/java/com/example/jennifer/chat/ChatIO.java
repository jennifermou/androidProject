package com.example.jennifer.chat;

import android.content.Context;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
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
        String out = new Scanner(new URL("http://localhost:2017/android/0").openStream(), "UTF-8").useDelimiter("\\A").next();
        Message message = Message.fromJSON(out);
        return message;
    }


}
