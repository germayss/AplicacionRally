package cr.developersgss.rally.ModuloJueces;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
<<<<<<< HEAD
import android.widget.*;
=======
>>>>>>> ae709ea3ddba6c29d9dee12179a740bf8a6b158c
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

import cr.developersgss.rally.Login.Globales;
import cr.developersgss.rally.R;


public class ClassRegistrarJuez extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    //registrar Juez
    //variables de la interfaz
    Button btnRegistrarJuez;
    TextInputEditText txtusuario, txtnombre, txtcontrasena;
    ProgressDialog progreso;//ventana de progreso
    //conexion con el ws
    RequestQueue request;
    JsonObjectRequest jor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_registrar_juez);



        txtnombre = (TextInputEditText) findViewById(R.id.txtnombre);
        txtusuario = (TextInputEditText) findViewById(R.id.txtusuario);
        txtcontrasena = (TextInputEditText) findViewById(R.id.txtcontrasena);
        btnRegistrarJuez = (Button) findViewById(R.id.BtnRegistrarJuez);





    }

    private void RegistrarJuez() {


             {//registra un nuevo juez en la bd
            request = Volley.newRequestQueue(this);
            progreso = new ProgressDialog(this, AlertDialog.THEME_HOLO_DARK);
            progreso.setMessage("Registrando...");
            progreso.show();

            try {
                Globales globales = (Globales)getApplication();
                String idrally=globales.getIDRallyActual();

                Toast.makeText(this, "id"+idrally, Toast.LENGTH_SHORT).show();

                String url = "https://aplicacionrallygss.000webhostapp.com/InsertarJuez.php?" +
                        "IDJuez=NULL&IDRally="+idrally+"&IDPuntoControl=1&UsuarioJuez=" + txtusuario.getText().toString() +
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
        Toast.makeText(this, "No se puede conectar " + error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {


          // limpa los botones y msj de nuevo juez
            progreso.hide();
            txtnombre.setText("");
            txtcontrasena.setText("");
            txtusuario.setText("");
            Toast.makeText(this, "Se ingreso un nuevo juez! ", Toast.LENGTH_SHORT).show();


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








