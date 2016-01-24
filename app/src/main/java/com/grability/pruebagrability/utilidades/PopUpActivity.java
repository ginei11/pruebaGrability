/**
 * **************************************************************
 * Copyright (c) 2016 - Carlos Arturo Reyes Romero, All rights reserved
 * -
 * muestra un popup
 * -
 * Autor:		Carlos Arturo Reyes Romero
 * email:		carr900@gmail.com
 * Creado:   	23/01/2015
 * Proyecto: 	PruebaGrability
 * ****************************************************************
 */

package com.grability.pruebagrability.utilidades;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.grability.pruebagrability.R;
import com.grability.pruebagrability.constants.ConstantesGenerales;
import com.grability.pruebagrability.modelo.Entry;
import com.squareup.picasso.Picasso;



public class PopUpActivity extends Activity implements OnClickListener {
    private Entry entry;
    private ImageView mPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        Bundle datos = getIntent().getExtras();
        entry = new Entry();

        if (datos != null) {
            entry = (Entry) datos.getSerializable("entry");
        }

        Log.d(ConstantesGenerales.TAG, "entry: " + entry.toString());

        String titulo = "" + entry.getImName().getLabel();
        String descripcion = "" + entry.getSummary().getLabel();
        String fecha = "Fecha: " + entry.getImReleaseDate().getLabel();

        TextView mTxtTitulo = (TextView) findViewById(R.id.txt_titulo);
        TextView mTxtMensaje = (TextView) findViewById(R.id.txt_mensaje);

        TextView mTxtFecha = (TextView) findViewById(R.id.txt_fecha);

        Button mBtnAceptar = (Button) findViewById(R.id.acceptBtn);

        mPhoto = (ImageView) findViewById(R.id.photo);

        Picasso.with(getApplicationContext()).
                load(entry.getImImage().get(0).getLabel())
                .placeholder(getApplicationContext().getResources().getDrawable(R.mipmap.ic_app))
                .error(getApplicationContext().getResources().getDrawable(R.mipmap.ic_no_image))
                .into(mPhoto);

       

        mTxtTitulo.setText(titulo);
        mTxtMensaje.setText(descripcion);
        mTxtFecha.setText(fecha);

        mBtnAceptar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.acceptBtn:
                //Toast.makeText(getApplicationContext(), "Cerrando popup", Toast.LENGTH_SHORT).show();
                this.finish();
                break;
        }
    }
}