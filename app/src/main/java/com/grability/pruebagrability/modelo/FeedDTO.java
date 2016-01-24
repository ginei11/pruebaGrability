package com.grability.pruebagrability.modelo;

import java.io.Serializable;

/**
 * **************************************************************
 * Copyright (c) 2015 - 2016 Carlos Arturo Reyes Romero, All rights reserved
 * <p/>
 * -
 * Descripcion de la clase
 * -
 * Autor:		Carlos Arturo Reyes Romero
 * email:		carr900@gmail.com
 * Creado:   	23/01/2016
 * Proyecto: 	PruebaGrability
 * ****************************************************************
 */
public class FeedDTO implements Serializable{
    private Feed feed;

    @Override
    public String toString() {
        return "FeedDTO{" +
                "feed=" + feed +
                '}';
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
