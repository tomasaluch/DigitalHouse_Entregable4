package com.example.tomasaluch.entregable2java.DAO;



import android.os.AsyncTask;


import com.example.tomasaluch.entregable2java.Model.ContainerPaints;
import com.example.tomasaluch.entregable2java.Model.HTTPConectionsManager;
import com.example.tomasaluch.entregable2java.Model.Paint;
import com.example.tomasaluch.entregable2java.Model.ResultListener;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by digitalhouse on 24/05/17.
 */

public class DAOPaintInternet {



    public void obtenerProductosDeInternet(ResultListener<List<Paint>> listenerFromController){

        obtenerPaintsDeInternet tarea = new obtenerPaintsDeInternet(listenerFromController);
        tarea.execute();
    }

    private class obtenerPaintsDeInternet extends AsyncTask<String, Void, List<Paint>> {

        ResultListener<List<Paint>> listener;

        public obtenerPaintsDeInternet(ResultListener<List<Paint>> listener) {
            this.listener = listener;
        }

        @Override
        protected List<Paint> doInBackground(String... params) {

            ContainerPaints contenedora = null;
            try {
                HTTPConectionsManager connectionManager = new HTTPConectionsManager();
                String leerElJsonDeInternet = connectionManager.getRequestString("https://api.myjson.com/bins/x858r");

                Gson gson = new Gson();
                contenedora = gson.fromJson(leerElJsonDeInternet, ContainerPaints.class);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return contenedora.getPaints();
        }

        @Override
        protected void onPostExecute(List<Paint> paints) {
            listener.finish(paints);
        }
    }
}
