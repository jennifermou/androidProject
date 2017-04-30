package com.example.jennifer.chat;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jennifer on 19/04/2017.
 */

public class Message {
    private int id;
    private int queue;
    private String author;
    private long timestamp;
    private String body;

    public Message(int id, int queue, String author, long timestamp, String body) {
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

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
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
        return "Message{" +
                ", author='" + author + '\'' +
                ", timestamp=" + timestamp +
                ", body='" + body + '\'' +
                '}';
    }


    public static Message fromJSON(String json) throws JSONException {
        String body;
        long timestamp;
        String author;

        JSONObject messageJSON = new JSONObject(json);

        body = messageJSON.optString("message");
        timestamp = messageJSON.optLong("timestamp");
        author = messageJSON.optString("author");

        Message message = new Message(0, 0, author,timestamp,body);
        return message;
    }

}
