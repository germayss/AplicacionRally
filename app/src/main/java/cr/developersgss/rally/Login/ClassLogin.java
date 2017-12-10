package cr.developersgss.rally.Login;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import cr.developersgss.rally.ModuloAdministrador.ClassMenuPrincipal;
import cr.developersgss.rally.ModuloRallyJuez.ClassPuntodeControl;
import cr.developersgss.rally.ModuloRallyUsuario.ClassIniciarRally;
import cr.developersgss.rally.R;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class ClassLogin extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    ProgressDialog progreso;
    RequestQueue rq;
    JsonObjectRequest jor;
    TextInputEditText txtusuario, txtpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_login);

        txtusuario = findViewById(R.id.txtLoginUsuario);
        txtpass = findViewById(R.id.txtLoginPass);
    }

    //Conexion con el web services y obtencion de datos
    private void cargarWS(){
        rq = Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this, AlertDialog.THEME_HOLO_DARK);
        progreso.setMessage("Consultando...");
        progreso.show();
        try{
            String url="https://aplicacionrallygss.000webhostapp.com/loginRally.php?Usuario="+txtusuario.getText().toString()+"&Pass="+txtpass.getText().toString();
            jor=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
            rq.add(jor);
        }catch (Error e){}
    }

    public void onClickContraseña(View view) {
        Intent SiguienteActividad = new Intent(ClassLogin.this,ClassCambioContrasena.class);
        startActivity(SiguienteActividad);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "No se puede conectar " + error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }

    //response equivale a los datos obtenidos en la conexion
    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        try {
            JSONArray array = response.getJSONArray("login");
            JSONObject jsonObject = array.getJSONObject(0);

            // guarda en variables globales
           VariablesGlobales global= new VariablesGlobales();
            global.setIDRallyActual( jsonObject.getString("IDRally"));
            Toast.makeText(this, "el id rally es "+global.getIDRallyActual() , Toast.LENGTH_SHORT).show();

            if (Integer.parseInt(jsonObject.getString("Tipo")) == 3){
                Intent SiguienteActividad = new Intent(ClassLogin.this, ClassIniciarRally.class);
                startActivity(SiguienteActividad);
            } else if (Integer.parseInt(jsonObject.getString("Tipo")) == 2){
                Intent SiguienteActividad = new Intent(ClassLogin.this, ClassPuntodeControl.class);
                startActivity(SiguienteActividad);
            } else if (Integer.parseInt(jsonObject.getString("Tipo")) == 1){
                Intent SiguienteActividad = new Intent(ClassLogin.this, ClassMenuPrincipal.class);
                startActivity(SiguienteActividad);
            }

        }catch (JSONException e){}
    }

    public void pp(View view) {

        if (txtpass.getText().toString().isEmpty() || txtusuario.getText().toString().isEmpty()) {
            if (txtusuario.getText().toString().isEmpty()) {
                txtusuario.setError("VACIO");
            }
            if (txtpass.getText().toString().isEmpty()){
                txtpass.setError("VACIO");
            }
        }else  {
        cargarWS();
    }
}
}
