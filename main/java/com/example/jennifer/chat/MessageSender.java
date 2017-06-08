package com.example.jennifer.chat;

import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by jennifer on 03/06/2017.
 */

public class MessageSender extends AsyncTask<String /* type des paramètres */, Message /* type de l'info de progression */, Void /* type du résultat final */> {
    private WeakReference<ChatActivity> chatActivity = null;
    private static final String TAG = "CHATACTIVITY";

    public MessageSender (ChatActivity cActivity) {link(cActivity);}
    public void link (ChatActivity pActivity) {chatActivity = new WeakReference<ChatActivity>(pActivity);}

    @Override
    protected Void doInBackground(String... params) {
        return null;
    }

    public static void postURLEncoder(HttpURLConnection conn, Map<String, ?> data) throws IOException
    {
        // Encode data
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setChunkedStreamingMode(0); // We don't already know the total size, does not work with HTTP/1.0
        conn.connect();
        Writer w = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "ASCII"));
        boolean first = true;
        for (Map.Entry<String, ?> entry: data.entrySet())
        {
            if (! first) w.write("&"); // Add a separator
            else first = false;
            w.write(URLEncoder.encode(entry.getKey(), "UTF-8"));
            w.write("=");
            w.write(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
        }
        w.close();
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        // ce code est exécuté sur la thread principale avant le démarrage de la tâche de récupération
    }

    @Override
    protected void onPostExecute(Void o)
    {
        super.onPostExecute(o);
        // ce code est exécuté sur la thread principale après la fin de la tâche
        // il peut être intéressant pour publier sur l'UI le résultat final de la tâche
        // ici ce n'est pas utile car nous nous intéressions plutôt aux informations de progrès
        // plutôt qu'au résultat final
    }

    @Override
    protected void onProgressUpdate(Message... values)
    {
        super.onProgressUpdate(values);
        chatActivity.get().addReceivedMessage(values[0]);
        // on récupère ici les messages postés avec publishProgress()
        // le code de cette méthode est exécuté sur la thread principale
        // on peut donc l'utiliser pour mettre à jour l'UI en appelant la méthode
        // addReceivedMessage(message) de l'activité
        // la difficulté est qu'il faut avoir une référence vers l'instance de l'activité en cours
        // d'exécution: on peut rajouter une méthode statique getInstance() dans ChatActivity qui retourne
        // l'instance courante (avec initialisation dans le onCreate())
    }

    @Override
    protected void onCancelled()
    {
        super.onCancelled();
        // code exécuté sur la thread principale lorsque la tâche est annulée
    }

}
