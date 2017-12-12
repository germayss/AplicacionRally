package cr.developersgss.rally.ModuloRuta;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import java.util.ArrayList;

import cr.developersgss.rally.Login.Globales;
import cr.developersgss.rally.ModuloAdministrador.ClassMenuPrincipal;
import cr.developersgss.rally.Objetos.IDRally;
import cr.developersgss.rally.R;

/**
 * Created by germa on 24/11/2017.
 */

public class ClassRegistroRuta extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    RequestQueue request;
    JsonObjectRequest jor;
    ArrayList lista_rallys;
    TextInputEditText RegistrarNombreRuta,RegistrarFechaRuta,RegistrarHoraRuta;
    Button BtnRegistrarRuta;
    ProgressDialog progreso;//ventana de progreso


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_registroruta);

         RegistrarNombreRuta= (TextInputEditText) findViewById(R.id.RegistrarNombreRuta);
        RegistrarFechaRuta = (TextInputEditText) findViewById(R.id.RegistrarFechaRuta);
        RegistrarHoraRuta = (TextInputEditText) findViewById(R.id.RegistrarHoraRuta);
        BtnRegistrarRuta= (Button) findViewById(R.id.BtnRegistrarRuta);
        lista_rallys= new ArrayList<>();




    }

    private void RegistrarRuta() {

        Globales globales = (Globales)getApplication();
        String idrally=globales.getIDRallyActual();

           request = Volley.newRequestQueue(this);
           progreso = new ProgressDialog(this, AlertDialog.THEME_HOLO_DARK);
           progreso.setMessage("Registrando...");
           progreso.show();

           try {


               String nombreruta = URLEncoder.encode(RegistrarNombreRuta.getText().toString(),"UTF-8");

               String url = "https://aplicacionrallygss.000webhostapp.com/InsertarRuta.php?"+
                       "IDRally="+idrally+
                       "&NombreRuta="+nombreruta+
                       "&FechaInicioRuta="+RegistrarFechaRuta.getText().toString()+
                       "&HoraInicioRuta="+RegistrarHoraRuta.getText().toString();
               jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
               request.add(jor);
           } catch (Error e) {

           }//termina try
           catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        RegistrarFechaRuta.setText("");
        RegistrarNombreRuta.setText("");
        RegistrarHoraRuta.setText("");
        Toast.makeText(this, "Verifique los datos:  " + error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {



                progreso.hide();
                RegistrarFechaRuta.setText("");
                RegistrarNombreRuta.setText("");
                RegistrarHoraRuta.setText("");
                Toast.makeText(this, "Se ingreso una nueva ruta ", Toast.LENGTH_SHORT).show();



    }
    public void onClickRegistrarRuta(View view) {

        if (  RegistrarFechaRuta.getText().toString().isEmpty() || RegistrarHoraRuta.getText().toString().isEmpty() || RegistrarNombreRuta.getText().toString().isEmpty()){
            if (RegistrarNombreRuta.getText().toString().isEmpty()){
                RegistrarNombreRuta.setError("VACIO");
            }
            if(RegistrarHoraRuta.getText().toString().isEmpty()){
                RegistrarHoraRuta.setError("VACIO");
            }
            if( RegistrarFechaRuta.getText().toString().isEmpty()) {
                RegistrarFechaRuta.setError("VACIO");

            }
        }else{
           RegistrarRuta();
           // Intent SiguienteActividad = new Intent(ClassRegistroRuta.this, ClassPuntosDeControlRuta.class);
           // startActivity(SiguienteActividad);
        }



    }// fin onclick

}
