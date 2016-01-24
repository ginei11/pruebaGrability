package com.grability.pruebagrability.utilidades;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Primitives;
import com.grability.pruebagrability.constants.ConstantesGenerales;

import java.lang.reflect.Type;

/**
 * **************************************************************
 * Copyright (c) 2015 - 2016 Carlos Arturo Reyes Romero, All Rights reserved
 * <p/>
 * -
 * Descripcion de la clase
 * -
 * Autor:		Carlos Arturo Reyes Romero
 * email:		carr900@gmail.com
 * Creado:   	21/01/2016
 * Proyecto: 	PruebaGrability
 * ****************************************************************
 */
public class UtilidadGson {

    /**
     * Metodo que obtiene un DTO a partir de un String jSon.
     *
     * @author Carlos Arturo Reyes Romero
     * @param dato dato a convertir en dto
     * @param claseDeT clase que convierte el string
     * @return
     */
    public static <T> T obtenerDTO(String dato, Class<T> claseDeT) {

        Gson gson = null;
        Object object = null;

        if (dato != null) {

            if (dato.length() > 0) {

                gson = new GsonBuilder().serializeNulls().create();
                object = gson.fromJson(dato, (Type) claseDeT);
                Log.d(ConstantesGenerales.TAG, "obtener DTO objeto: " + object);
            }
        }

        return Primitives.wrap(claseDeT).cast(object);
    }
}
