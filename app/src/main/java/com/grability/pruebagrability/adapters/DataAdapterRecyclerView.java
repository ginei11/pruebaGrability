package com.grability.pruebagrability.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.grability.pruebagrability.R;
import com.grability.pruebagrability.modelo.Entry;
import com.grability.pruebagrability.utilidades.PopUpActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * **************************************************************
 * Copyright (c) 2015 - 2016 Carlos Arturo Reyes Romero, All rights reserved
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
public class DataAdapterRecyclerView extends RecyclerView.Adapter<DataAdapterRecyclerView.APPSViewHolder> {

    public static class APPSViewHolder extends RecyclerView.ViewHolder {

        private CardView cv;
        private ImageView iconoApp;
        private TextView titleRv, subtitleRv, txtArtist;
        private RelativeLayout item_cv;
        private View view;

        APPSViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            cv = (CardView) itemView.findViewById(R.id.cv);
            iconoApp= (ImageView) itemView.findViewById(R.id.img_app);
            titleRv = (TextView) itemView.findViewById(R.id.title_rv);
            subtitleRv = (TextView) itemView.findViewById(R.id.subtitle_rv);

            txtArtist = (TextView) itemView.findViewById(R.id.txt_artist);

            item_cv = (RelativeLayout) itemView.findViewById(R.id.item_cv);
        }
    }


    private List<Entry> entries = new ArrayList<Entry>();
    private Context context;


    public DataAdapterRecyclerView(Context context, List<Entry> entries) {
        this.context = context;
        this.entries = entries;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public APPSViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_cv_list_apps, viewGroup, false);
        APPSViewHolder pvh = new APPSViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final APPSViewHolder driverViewHolder, final int i) {

        //setea el titulo de la app
        driverViewHolder.titleRv.setText(entries.get(i).getImName().getLabel());
        //setea la descripcion de la app
        driverViewHolder.subtitleRv.setText(entries.get(i).getCategory().getAttributes().getLabel());
        //Cargamos el nombre del desarrollador
        driverViewHolder.txtArtist.setText(entries.get(i).getImArtist().getLabel());

        //Se carga la imagen del objeto
        Picasso.with(context).
                load(entries.get(i).getImImage().get(0).getLabel())
                .placeholder(context.getResources().getDrawable(R.mipmap.ic_app))
                .error(context.getResources().getDrawable(R.mipmap.ic_no_image))
                .into(driverViewHolder.iconoApp);


        // Listener para mostrar descripcion en la lista
        driverViewHolder.item_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent popUpIntent = new Intent(context, PopUpActivity.class);
                //pasamos el objeto a la actividad
                Bundle datos = new Bundle();
                datos.putSerializable("entry", entries.get(i));

                popUpIntent.putExtras(datos);
                popUpIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //Se inicia la actividad
                context.startActivity(popUpIntent);



            }
        });

    }



    @Override
    public int getItemCount() {
        return entries.size();
    }
}