package com.example.jennifer.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by jennifer on 19/04/2017.
 */

public class ChatActivity extends AppCompatActivity {

    private EditText messageText;
    private ListView listMessage;
    private Button sendButton;
    private List<Message> messages;
    private MessageAdapter adapter;
    private MessageRetriever retriever;
    private MessageSender sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        messageText = (EditText) findViewById(R.id.messageText);
        listMessage = (ListView) findViewById(R.id.listMessage);
        sendButton = (Button) findViewById(R.id.sendButton);
        sender = new MessageSender(ChatActivity.this);
        retriever = new MessageRetriever(ChatActivity.this);

        sender.execute("http://10.0.2.2:2017/android/");
        retriever.execute("http://10.0.2.2:2017/android/");


        messages = new ArrayList<Message>();
        adapter = new MessageAdapter(messages, this);

        listMessage.setAdapter(adapter);

    }

    public void clearChatList() {
        listMessage.setAdapter(null);
    }

    public void addReceivedMessage(Message message) {
        adapter.setNotifyOnChange(true);
        adapter.add(message);
        adapter.notifyDataSetChanged();

    }

    public void sendMessage(Message message) {
        Toast t = Toast.makeText(this, message.getBody(), Toast.LENGTH_SHORT);
        t.show();
    }

    
    public void OnClickSendMessage(View view){
        String body = messageText.getText().toString();
        Message m = new Message(5, "android" , "Major", System.currentTimeMillis(), body);
        String messageJson = JSONObject.quote(m.toString());
        Toast t = Toast.makeText(this, messageJson, Toast.LENGTH_SHORT);
        t.show();
    }


}
