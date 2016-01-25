package com.grability.pruebagrability.impl;

import android.util.Log;

import com.grability.pruebagrability.constants.ConstantesComunicacion;
import com.grability.pruebagrability.constants.ConstantesGenerales;
import com.grability.pruebagrability.modelo.Author;
import com.grability.pruebagrability.modelo.Feed;
import com.grability.pruebagrability.modelo.FeedDTO;
import com.grability.pruebagrability.utilidades.Comunicacion;
import com.grability.pruebagrability.utilidades.UtilidadGson;

import java.util.HashMap;

/**
 * **************************************************************
 * Copyright (c) 2015 - 2016 Carlos Arturo Reyes Romero, All Rights reserved
 * <p/>
 * -
 * Clase que maneja la logica para el servicio de obtener la lista de apps
 * -
 * Autor:		Carlos Arturo Reyes Romero
 * email:		carr900@gmail.com
 * Creado:   	21/01/2016
 * Proyecto: 	PruebaGrability
 * ****************************************************************
 */
public class GetListApps {

    public FeedDTO obtenerDatos() {

        String respuesta = null;
        Comunicacion comunicacion = new Comunicacion();
        HashMap<String, String> parametros = new HashMap<String, String>();
        FeedDTO feedDTO= null;
        try {
            comunicacion = new Comunicacion();

            //Enviamos la trasaccion
            respuesta = comunicacion.enviarTransaccion(ConstantesComunicacion.SERVICIO_OBTENER_DATOS, parametros, ConstantesComunicacion.GET);
            respuesta=respuesta.replaceAll("im:name","imName");
            respuesta=respuesta.replaceAll("im:image", "imImage");
            respuesta=respuesta.replaceAll("im:price","imPrice");
            respuesta=respuesta.replaceAll("im:contentType","imContentType");
            respuesta=respuesta.replaceAll("im:artist","imArtist");
            respuesta=respuesta.replaceAll("im:releaseDate","imReleaseDate");

            Log.d(ConstantesGenerales.TAG, "respuesta String " + respuesta);

            //Convertimos al DTO especifico
            feedDTO = UtilidadGson.obtenerDTO(respuesta, FeedDTO.class);
            Log.d(ConstantesGenerales.TAG, "respuesta objecto= " + feedDTO.toString());


        } catch (Exception e) {
            Log.e(ConstantesGenerales.TAG,
                    "Error al obtener datos", e);
        }
        return feedDTO;

    }
}