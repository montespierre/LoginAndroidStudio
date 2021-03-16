package com.weimont.loginandroidstudio.Vistas.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.weimont.loginandroidstudio.Presentador.GestionarJsonUsuario;
import com.weimont.loginandroidstudio.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText nombreUsuario, passwordUsuario;
    Button vbtnIngresar;
    ProgressDialog progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        nombreUsuario = findViewById(R.id.idNombre);
        passwordUsuario = findViewById(R.id.idPassword);
        vbtnIngresar = findViewById(R.id.btnIngresar);

        vbtnIngresar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnIngresar:
                GestionarJsonUsuario jsonUsuario = new GestionarJsonUsuario(this, progreso);
                jsonUsuario.iniciarSesion(nombreUsuario, passwordUsuario);
                break;
        }

    }
}