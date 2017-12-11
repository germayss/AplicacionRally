package cr.developersgss.rally.ModuloRuta;

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

import java.util.ArrayList;

import cr.developersgss.rally.ModuloAdministrador.ClassMenuPrincipal;
import cr.developersgss.rally.Objetos.IDRally;
import cr.developersgss.rally.R;

/**
 * Created by germa on 24/11/2017.
 */

public class ClassRegistroRuta extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    RequestQueue request;
    JsonObjectRequest jor;
    Spinner SpinnerRally;
    ArrayList lista_rallys;
    TextInputEditText RegistrarNombreRuta,RegistrarFechaRuta,RegistrarHoraRuta;
    Button BtnRegistrarRuta;
    ProgressDialog progreso;//ventana de progreso
    int llenarSpinner=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_registroruta);

         RegistrarNombreRuta= (TextInputEditText) findViewById(R.id.RegistrarNombreRuta);
        RegistrarFechaRuta = (TextInputEditText) findViewById(R.id.RegistrarFechaRuta);
        RegistrarHoraRuta = (TextInputEditText) findViewById(R.id.RegistrarHoraRuta);
        BtnRegistrarRuta= (Button) findViewById(R.id.BtnRegistrarRuta);
        lista_rallys= new ArrayList<>();
        SpinnerRally= findViewById(R.id.SpinnerRally);

        RegistrarRuta();

    }

    private void RegistrarRuta() {

       if (llenarSpinner==1) {//carga el spinner del idrally
           request = Volley.newRequestQueue(this);

           try {
               String url = "https://aplicacionrallygss.000webhostapp.com/SelectIDRally.php";
               jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
               request.add(jor);
           } catch (Error e) {

           }//termina try

       }else{

           request = Volley.newRequestQueue(this);
           progreso = new ProgressDialog(this);
           progreso.setMessage("Registrando...");
           progreso.show();

           try {


               String SeleRally=SpinnerRally.getSelectedItem().toString();

               String url = "https://aplicacionrallygss.000webhostapp.com/InsertarRuta.php?"+
                       "IDRally="+SeleRally+
                       "&NombreRuta="+RegistrarNombreRuta.getText().toString()+
                       "&FechaInicioRuta="+RegistrarFechaRuta.getText().toString()+
                       "&HoraInicioRuta="+RegistrarHoraRuta.getText().toString();
               jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
               request.add(jor);
           } catch (Error e) {

           }//termina try

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

        if (llenarSpinner == 1) {// si el ws da respuesta carga el spinner

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
        }else{
            {  // limpa los botones y msj
                progreso.hide();
                RegistrarFechaRuta.setText("");
                RegistrarNombreRuta.setText("");
                RegistrarHoraRuta.setText("");
                Toast.makeText(this, "Se ingreso una nueva ruta ", Toast.LENGTH_SHORT).show();

            }
        }
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
            llenarSpinner=2;
           RegistrarRuta();
            Intent SiguienteActividad = new Intent(ClassRegistroRuta.this, ClassPuntosDeControlRuta.class);
            startActivity(SiguienteActividad);
        }



    }// fin onclick

}
