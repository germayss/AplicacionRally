package cr.developersgss.rally.ModuloEquipos;

import android.app.AlertDialog;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cr.developersgss.rally.ModuloJueces.ClassMenuJueces;
import cr.developersgss.rally.ModuloJueces.ClassModificarJuez;
import cr.developersgss.rally.Objetos.TablaJuez;
import cr.developersgss.rally.R;

/**
 * Created by germa on 24/11/2017.
 */

public class ClassModificarEquipo  extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {


    TextInputEditText txtusuario, txtnombre, txtcontrasena;
    TextView BuscarIDEquipo;
    ProgressDialog progreso;//ventana de progreso
    //conexion con el ws
    RequestQueue request;
    JsonObjectRequest jor;
    String idintent;

    int actividad = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_modificarequipo);


        txtnombre = (TextInputEditText) findViewById(R.id.txtnombreE);
        txtusuario = (TextInputEditText) findViewById(R.id.txtusuarioE);
        txtcontrasena = (TextInputEditText) findViewById(R.id.txtcontrasenaE);
        BuscarIDEquipo = findViewById(R.id.BuscarIDEquipo);


        idintent = getIntent().getStringExtra("ID");

        ActualizarEquipo();
    }

    private void ActualizarEquipo() {
        if (actividad==1) {
            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this, AlertDialog.THEME_HOLO_DARK);
            progreso.setMessage("Buscando...");
            progreso.show();

            try {
                String url = "https://aplicacionrallygss.000webhostapp.com/BuscarIDEquipo.php?IDEquipo="+idintent;
                //  BuscarIDJuez.getText().toString();
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try

        }
        else if (actividad==2){

            //modificar juez en la bd
            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this,AlertDialog.THEME_HOLO_DARK);
            progreso.setMessage("Modificando...");
            progreso.show();

            try {

                String nombreE= URLEncoder.encode(txtnombre.getText().toString(),"UTF-8");

                String url = "https://aplicacionrallygss.000webhostapp.com/ModificarEquipo.php?"+
                        "IDEquipo="+BuscarIDEquipo.getText().toString()+
                        "&UsuarioEquipo="+txtusuario.getText().toString()+
                        "&NombreEquipo="+nombreE+
                        "&PasswordEquipo="+txtcontrasena.getText().toString();

                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }else if (actividad==3){


            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this, AlertDialog.THEME_HOLO_DARK);
            progreso.setMessage("Eliminando...");
            progreso.show();

            try {

                String url = "https://aplicacionrallygss.000webhostapp.com/EliminarEquipo.php?IDEquipo="+BuscarIDEquipo.getText().toString();
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try
        }


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();

        txtnombre.setText("");
        txtcontrasena.setText("");
        txtusuario.setText("");
        BuscarIDEquipo.setText("");

        Toast.makeText(this, "No se pudo conectar verifique los datos " + error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        if (actividad == 1) {
            progreso.hide();

            try {

                TablaJuez tablaJuez = new TablaJuez();
                JSONArray json_array = response.optJSONArray("equipo");//rally es el identificador del json

                JSONObject jsonObject = null;

                jsonObject = json_array.getJSONObject(0);

                tablaJuez.setNombre(jsonObject.optString("NombreEquipo"));
                tablaJuez.setUsuario(jsonObject.optString("UsuarioEquipo"));
                tablaJuez.setContrasena(jsonObject.optString("PasswordEquipo"));

                BuscarIDEquipo.setText(idintent);
                txtnombre.setText(tablaJuez.getNombre());
                txtusuario.setText(tablaJuez.getUsuario());
                txtcontrasena.setText(tablaJuez.getContrasena());


            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if (actividad == 2) {  // limpa los botones y msj

            progreso.hide();

            txtnombre.setText("");
            txtcontrasena.setText("");
            txtusuario.setText("");
            BuscarIDEquipo.setText("");
            Toast.makeText(this, "Se modifico un equipo! ", Toast.LENGTH_SHORT).show();
        } else if (actividad == 3) {
            progreso.hide();
            try {

                JSONArray json_array = response.optJSONArray("j");//rally es el identificador del json
                JSONObject jsonObject = null;

                jsonObject = json_array.getJSONObject(0);
                int s =jsonObject.optInt("IDEquipo");
               if(s==0)
                {
                    Toast.makeText(this, "Existen miembros! ", Toast.LENGTH_SHORT).show();
                }
                else
                    {

                    txtnombre.setText("");
                    txtcontrasena.setText("");
                    txtusuario.setText("");
                    BuscarIDEquipo.setText("");
                    Toast.makeText(this, "Se elimino un equipo! ", Toast.LENGTH_SHORT).show();
                }

            }   catch (JSONException e) {
            e.printStackTrace();
        }



        }
    }

    public void onClickEliminarEquipo(View view) {

        if (BuscarIDEquipo.getText().toString().isEmpty()) {
            BuscarIDEquipo.setError("VACIO");
        } else {
            actividad = 3;
            ActualizarEquipo();
            Intent SiguienteActividad = new Intent(ClassModificarEquipo.this, ClassMenuEquipos.class);
            startActivity(SiguienteActividad);
        }
    }// fin onclick

    public void onClickModificarEquipo(View view) {

        if (BuscarIDEquipo.getText().toString().isEmpty() || txtcontrasena.getText().toString().isEmpty() ||
                txtusuario.getText().toString().isEmpty() || txtnombre.getText().toString().isEmpty()) {
            if (BuscarIDEquipo.getText().toString().isEmpty()) {
                BuscarIDEquipo.setError("VACIO");
            }
            if (txtcontrasena.getText().toString().isEmpty()) {
                txtcontrasena.setError("VACIO");
            }
            if (txtusuario.getText().toString().isEmpty()) {
                txtusuario.setError("VACIO");
            }

            if (txtnombre.getText().toString().isEmpty()) {
                txtnombre.setError("VACIO");
            }

        } else {

            actividad = 2;
            ActualizarEquipo();
            Intent SiguienteActividad = new Intent(ClassModificarEquipo.this, ClassMenuEquipos.class);
            startActivity(SiguienteActividad);

        }
    }
    public void onClickConsultarMiembro(View view) {

            String temp= BuscarIDEquipo.getText().toString();

        Intent intent = new Intent(ClassModificarEquipo.this, ClassConsultarMiembro.class);
        intent.putExtra("ID2",temp);
        startActivity(intent);

    }
}
