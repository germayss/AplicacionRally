package cr.developersgss.rally.ModuloEquipos;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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


import cr.developersgss.rally.Objetos.TablaEquipos;

import cr.developersgss.rally.R;

/**
 * Created by germa on 24/11/2017.
 */

public class ClassConsultarEquipo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adap;
    private List<TablaEquipos> listaequipos;

    private static final  String URL="https://aplicacionrallygss.000webhostapp.com/ConsultarEquipo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_consultaequipo);

        recyclerView= findViewById(R.id.recyclerViewE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaequipos= new ArrayList<>();


        cargarws();

    }
    private void cargarws() {
        final ProgressDialog progressDialog = new ProgressDialog(this, AlertDialog.THEME_HOLO_DARK);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.hide();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array= jsonObject.getJSONArray("equipos");
                    for (int i=0; i<array.length(); i++){
                        JSONObject o= array.getJSONObject(i);
                        TablaEquipos item= new TablaEquipos(
                                o.getString("IDRally"),
                                o.getString("IDEquipo"),
                                o.getString("UsuarioEquipo"),
                                o.getString("NombreEquipo"),
                                o.getString("PasswordEquipo")
                        );

                        listaequipos.add(item);
                        AdaptadorEquipo adap =new AdaptadorEquipo(listaequipos,getApplicationContext());
                        adap.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                String temp=listaequipos.get(recyclerView.getChildAdapterPosition(view)).getIDEquipo();

                                Intent intent = new Intent(ClassConsultarEquipo.this, ClassModificarEquipo.class);
                                intent.putExtra("ID",temp);
                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(adap);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), "No se puede conectar " + error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("ERROR", error.toString());
            }
        }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    }
