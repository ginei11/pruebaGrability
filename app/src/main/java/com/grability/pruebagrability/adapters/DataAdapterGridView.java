package com.grability.pruebagrability.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.grability.pruebagrability.R;
import com.grability.pruebagrability.modelo.Entry;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * **************************************************************
 * Copyright (c) 2015 - 2015 Carlos Arturo Reyes Romero, All Rights reserved
 * <p/>
 * -
 * Adaptador de vista para muestra 4 columnas por el numero de filas necesarias
 * -
 * Autor:		Carlos Arturo Reyes Romero
 * email:		carr900@gmail.com
 * Creado:   	23/01/2015
 * Proyecto: 	PruebaGrability
 * ****************************************************************
 */
public class DataAdapterGridView extends BaseAdapter {
    private Context context;
    private List<Entry> entries;

    public DataAdapterGridView(Context context, List<Entry> entries) {
        this.context = context;
        this.entries = entries;
    }


    @Override
    public int getCount() {
        return entries.size();

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_gridview, viewGroup, false);
        }

        final Context mContext=view.getContext();
        ImageView imagen = (ImageView) view.findViewById(R.id.imagen);
        TextView nombre = (TextView) view.findViewById(R.id.nombre);

        //seteamos la imagen por defecto
        imagen.setImageResource(R.mipmap.ic_no_image);
        //se carga en lina las fotos
        Picasso.with(context).
                load(entries.get(position).getImImage().get(0).getLabel())
                .placeholder(context.getResources().getDrawable(R.mipmap.ic_app))
                .error(context.getResources().getDrawable(R.mipmap.ic_no_image))
                .into(imagen);
        //Seteamos el texto debajo de la imagen
        nombre.setText(entries.get(position).getImName().getLabel());

        imagen.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"hizo click", Toast.LENGTH_SHORT).show();
            //    Mostramos un mensaje
            //    Ventanas ventanas= new Ventanas();
            //    ventanas.alert(mContext,"","La foto tiene la siguiente carasteristica: "+dataGrids[position].getFeature() );
            //    Snackbar.make(finalView.getRootView(),dataGrids[position].getFeature() , Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return false;
            }
        });

        return view;
    }





}