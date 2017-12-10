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

import java.util.ArrayList;

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
    ArrayList lista_rallys;
    Spinner SpinnerRally;

    private int actividad =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_registroequipo);



        nombreEquipo = (TextInputEditText) findViewById(R.id.txtRegistrarNombreEquipo);
        contrasenaEquipo= (TextInputEditText) findViewById(R.id.txtRegistrarPassEquipo);
        usuarioEquipo = (TextInputEditText) findViewById(R.id.txtRegistrarUsuarioEquipo);
        btnRegistrarEquipo = (Button) findViewById(R.id.BtnRegistrarEquipo);
        SpinnerRally= findViewById(R.id.SpinnerRally);

        RegistrarEquipo();


    }

    private void RegistrarEquipo() {

        if (actividad == 1) {//carga el spinner del idrally
            request = Volley.newRequestQueue(this);

            try {
                String url = "https://aplicacionrallygss.000webhostapp.com/SelectIDRally.php";
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try

        } else {
            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this);
            progreso.setMessage("Registrando...");
            progreso.show();

            String SeleRally=SpinnerRally.getSelectedItem().toString();

            try {

                String url = "https://aplicacionrallygss.000webhostapp.com/InsertarEquipo.php?"
                        +"IDRally="+SeleRally+
                        "&NombreEquipo="+nombreEquipo.getText().toString()+
                        "&UsuarioEquipo="+usuarioEquipo.getText().toString()+
                        "&PasswordEquipo="+contrasenaEquipo.getText().toString();
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try
        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {

        progreso.hide();
        /*
        nombreEquipo.setText("");
        contrasenaEquipo.setText("");
        usuarioEquipo.setText("");*/

        Toast.makeText(this, "Verifique los datos: " + error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        if (actividad==1) {// si el ws da respuesta carga el spinner


            IDRally idrally = null;

            JSONArray json_array = response.optJSONArray("rally");//rally es el identificador del json


            try {

                for (int i = 0; i < json_array.length(); i++) {
                    idrally = new IDRally();
                    JSONObject jsonObject = null;
                    jsonObject = json_array.getJSONObject(i);
                    idrally.setID(jsonObject.optInt("IDRally"));
                    lista_rallys.add(idrally.getID());

                }

                ArrayAdapter<IDRally> ids = new ArrayAdapter<IDRally>(this, android.R.layout.simple_list_item_1, lista_rallys);
                SpinnerRally.setAdapter(ids);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else if (actividad==2){

            progreso.hide();
               /*
        nombreEquipo.setText("");
        contrasenaEquipo.setText("");
        usuarioEquipo.setText("");*/

            Toast.makeText(this, "Se ingreso un nuevo Equipo! ", Toast.LENGTH_SHORT).show();

        }else{

            progreso.hide();


            Toast.makeText(this, "Se ingreso un nuevo miembro! ", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickRegistrarJueces(View view) {
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

}
