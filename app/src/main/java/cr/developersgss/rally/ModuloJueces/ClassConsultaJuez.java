package cr.developersgss.rally.ModuloJueces;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cr.developersgss.rally.Objetos.TablaJuez;
import cr.developersgss.rally.R;

public class ClassConsultaJuez extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adap;

    private List<TablaJuez> listajueces;

    private static final  String URL="https://aplicacionrallygss.000webhostapp.com/ConsultarJuez.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_consulta_juez);

     recyclerView= findViewById(R.id.recyclerView);
     recyclerView.setHasFixedSize(true);
     recyclerView.setLayoutManager(new LinearLayoutManager(this));

     listajueces= new ArrayList<>();

     cargarws();

    }



    private void cargarws(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.hide();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array= jsonObject.getJSONArray("jueces");
                    for (int i=0; i<array.length(); i++){
                        JSONObject o= array.getJSONObject(i);
                        TablaJuez item= new TablaJuez(
                                o.getString("IDJuez"),
                                o.getString("IDRally"),
                                o.getString("IDPuntoControl"),
                                o.getString("UsuarioJuez"),
                                o.getString("NombreJuez"),
                                o.getString("ContrasenaJuez"),
                                o.getString("Tipo")

                                );
                        listajueces.add(item);
                        adap=new AdaptadorJuez(listajueces,getApplicationContext());
                        recyclerView.setAdapter(adap);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "No se puede conectar " + error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("ERROR", error.toString());
            }
        }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

}
