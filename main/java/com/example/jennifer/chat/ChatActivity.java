package com.example.jennifer.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jennifer on 19/04/2017.
 */

public class ChatActivity extends AppCompatActivity {

    private EditText messageText;
    private ListView listMessage;
    private Button sendButton;
    private List<Message> messages;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        messageText = (EditText) findViewById(R.id.messageText);
        listMessage = (ListView) findViewById(R.id.listMessage);
        sendButton = (Button) findViewById(R.id.sendButton);

        Message m1 = new Message(0, 0, "android", System.currentTimeMillis(), "Moi major pas vous");
        Message m2 = new Message(1, 1, "android", 230492343, "Moi major pas vous");
        Message m3 = new Message(2, 1, "android", 230492423, "Moi major pas vous");
        Message m4 = new Message(2, 1, "android", 230492423, "Moi major pas vous");

        messages = new ArrayList<Message>();
        adapter = new MessageAdapter(messages, this);

        messages.add(m1);
        messages.add(m2);
        messages.add(m3);

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
        Message m = new Message(5, 5 , "Major", 34142423, body);
        sendMessage(m);
    }

}
