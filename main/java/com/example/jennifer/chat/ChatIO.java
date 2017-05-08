package com.example.jennifer.chat;

import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


/**
 * Created by jennifer on 30/04/2017.
 */

public class ChatIO {

    public ChatIO() {}


    /**
     * Ce code permet de récupérer un message en fonction de son identifiant et du serveur.
     * @param server
     * @param queue
     * @param id
     * @return Le message ou null si le timeout est dépassé.
     * @throws IOException
     * @throws JSONException
     */
    public Message fetchMessage(String server, String queue, int id) throws IOException, JSONException {
        URL website = new URL(server + String.valueOf(id));
        HttpURLConnection connection = (HttpURLConnection) website.openConnection();
        connection.connect();
        int responseHttp = connection.getResponseCode();

        if (responseHttp != 200) {
            return null;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();


        Log.v("CHATIO", response.toString() + " JSON DU CHATIO");
        return Message.fromJSON(response.toString());

    }


}
