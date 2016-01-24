package com.grability.pruebagrability.modelo;

import java.io.Serializable;

/**
 * **************************************************************
 * Copyright (c) 2015 - 2016 Carlos Arturo Reyes Romero, All Rights reserved
 * <p>
 * -
 * Descripcion de la clase
 * -
 * Autor:		Carlos Arturo Reyes Romero
 * email:		carr900@gmail.com
 * Creado:   	23/01/2016
 * Proyecto: 	PruebaGrability
 * ****************************************************************
 */
public class Id implements Serializable{
    private String Label;
    private Attributes attributes;

    @Override
    public String toString() {
        return "Id{" +
                "Label='" + Label + '\'' +
                ", attributes=" + attributes +
                '}';
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}
