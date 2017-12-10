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
    private String[][] lc = new String[100][4];
    int contador = 1;
    int contadorPC = 0;
    int contadorPC2 = 0;
    int finalpuntoControl = 0;
    int sec = 99999;
    String idequipo = "";
    String idrally = "";

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
                progreso=new ProgressDialog(this);
                progreso.setMessage("Consultando...");
                progreso.show();
                String url="https://aplicacionrallygss.000webhostapp.com/validarpuntocontrol.php?ID="+idequipo;
                jor=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                rq.add(jor);
            } else if (contador == 2){
                String url="https://aplicacionrallygss.000webhostapp.com/validarsecuencia.php?ID="+lc[contadorPC2][0];
                jor=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                rq.add(jor);
                contadorPC2++;
            } else if (contador == 3){
                String url="https://aplicacionrallygss.000webhostapp.com/validarsecuenciainicial.php?ID="+idrally;
                jor=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                rq.add(jor);
            } else if (contador == 4){
                String url="https://aplicacionrallygss.000webhostapp.com/validarsecuenciainicial2.php?ID="+lc[0][3];
                jor=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                rq.add(jor);
            }
        }catch (Error e){}
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if (contador == 1){
                JSONArray array = response.getJSONArray("control");
                JSONObject j= array.getJSONObject(0);
                if (Integer.parseInt(j.getString("IDPuntoControl")) == 1){
                    contador = 3;
                    cargarWS();
                } else {
                    for (int i=0; i<array.length(); i++){
                        JSONObject j2= array.getJSONObject(i);
                        lc[i][0] = j2.getString("IDPuntoControl");
                        lc[i][1] = j2.getString("HoraLlegadaEquipoControl");
                        lc[i][2] = j2.getString("HoraSalidaEquipoControl");
                        contadorPC++;
                    }
                    contador = 2;
                    cargarWS();
                }
            } else if(contador == 2){
                if (contadorPC > contadorPC2){
                    JSONArray array = response.getJSONArray("punto");
                    JSONObject j= array.getJSONObject(0);
                    lc[(contadorPC2-1)][3] = j.getString("SecuenciaPuntoControl");
                    cargarWS();
                } else {
                    for (int i = 0; i<contadorPC2; i++){
                        if(sec > Integer.parseInt(lc[i][3])){
                            sec = Integer.parseInt(lc[i][3]);
                            finalpuntoControl = Integer.parseInt(lc[i][0]);
                        }
                    }
                }
            } else if(contador == 3){
                //progreso.hide();
                JSONArray array = response.getJSONArray("ruta");
                JSONObject j= array.getJSONObject(0);
                lc[0][3] = j.getString("IDRuta");
                contador = 4;
                cargarWS();
            } else if(contador == 4){
                JSONArray array = response.getJSONArray("ruta");
                JSONObject j= array.getJSONObject(0);
                Intent SiguienteActividad = new Intent(ClassIniciarRally.this, ClassSeguirRuta.class);
                SiguienteActividad.putExtra("ID",j.getString("IDPuntoControl"));
                SiguienteActividad.putExtra("IDR",idrally);
                startActivity(SiguienteActividad);
            }
        }catch (JSONException e){}
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this,"NO se pudo "+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }
}
