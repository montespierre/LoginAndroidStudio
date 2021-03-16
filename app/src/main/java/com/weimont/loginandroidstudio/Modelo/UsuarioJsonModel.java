package com.weimont.loginandroidstudio.Modelo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.weimont.loginandroidstudio.Entidades.Usuario;
import com.weimont.loginandroidstudio.R;
import com.weimont.loginandroidstudio.Vistas.Activities.LoginActivity;
import com.weimont.loginandroidstudio.Vistas.Activities.MainActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UsuarioJsonModel {

    ProgressDialog progreso;
    Context context;

    public UsuarioJsonModel(Context context, ProgressDialog progreso){
        this.context = context;
        this.progreso = progreso;
    }

    public void IniciarSesion(final EditText nombreUsuario, final EditText passwordUsuario){

        progreso = new ProgressDialog(context);
        progreso.setMessage("Cargando...");
        progreso.show();

        String url = "http://" + context.getString(R.string.ip_servidor) + "/api/usuarios/login";

        Map<String, String> parametros = new HashMap<>();
        parametros.put("nombreUsuario", nombreUsuario.getText().toString());
        parametros.put("passwordUsuario", passwordUsuario.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(parametros), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Usuario usuario = new Usuario();

                try {
                    if(!response.optString("nombre").equals("No existe")){
                        if(response.optString("nombreUsuario").equals(nombreUsuario.getText().toString()) && response.optString("passwordUsuario").equals(passwordUsuario.getText().toString())){

                            usuario.setId(response.optInt("id"));
                            usuario.setNombre(response.optString("nombre"));
                            usuario.setApellidos(response.optString("apellidos"));
                            usuario.setEmail(response.optString("email"));
                            usuario.setRol(response.optString("rol"));

                            //para viajar de content(Login) a mainActivity
                            Intent inten = new Intent(context, MainActivity.class);
                            context.startActivity(inten);
                            Toast.makeText(context, "Bienvenido!" + usuario.getApellidos() + usuario.getNombre(), Toast.LENGTH_LONG).show();


                        } else {
                            Toast.makeText(context, "Usuario o password invalido" + usuario.getApellidos() + usuario.getNombre(), Toast.LENGTH_LONG).show();
                        }



                    }else{
                        Toast.makeText(context, "Usuario o password invalido!" + usuario.getApellidos() + usuario.getNombre(), Toast.LENGTH_LONG).show();
                    }

                    progreso.hide();
                }catch (Exception ex){
                    Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }


                );
        //Patrong Singleton
        PatronSingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);


    }


}
