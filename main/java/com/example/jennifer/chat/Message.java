package com.example.jennifer.chat;

import android.icu.text.SimpleDateFormat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jennifer on 19/04/2017.
 */

public class Message {
    private int id;
    private String queue;
    private String author;
    private long timestamp;
    private String body;

    public Message(int id, String queue, String author, long timestamp, String body) {
        this.id = id;
        this.queue = queue;
        this.author = author;
        this.timestamp = timestamp;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    @Override
    public String toString() {
        return"{" +
                "\"message\": " + JSONObject.quote(body) +
                ", \"author\": " + JSONObject.quote(author) +
                ", \"timestamp\": " + timestamp +
                "}";
    }


    public static Message fromJSON(String json) throws JSONException {
        String body;
        long timestamp;
        String author;
        Log.v("MESSAGE", json + " JSON MSSAGe");
        JSONObject messageJSON = new JSONObject(json);

        body = messageJSON.optString("message");
        timestamp = messageJSON.optLong("timestamp");
        author = messageJSON.optString("author");

        Message message = new Message(0, "", author, timestamp,body);
        return message;
    }

}
