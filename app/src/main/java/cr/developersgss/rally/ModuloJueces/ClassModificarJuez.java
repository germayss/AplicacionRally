package cr.developersgss.rally.ModuloJueces;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cr.developersgss.rally.Objetos.TablaJuez;
import cr.developersgss.rally.R;

public class ClassModificarJuez extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    //registrar Juez
    //variables de la interfaz

    TextInputEditText txtusuario, txtnombre, txtcontrasena;
    TextView BuscarIDJuez;
    ProgressDialog progreso;//ventana de progreso
    //conexion con el ws
    RequestQueue request;
    JsonObjectRequest jor;
    String idintent;


    int actividad=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_modificar_juez);



        txtnombre = (TextInputEditText) findViewById(R.id.txtnombre);
        txtusuario = (TextInputEditText) findViewById(R.id.txtusuario);
        txtcontrasena = (TextInputEditText) findViewById(R.id.txtcontrasena);
        BuscarIDJuez =  findViewById(R.id.BuscarIDJuez);


        idintent = getIntent().getStringExtra("ID");

    ActualizarJuez();
    }

    private void ActualizarJuez() {

         if (actividad==1) {
            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this);
            progreso.setMessage("Buscando...");
            progreso.show();

            try {
                String url = "https://aplicacionrallygss.000webhostapp.com/BuscarIDJuez.php?IDJuez="+idintent;
                      //  BuscarIDJuez.getText().toString();
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try

        }
        else if (actividad==2){

            //modificar juez en la bd
            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this);
            progreso.setMessage("Modificando...");
            progreso.show();

            try {



                String url = "https://aplicacionrallygss.000webhostapp.com/ModificarJuez.php?"+
                        "IDJuez="+BuscarIDJuez.getText().toString()+
                        "&UsuarioJuez=" + txtusuario.getText().toString()+
                        "&NombreJuez=" + txtnombre.getText().toString()+
                        "&ContrasenaJuez=" + txtcontrasena.getText().toString();

                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try

        }else if (actividad==3){


            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this);
            progreso.setMessage("Eliminando...");
            progreso.show();

            try {

                String url = "https://aplicacionrallygss.000webhostapp.com/EliminarJuez.php?IDJuez="+BuscarIDJuez.getText().toString();
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try
        }

    }//termina modificar juez



    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();

        txtnombre.setText("");
        txtcontrasena.setText("");
        txtusuario.setText("");
        BuscarIDJuez.setText("");

        Toast.makeText(this, "No se pudo conectar verifique los datos " + error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());

    }

    @Override
    public void onResponse(JSONObject response) {

        if (actividad == 1) {
            progreso.hide();

            try {

                TablaJuez tablaJuez= new TablaJuez();
                JSONArray json_array =response.optJSONArray("juez");//rally es el identificador del json

                    JSONObject jsonObject= null;

                    jsonObject=json_array.getJSONObject(0);

                tablaJuez.setNombre(jsonObject.optString("NombreJuez"));
                tablaJuez.setUsuario(jsonObject.optString("UsuarioJuez"));
                tablaJuez.setContrasena(jsonObject.optString("ContrasenaJuez"));

                BuscarIDJuez.setText(idintent);
                txtnombre.setText(tablaJuez.getNombre());
                txtusuario.setText(tablaJuez.getUsuario());
                txtcontrasena.setText(tablaJuez.getContrasena());


            }catch (JSONException e){
                e.printStackTrace();
            }


        }
         else if(actividad==2) {  // limpa los botones y msj

            progreso.hide();

            txtnombre.setText("");
            txtcontrasena.setText("");
            txtusuario.setText("");
            BuscarIDJuez.setText("");
            Toast.makeText(this, "Se modifico un juez! ", Toast.LENGTH_SHORT).show();
        }
        else if (actividad==3){
            progreso.hide();

            txtnombre.setText("");
            txtcontrasena.setText("");
            txtusuario.setText("");
            BuscarIDJuez.setText("");
            Toast.makeText(this, "Se elimino un juez! ", Toast.LENGTH_SHORT).show();
        }

    }




    public void onClickEliminar(View view) {

        if(BuscarIDJuez.getText().toString().isEmpty()) {
            BuscarIDJuez.setError("VACIO");
        }else
        {
            actividad=3;
            ActualizarJuez();
            Intent SiguienteActividad = new Intent(ClassModificarJuez.this, ClassMenuJueces.class);
            startActivity(SiguienteActividad);
        }
    }// fin onclick

    public void onClickModificar(View view) {

        if (BuscarIDJuez.getText().toString().isEmpty()||txtcontrasena.getText().toString().isEmpty()||
                txtusuario.getText().toString().isEmpty()||txtnombre.getText().toString().isEmpty())
        {
            if(BuscarIDJuez.getText().toString().isEmpty()) {
                BuscarIDJuez.setError("VACIO");
            }
            if (txtcontrasena.getText().toString().isEmpty()){
                txtcontrasena.setError("VACIO");
            }
            if (txtusuario.getText().toString().isEmpty()){
                txtusuario.setError("VACIO");
            }

            if (txtnombre.getText().toString().isEmpty()){
                txtnombre.setError("VACIO");
            }

        }else {

           actividad=2;
           ActualizarJuez();
            Intent SiguienteActividad = new Intent(ClassModificarJuez.this, ClassMenuJueces.class);
            startActivity(SiguienteActividad);

        }

    }// fin onclick



}
