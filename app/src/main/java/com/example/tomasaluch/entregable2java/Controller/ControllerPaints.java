package com.example.tomasaluch.entregable2java.Controller;

import com.example.tomasaluch.entregable2java.DAO.DAOPaintInternet;
import com.example.tomasaluch.entregable2java.Model.Paint;
import com.example.tomasaluch.entregable2java.Model.ResultListener;

import java.util.List;

/**
 * Created by tomasaluch on 30/11/17.
 */

public class ControllerPaints {

    DAOPaintInternet daoPaints=new DAOPaintInternet();


    public void obtenerPaints(final ResultListener<List<Paint>> listenerFromView){
        daoPaints.obtenerProductosDeInternet(new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> resultado) {
                if(resultado != null){
                    listenerFromView.finish(resultado);
                }
            }

        });
    }
}
