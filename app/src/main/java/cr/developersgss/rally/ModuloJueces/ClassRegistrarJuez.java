package cr.developersgss.rally.ModuloJueces;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.*;
import org.json.JSONObject;
import cr.developersgss.rally.R;


public class ClassRegistrarJuez extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    //variables de la interfaz
    Button btnRegistrarJuez;
    TextInputEditText txtusuario, txtnombre, txtcontrasena;
    ProgressDialog progreso;//ventana de progreso

    //conexion con el ws
    RequestQueue request;
    JsonObjectRequest jor;


    private AsyncHttpClient cliente;
    private Spinner SpinnerRally;



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


        request = Volley.newRequestQueue(this);


        progreso = new ProgressDialog(this);
        progreso.setMessage("Registrando...");
        progreso.show();

        try {
            String url = "https://aplicacionrallygss.000webhostapp.com/InsertarJuez.php?" +
                    "IDJuez=NULL&IDRally=1&IDPuntoControl=1&UsuarioJuez=" + txtusuario.getText().toString() +
                    "&NombreJuez=" + txtnombre.getText().toString() + "&ContrasenaJuez=" +
                    txtcontrasena.getText().toString() + "&Tipo=NULL";
            jor = new JsonObjectRequest(Request.Method.GET, url, null, this, this);//conecta con el url
            request.add(jor);
        } catch (Error e) {

        }
    }


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

        progreso.hide();

        Toast.makeText(this, "Se ingreso un nuevo juez! ", Toast.LENGTH_SHORT).show();

    }

    public void onClickRegistrarJueces(View view) {
        RegistrarJuez();
    }


}






