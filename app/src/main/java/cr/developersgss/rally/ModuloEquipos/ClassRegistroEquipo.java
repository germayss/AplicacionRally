package cr.developersgss.rally.ModuloEquipos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import cr.developersgss.rally.Objetos.IDRally;
import cr.developersgss.rally.R;

/**
 * Created by germa on 24/11/2017.
 */

public class ClassRegistroEquipo extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {


    RequestQueue request;
    JsonObjectRequest jor;
    ProgressDialog progreso;//ventana de progreso
    Button btnRegistrarEquipo,btnRegistrarMiembro;
    TextInputEditText nombreEquipo,contrasenaEquipo,usuarioEquipo,miembroEquipo;
    CheckBox lider;
    String idequipo;

    private int actividad =1;
    private Boolean AsignoLider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_registroequipo);



        nombreEquipo = (TextInputEditText) findViewById(R.id.txtRegistrarNombreEquipo);
        contrasenaEquipo= (TextInputEditText) findViewById(R.id.txtRegistrarPassEquipo);
        usuarioEquipo = (TextInputEditText) findViewById(R.id.txtRegistrarUsuarioEquipo);
        miembroEquipo = (TextInputEditText) findViewById(R.id.RegistrarMiembroEquipo);
        btnRegistrarEquipo = (Button) findViewById(R.id.BtnRegistrarEquipo);
        btnRegistrarMiembro= (Button) findViewById(R.id.BtnRegistrarMiembroEquipo);
        lider= findViewById(R.id.CheckLider);

 btnRegistrarMiembro.setEnabled(false);


    }

    private void RegistrarEquipo() {

        Globales globales = (Globales)getApplication();
        String idrally=globales.getIDRallyActual();
        if (actividad==1){

            AsignoLider=false;
            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this);
            progreso.setMessage("Registrando...");
            progreso.show();

            try {
                String nombrequipo = URLEncoder.encode(nombreEquipo.getText().toString(),"UTF-8");
                String url = "https://aplicacionrallygss.000webhostapp.com/InsertarEquipo.php?"
                        +"IDRally="+idrally+
                        "&NombreEquipo="+nombrequipo+
                        "&UsuarioEquipo="+usuarioEquipo.getText().toString()+
                        "&PasswordEquipo="+contrasenaEquipo.getText().toString();
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{

            try {

               int estadoLider;
                request = Volley.newRequestQueue(this);
                progreso = new ProgressDialog(this);
                progreso.setMessage("Registrando...");
                progreso.show();


                if (AsignoLider){

                    estadoLider=2;

                }else{

                    if (lider.isChecked()){
                        estadoLider=1;
                        AsignoLider=true;
                    }else {
                        estadoLider=2;
                    }
                }
                String miembro = URLEncoder.encode(miembroEquipo.getText().toString(),"UTF-8");


                String url = "https://aplicacionrallygss.000webhostapp.com/InsertarPersonaEquipo.php?"+
                        "IDRally="+idrally.toString()+
                        "&IDEquipo="+idequipo.toString()+
                        "&NombrePersonaEquipo="+miembro+
                        "&LiderPersonaEquipo="+estadoLider;
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
        } catch (Error e) {

            }//termina try
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        progreso.hide();
            if (actividad==1){

                nombreEquipo.setText("");
                contrasenaEquipo.setText("");
                usuarioEquipo.setText("");

                Toast.makeText(this, "Verifique los datos: " + error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("ERROR", error.toString());
            }else{
                miembroEquipo.setText("");
                Toast.makeText(this, "Verifique los datos: " + error.toString(), Toast.LENGTH_SHORT).show();
               // Log.i("ERROR", error.toString());
            }

    }

    @Override
    public void onResponse(JSONObject response) {

        if (actividad==1) {// si el ws da respuesta carga el spinner
            progreso.hide();

        nombreEquipo.setText("");
        contrasenaEquipo.setText("");
        usuarioEquipo.setText("");

            try {

                JSONArray array = response.getJSONArray("equipo");
                JSONObject  jsonObject = array.getJSONObject(0);
                idequipo= jsonObject.optString("IDEquipo");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(this, "Se ingreso un nuevo equipo! ", Toast.LENGTH_SHORT).show();
                btnRegistrarMiembro.setEnabled(true);
        }else{

            progreso.hide();
            miembroEquipo.setText("");



            Toast.makeText(this, "Se ingreso un nuevo miembro! ", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickRegistrarEquipo(View view) {
        if (nombreEquipo.getText().toString().isEmpty() || contrasenaEquipo.getText().toString().isEmpty() ||
                usuarioEquipo.getText().toString().isEmpty()){

            if (nombreEquipo.getText().toString().isEmpty()){
                nombreEquipo.setError("VACIO");
            }
            if (contrasenaEquipo.getText().toString().isEmpty()){

                contrasenaEquipo.setError("VACIO");
            }
            if (usuarioEquipo.getText().toString().isEmpty()){

                usuarioEquipo.setError("VACIO");
            }
        }else {
            actividad=1;
            RegistrarEquipo();
        }
    }
    public void onClickRegistrarMiembro(View view) {
        if(miembroEquipo.getText().toString().isEmpty()){
            miembroEquipo.setError("VACIO");
         }else  {

            actividad=2;
            RegistrarEquipo();
        }

        }


}
