package com.grability.pruebagrability.utilidades;

import android.util.Log;

import com.grability.pruebagrability.constants.ConstantesComunicacion;
import com.grability.pruebagrability.constants.ConstantesGenerales;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;

/**
 * **************************************************************
 * Copyright (c) 2015 - 2016 Carlos Arturo Reyes Romero, All Rights reserved
 * <p/>
 * -
 * Clase para consumir webservices
 * -
 * Autor:		Carlos Arturo Reyes Romero
 * email:		carr900@gmail.com
 * Creado:   	21/01/2016
 * Proyecto: 	PruebaGrability
 * ****************************************************************
 */
public class Comunicacion {
    /**
     * metodo que envia una transaccion a un web service
     * @param service servicio que va a consumir
     * @param parametros datos a enviar
     * @param tipo de peticion
     * @return
     */
    public String enviarTransaccion(String service,
                                    HashMap<String, String> parametros, String tipo) {
        String respuesta = null;
        Log.d(ConstantesGenerales.TAG+" ENVIAR SERVICIO ", " " + service);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = null;
            Response responses = null;
            String url="";

            if (tipo.equals(ConstantesComunicacion.GET)) {
                url = ConstantesComunicacion.URL_SERVICIOS + service + "?" + armarGet(parametros);
              //  Log.d(ConstantesGenerales.TAG+" parametros get ",""+url);
                request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
            } else if (tipo.equals(ConstantesComunicacion.POST)) {

                request = new Request.Builder()
                        .url(ConstantesComunicacion.URL_SERVICIOS + service)
                        .post(armarPost(parametros))
                        .build();

            }

            responses = client.newCall(request).execute();
            respuesta = responses.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return respuesta;
    }


    /**
     * Metodo que arma los parametros para una peticion GET
     * @param parametros hashmap de los parametros a enviar
     * @return
     */
    private String armarGet(HashMap<String, String> parametros){
        String parametroString="";
        String valor;
        for ( String llave : parametros.keySet() ) {
            valor= parametros.get(llave);
            if(parametroString.equals(""))
                parametroString+=llave+"="+valor;
            else parametroString+="&"+llave+"="+valor;
        }
       // Log.d(ConstantesGenerales.TAG+" parametros get ",""+parametroString);
        return parametroString;
    }


    /**
     * Metodo que arma los parametros para una peticion POST
     * @param parametros hashmap de los parametros a enviar
     * @return
     */
    private RequestBody armarPost(final HashMap<String, String> parametros) {
        FormEncodingBuilder formBody = new FormEncodingBuilder();
        String valor;
        for (String llave : parametros.keySet()) {
            valor= parametros.get(llave);
            formBody.add(llave,valor);
        }

     //   Log.d(ConstantesGenerales.TAG+" parametros post ",""+formBody.toString());
        return formBody.build();
    }
}
