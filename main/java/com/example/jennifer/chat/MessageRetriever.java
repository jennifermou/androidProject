package com.example.jennifer.chat;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by jennifer on 02/05/2017.
 */

public class MessageRetriever extends AsyncTask<String /* type des paramètres */, Message /* type de l'info de progression */, Void /* type du résultat final */>
{
    private WeakReference<ChatActivity> chatActivity = null;
    private static final String TAG = "CHATACITIVY";
    private int i = 0;
    public MessageRetriever (ChatActivity cActivity) {link(cActivity);}
    public void link (ChatActivity pActivity) {chatActivity = new WeakReference<ChatActivity>(pActivity);}


    @Override
    protected Void doInBackground(String[] params)
    {
        try {
            String url = params[0];

            ChatIO chat = new ChatIO();
            Message message;
            while( (message = chat.fetchMessage(url, "android", i)) != null) {
                publishProgress(message);
                i++;
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.v(TAG, e.toString());
        }

        return null;
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
        chatActivity.get().addReceivedMessage(values[i]);
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
