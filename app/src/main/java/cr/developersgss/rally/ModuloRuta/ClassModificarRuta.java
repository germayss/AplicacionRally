package cr.developersgss.rally.ModuloRuta;

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

import cr.developersgss.rally.ModuloJueces.ClassConsultaJuez;
import cr.developersgss.rally.ModuloJueces.ClassModificarJuez;
import cr.developersgss.rally.Objetos.TablaRuta;
import cr.developersgss.rally.R;

/**
 * Created by germa on 24/11/2017.
 */

public class ClassModificarRuta extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    TextInputEditText txtnombreruta, txtfecha, txthora;
    TextView BuscarIDRuta;
    ProgressDialog progreso;//ventana de progreso
    //conexion con el ws
    RequestQueue request;
    JsonObjectRequest jor;
    String idintent;


    int actividad=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_modificarruta);



        txtnombreruta = (TextInputEditText) findViewById(R.id.txtnombreruta);
        txtfecha = (TextInputEditText) findViewById(R.id.txtfecha);
        txthora = (TextInputEditText) findViewById(R.id.txthora);
        BuscarIDRuta =  findViewById(R.id.BuscarIDRuta);


        idintent = getIntent().getStringExtra("ID");

        ActualizarRuta();
    }
    private void ActualizarRuta() {

        if (actividad==1) {
            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this, AlertDialog.THEME_HOLO_DARK);
            progreso.setMessage("Buscando...");
            progreso.show();

            try {
                String url = "https://aplicacionrallygss.000webhostapp.com/BuscarIDRuta.php?IDRuta="+idintent;
                //  BuscarIDJuez.getText().toString();
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try

        }
        else if (actividad==2){

            //modificar juez en la bd
            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this, AlertDialog.THEME_HOLO_DARK);
            progreso.setMessage("Modificando...");
            progreso.show();

            try {



                String url = "https://aplicacionrallygss.000webhostapp.com/ModificarRuta.php?"+
                        "IDRuta="+BuscarIDRuta.getText().toString()+
                        "&NombreRuta=" + txtnombreruta.getText().toString()+
                        "&FechaInicioRuta="+ txtfecha.getText().toString()+
                        "&HoraInicioRuta="+ txthora.getText().toString();

                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try

        }else if (actividad==3){


            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this, AlertDialog.THEME_HOLO_DARK);
            progreso.setMessage("Eliminando...");
            progreso.show();

            try {

                String url = "https://aplicacionrallygss.000webhostapp.com/EliminarRuta.php?IDRuta="+BuscarIDRuta.getText().toString();
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try
        }

    }//termina modificar juez
    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();

        txtnombreruta.setText("");
        txtfecha.setText("");
        txthora.setText("");
        BuscarIDRuta.setText("");

        Toast.makeText(this, "No se pudo conectar verifique los datos " + error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        if (actividad == 1) {
            progreso.hide();

            try {

                TablaRuta tablaRuta= new TablaRuta();
                JSONArray json_array =response.optJSONArray("ruta");//rally es el identificador del json

                JSONObject jsonObject= null;

                jsonObject=json_array.getJSONObject(0);

                tablaRuta.setNombreRuta(jsonObject.optString("NombreRuta"));
                tablaRuta.setFechaInicioRuta(jsonObject.optString("FechaInicioRuta"));
                tablaRuta.setHoraInicioRuta(jsonObject.optString("HoraInicioRuta"));

                BuscarIDRuta.setText(idintent);
                txtnombreruta.setText(tablaRuta.getNombreRuta());
                txtfecha.setText(tablaRuta.getFechaInicioRuta());
                txthora.setText(tablaRuta.getHoraInicioRuta());


            }catch (JSONException e){
                e.printStackTrace();
            }


        }
        else if(actividad==2) {  // limpa los botones y msj

            progreso.hide();

            txtnombreruta.setText("");
            txtfecha.setText("");
            txthora.setText("");
            BuscarIDRuta.setText("");
            Toast.makeText(this, "Se modifico una ruta! ", Toast.LENGTH_SHORT).show();
        }
        else if (actividad==3){
            progreso.hide();

            txtnombreruta.setText("");
            txtfecha.setText("");
            txthora.setText("");
            BuscarIDRuta.setText("");
            Toast.makeText(this, "Se elimino una ruta! ", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickEliminarR(View view) {

        if(BuscarIDRuta.getText().toString().isEmpty()) {
            BuscarIDRuta.setError("VACIO");
        }else
        {
            actividad=3;
            ActualizarRuta();
            Intent SiguienteActividad = new Intent(ClassModificarRuta.this, ClassMenuRuta.class);
            startActivity(SiguienteActividad);
        }
    }// fin onclick

    public void onClickModificarR(View view) {

        if (BuscarIDRuta.getText().toString().isEmpty()||txthora.getText().toString().isEmpty()||
                txtfecha.getText().toString().isEmpty()||txtnombreruta.getText().toString().isEmpty())
        {
            if(BuscarIDRuta.getText().toString().isEmpty()) {
                BuscarIDRuta.setError("VACIO");
            }
            if (txthora.getText().toString().isEmpty()){
                txthora.setError("VACIO");
            }
            if (txtfecha.getText().toString().isEmpty()){
                txtfecha.setError("VACIO");
            }

            if (txtnombreruta.getText().toString().isEmpty()){
                txtnombreruta.setError("VACIO");
            }

        }else {

            actividad=2;
            ActualizarRuta();
            Intent SiguienteActividad = new Intent(ClassModificarRuta.this, ClassMenuRuta.class);
            startActivity(SiguienteActividad);

        }

    }// fin onclick
}
