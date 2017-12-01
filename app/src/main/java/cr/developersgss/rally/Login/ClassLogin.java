package cr.developersgss.rally.Login;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.os.AsyncTask;
import java.text.NumberFormat;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import org.w3c.dom.Text;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;


import cr.developersgss.rally.ModuloAdministrador.ClassMenuPrincipal;
import cr.developersgss.rally.R;

import static com.android.volley.toolbox.Volley.newRequestQueue;
import static cr.developersgss.rally.R.id.txtLoginUsuario;

public class ClassLogin extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    ProgressDialog progreso;
    RequestQueue rq;
    JsonObjectRequest jor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_login);
    }

    private void cargarWS(){
        rq = Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();
        try{
            String url="https://aplicacionrallygss.000webhostapp.com/login.php";
            jor=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
            rq.add(jor);
        }catch (Error e){

        }
    }

    public void onClickContraseña(View view) {
        Intent SiguienteActividad = new Intent(ClassLogin.this,ClassCambioContrasena.class);
        startActivity(SiguienteActividad);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this,"NO se pudo "+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast.makeText(this,"Mensaje: "+response,Toast.LENGTH_SHORT).show();

        Intent SiguienteActividad = new Intent(ClassLogin.this, ClassMenuPrincipal.class);
        startActivity(SiguienteActividad);
    }

    public void pp(View view) {
        cargarWS();
    }
}
