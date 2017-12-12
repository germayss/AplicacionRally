package cr.developersgss.rally.ModuloRallyUsuario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import cr.developersgss.rally.R;

public class ClassIniciarRally extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    ProgressDialog progreso;
    RequestQueue rq;
    JsonObjectRequest jor;
    int contador = 1, contadorPC2 = 0;
    String idequipo = "", idpuntocontrol = "", idrally = "", idruta = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_iniciar_rally);
        Bundle bundle = getIntent().getExtras();
        idequipo = bundle.getString("ID");
        idrally = bundle.getString("IDR");
    }

    public void onClickIniciarRally(View view) {
        cargarWS();
    }

    private void cargarWS(){

        rq = Volley.newRequestQueue(this);
        try{
            if (contador == 1){
                //En cual punto de control esta el equipo
                progreso=new ProgressDialog(this);
                progreso.setMessage("Consultando...");
                progreso.show();
                String url="https://aplicacionrallygss.000webhostapp.com/validarpuntocontrol.php?ID="+idequipo;
                jor=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                rq.add(jor);
            } else if (contador == 2){
                //Cual ruta esta activa por un administrador
                String url="https://aplicacionrallygss.000webhostapp.com/validarsecuenciainicial.php?ID="+idrally;
                jor=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                rq.add(jor);
                contadorPC2++;
            } else if (contador == 3){
                //Cual es el primer punto de control en la ruta
                String url="https://aplicacionrallygss.000webhostapp.com/validarsecuenciainicial2.php?ID="+idruta;
                jor=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                rq.add(jor);
            } else if (contador == 4){
                //El equipo ya salio del punto de control o sigue en pruebas, segun hora de salida
                String url="https://aplicacionrallygss.000webhostapp.com/validarsalidapunto.php?IDE="+idequipo+"&IDPC="+idpuntocontrol;
                jor=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                rq.add(jor);
            }
        }catch (Error e){}
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if (contador == 1){
                JSONArray array = response.getJSONArray("pcontrol");
                JSONObject j1= array.getJSONObject(0);
                //IDpuntoControl 1 default para equipos equipo al inicio del rally
                if (Integer.parseInt(j1.getString("IDPuntoControl")) == 1){
                    contador = 2;
                    cargarWS();
                } else {
                    //Captura IDPuntoControl
                    idpuntocontrol = j1.getString("IDPuntoControl");
                    contador = 4;
                    cargarWS();
                }
            } else if(contador == 2){
                JSONArray array = response.getJSONArray("ruta");
                JSONObject j2= array.getJSONObject(0);
                //Captura IDRuta
                idruta = j2.getString("IDRuta");
                contador = 3;
                cargarWS();
            } else if(contador == 3){
                JSONArray array = response.getJSONArray("ruta");
                JSONObject j3= array.getJSONObject(0);
                progreso.hide();
                //Hacia el punto seguir ruta desde el punto inicial del rally
                Intent SiguienteActividad = new Intent(ClassIniciarRally.this, ClassSeguirRuta.class);
                SiguienteActividad.putExtra("IDPC",j3.getString("IDPuntoControl"));
                SiguienteActividad.putExtra("IDE",idequipo);
                SiguienteActividad.putExtra("IDR",idrally);
                startActivity(SiguienteActividad);
            } else if(contador == 4){
                JSONArray array = response.getJSONArray("hora");
                JSONObject j4= array.getJSONObject(0);
                progreso.hide();
                //Hora 0 error al capturar datos
                if (j4.getString("HoraSalidaEquipoControl") == "0"){
                    Toast.makeText(this,"Error al optener informacion de ruta",Toast.LENGTH_SHORT).show();
                } else{
                    //hora salida null el equipo continua realizando pruebas, sin hora de salida
                    if (j4.getString("HoraSalidaEquipoControl") == "null" ) {
                        Intent SiguienteActividad = new Intent(ClassIniciarRally.this, ClassIniciarPruebas.class);
                        SiguienteActividad.putExtra("IDPC",idpuntocontrol);
                        SiguienteActividad.putExtra("IDE",idequipo);
                        SiguienteActividad.putExtra("IDR",idrally);
                        startActivity(SiguienteActividad);
                    } else {
                        //Hora salida existe, ya termino prueba y va a seguir ruta al siguiente punto control
                        Intent SiguienteActividad = new Intent(ClassIniciarRally.this, ClassSeguirRuta.class);
                        SiguienteActividad.putExtra("IDPC",idpuntocontrol);
                        SiguienteActividad.putExtra("IDE",idequipo);
                        SiguienteActividad.putExtra("IDR",idrally);
                        startActivity(SiguienteActividad);
                    }
                }
            }
        }catch (JSONException e){
            Toast.makeText(this,"Error al obtener datos de ruta del equipo",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this,"NO se pudo "+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }
}
