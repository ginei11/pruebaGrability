package com.grability.ventanas;

import android.content.Context;
import android.text.InputType;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.elasticbeanstalk.ventanas.R;

/**
 * **************************************************************
 * Copyright (c) 2015 - 2015 Carlos Arturo Reyes Romero, All rights reserved
 * <p>
 * -
 * Clase que implementa la libreria material dialog de afollestad
 * Documentacion: https://github.com/afollestad/material-dialogs
 * -
 * Autor:		Carlos Arturo Reyes Romero
 * email:		carr900@gmail.com
 * ****************************************************************
 */
public class Ventanas {

    private MaterialDialog.Builder dialog;
    private  MaterialDialog dialogProgress;
    /**
     * dialogo aleta con boton ok
     *
     * @param context
     * @param title    titulo de la notificacion
     * @param menssage mensaje en la notificacion
     */
    public void alert(Context context, String title, String menssage) {

        dialog = new MaterialDialog.Builder(context);
        dialog.title(title)
                .content(menssage)
                .positiveText(R.string.alertAcceptButton);
        dialog.show();
    }

    /**
     * dialogo con listener en aceptar y cancelar
     *
     * @param context
     * @param title
     * @param menssage
     * @param onAcceptButtonClickListener
     * @param onCancelButtonClickListener
     */
    public void dialog(Context context, String title, String menssage, MaterialDialog.SingleButtonCallback onAcceptButtonClickListener, MaterialDialog.SingleButtonCallback onCancelButtonClickListener) {

        dialog = new MaterialDialog.Builder(context);
        dialog.title(title)
                .content(menssage)
                .positiveText(R.string.alertAcceptButton)
                .negativeText(R.string.alertCancelButton)
                .onPositive(onAcceptButtonClickListener)
                .onNegative(onCancelButtonClickListener);
        dialog.show();
    }


    /**
     * @param context
     * @param title   texto a mostrar como titulo
     * @param mensaje texto a mostrar como contenido del cargango
     */
    public void loadingIndeterminate(Context context, String title, String mensaje) {

       dialogProgress= new MaterialDialog.Builder(context)
                .title(title)
                .content(mensaje)
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    public void stopLoading() {
        dialogProgress.cancel();
    }


    /**
     * @param context
     * @param title   texto a mostrar como titulo
     * @param mensaje texto a mostrar como contenido del cargango
     */
    public void inputDialog(Context context, String title, String mensaje,String hint, MaterialDialog.InputCallback listener) {

        new MaterialDialog.Builder(context)
                .title(title)
                .content(mensaje)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(hint, "", listener).show();
    }



}
