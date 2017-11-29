package cr.developersgss.rally.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Response;

import org.json.JSONObject;

import cr.developersgss.rally.ModuloAdministrador.ClassMenuPrincipal;
import cr.developersgss.rally.R;

public class ClassLoginInterface extends AppCompatActivity implements com.android.volley.Response.Listener<JSONObject>, com.android.volley.Response.ErrorListener {

    TextInputLayout txtLoginUsuario, txtLoginPass;
    Button btnIngresar;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonrequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_login);

        txtLoginUsuario = findViewById(R.id.txtLoginUsuario);
        txtLoginPass = findViewById(R.id.txtLoginPass);
        btnIngresar = findViewById(R.id.btnIngresar);

        request = Volley.newRequestQueue(getApplicationContext());
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });
    }

    private void cargarWebService(){
        progreso=new ProgressDialog(getApplicationContext());
        progreso.setMessage("Consultando...");
        progreso.show();
        String url = "https://aplicacionrallygss.000webhostapp.com/login.php";
        jsonrequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonrequest);
    }

    public void onClickLogin(View view) {
        Intent SiguienteActividad = new Intent(ClassLoginInterface.this, ClassMenuPrincipal.class);
        startActivity(SiguienteActividad);
    }

    public void onClickContraseña(View view) {
        Intent SiguienteActividad = new Intent(ClassLoginInterface.this, ClassCambioContrasena.class);
        startActivity(SiguienteActividad);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getApplicationContext(),"No se pudo consultar " +error.toString(),Toast.LENGTH_SHORT).show();
        //Log.i("Error",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast.makeText(getApplicationContext(), "Mensaje: "+response,Toast.LENGTH_SHORT).show();
    }
}
