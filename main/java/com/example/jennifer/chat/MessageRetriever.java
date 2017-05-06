package com.example.jennifer.chat;

import android.os.AsyncTask;

/**
 * Created by jennifer on 02/05/2017.
 */

public class MessageRetriever extends AsyncTask<String /* type des paramètres */, Message /* type de l'info de progression */, Void /* type du résultat final */>
{
    @Override
    protected Void doInBackground(String[] params)
    {

        String url = params[0];
        int count = url.length();
        for (int i = 0; i < count; i++) {

        }
        // nous implantons ici la boucle de récupération des messages
        // ce code n'est pas exécuté sur la thread principale et ne bloque donc pas l'UI
        // il faut vérifier régulièrement avec isCancelled() si l'on doit sortir de la méthode
        // pour cause d'annulation de la tâche
        // entre-temps à chaque fois que nous récupérons un message, nous appelons publishProgress(message)
        // pour que ce message soit publié comme information de progrès et provoque l'appel à onProgressUpdate()
        // dans la thread principale s'occupant de l'UI
        // dans la thread principale, on pourra appeler la méthode cancel() de l'AsyncTask pour l'annuler
        // typiquement on lance l'AsyncTask en appelant execute() dans la méthode onResume() de l'activité
        // et on la stoppe en appelant cancel(true) dans la méthode onPause() de l'activité
        // cela évite de faire travailler inutilement notre tâche alors que l'activité est en arrière-plan
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
