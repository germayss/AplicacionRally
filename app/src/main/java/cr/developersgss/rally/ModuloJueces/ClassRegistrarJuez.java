package cr.developersgss.rally.ModuloJueces;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
import java.util.List;

import cr.developersgss.rally.Login.IDPunto;
import cr.developersgss.rally.Login.IDRally;
import cr.developersgss.rally.R;


public class ClassRegistrarJuez extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    //registrar Juez
    //variables de la interfaz
    Button btnRegistrarJuez;
    TextInputEditText txtusuario, txtnombre, txtcontrasena;
    ProgressDialog progreso;//ventana de progreso
    Spinner SpinnerRally,SpinnerPuntos;
    //conexion con el ws
    RequestQueue request;
    JsonObjectRequest jor;
    ArrayList lista_rallys;
    ArrayList lista_puntos;


    int llenarSpinner=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_registrar_juez);


       lista_rallys= new ArrayList<>();
        lista_puntos= new ArrayList<>();
        txtnombre = (TextInputEditText) findViewById(R.id.txtnombre);
        txtusuario = (TextInputEditText) findViewById(R.id.txtusuario);
        txtcontrasena = (TextInputEditText) findViewById(R.id.txtcontrasena);
        btnRegistrarJuez = (Button) findViewById(R.id.BtnRegistrarJuez);
        SpinnerRally= findViewById(R.id.SpinnerRally);
        SpinnerPuntos= findViewById(R.id.SpinnerPuntos);

        RegistrarJuez();




    }

    private void RegistrarJuez() {



        if (llenarSpinner==1){//carga el spinner del idrally
            request = Volley.newRequestQueue(this);

            try {
                String url = "https://aplicacionrallygss.000webhostapp.com/SelectIDRally.php";
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try

        }else if (llenarSpinner==2){//carga el spiiner del punto de control
            request = Volley.newRequestQueue(this);
            try {
                String url = "https://aplicacionrallygss.000webhostapp.com/SelectIDPunto.php";
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try

        }else {//registra un nuevo juez en la bd
            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this);
            progreso.setMessage("Registrando...");
            progreso.show();



            try {

                String SelePunto=SpinnerPuntos.getSelectedItem().toString();
                String SeleRally=SpinnerRally.getSelectedItem().toString();

                String url = "https://aplicacionrallygss.000webhostapp.com/InsertarJuez.php?" +
                        "IDJuez=NULL&IDRally="+SeleRally+"&IDPuntoControl="+SelePunto+"&UsuarioJuez=" + txtusuario.getText().toString() +
                        "&NombreJuez=" + txtnombre.getText().toString() + "&ContrasenaJuez=" +
                        txtcontrasena.getText().toString() + "&Tipo=NULL";
                jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
                request.add(jor);
            } catch (Error e) {

            }//termina try
        }

    }//termina RegistrarJuez


    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        txtnombre.setText("");
        txtcontrasena.setText("");
        txtusuario.setText("");
        Toast.makeText(this, "Error verifique los datos " + error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {

        if (llenarSpinner==1){// si el ws da respuesta carga el spinner


           IDRally idrally =null;

           JSONArray json_array =response.optJSONArray("rally");//rally es el identificador del json


            try {

                for (int i = 0; i < json_array.length(); i++) {
                    idrally =new IDRally();
                    JSONObject jsonObject= null;
                    jsonObject=json_array.getJSONObject(i);
                    idrally.setID(jsonObject.optInt("IDRally"));
                    lista_rallys.add(idrally.getID());

                }

                ArrayAdapter<IDRally> ids=new ArrayAdapter<IDRally>(this,android.R.layout.simple_list_item_1,lista_rallys);
                SpinnerRally.setAdapter(ids);
                llenarSpinner=2;

            }catch (JSONException e){
                e.printStackTrace();
            }

            RegistrarJuez();
        }else if (llenarSpinner==2){// si el ws da respuesta carga el spinner

            IDPunto idpunto =null;

            JSONArray json_array2 =response.optJSONArray("punto");//rally es el identificador del json


            try {

                for (int i = 0; i < json_array2.length(); i++) {
                    idpunto =new IDPunto();
                    JSONObject jsonObject2= null;
                    jsonObject2=json_array2.getJSONObject(i);
                    idpunto.setIDp(jsonObject2.optInt("IDPuntoControl"));
                    lista_puntos.add(idpunto.getIDp());

                }

                ArrayAdapter<IDPunto> ids2=new ArrayAdapter<IDPunto>(this,android.R.layout.simple_list_item_1,lista_puntos);
                SpinnerPuntos.setAdapter(ids2);
                llenarSpinner=3;

            }catch (JSONException e){
                e.printStackTrace();
            }



        }else
            {  // limpa los botones y msj de nuevo juez
            progreso.hide();
            txtnombre.setText("");
            txtcontrasena.setText("");
            txtusuario.setText("");
            Toast.makeText(this, "Se ingreso un nuevo juez! ", Toast.LENGTH_SHORT).show();
            llenarSpinner=3;


        }
    }


    public void onClickRegistrarJueces(View view) {

        if ( txtusuario.getText().toString().isEmpty() || txtnombre.getText().toString().isEmpty() || txtcontrasena.getText().toString().isEmpty()){
            if (txtcontrasena.getText().toString().isEmpty()){
                txtcontrasena.setError("VACIO");
            }
            if(txtnombre.getText().toString().isEmpty()){
                txtnombre.setError("VACIO");
            }
            if(txtusuario.getText().toString().isEmpty()) {
                txtusuario.setError("VACIO");

            }
        }else{
            RegistrarJuez();
        }



    }// fin onclick





}








