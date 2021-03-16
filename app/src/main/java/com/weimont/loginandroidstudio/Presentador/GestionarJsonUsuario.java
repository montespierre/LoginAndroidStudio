package com.weimont.loginandroidstudio.Presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.EditText;

import com.weimont.loginandroidstudio.Modelo.UsuarioJsonModel;

public class GestionarJsonUsuario {

    public Context context;
    public ProgressDialog progreso;
    private UsuarioJsonModel usuarioJsonModel;

    public GestionarJsonUsuario(Context context, ProgressDialog progreso){
        this.context = context;
        this.progreso = progreso;
        usuarioJsonModel = new UsuarioJsonModel(context, progreso);
    }

    public void iniciarSesion(final EditText nombreUsuario, final EditText passwordUsuario){
        try {
            usuarioJsonModel.IniciarSesion(nombreUsuario, passwordUsuario);

        }catch (Exception ex){
            throw ex;
        }
    }

}
