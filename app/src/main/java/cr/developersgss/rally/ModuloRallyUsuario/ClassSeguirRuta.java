package cr.developersgss.rally.ModuloRallyUsuario;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

import cr.developersgss.rally.R;

public class ClassSeguirRuta extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    String idequipo = "", idrally = "", idpuntocontrol = "2";
    TextView txtArea;
    RequestQueue rq;
    JsonObjectRequest jor;
    ProgressDialog progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_seguir_ruta);

        Bundle bundle = getIntent().getExtras();
        idequipo = bundle.getString("IDE");
        idrally = bundle.getString("IDR");
        idpuntocontrol = bundle.getString("IDPC");
        txtArea = (TextView) findViewById(R.id.txtPuntoControlSeguirRuta);
        cargar();
    }

    private  void  cargar(){
        progreso=new ProgressDialog(this, AlertDialog.THEME_HOLO_DARK);
        progreso.setMessage("Consultando...");
        progreso.show();
        String url="https://aplicacionrallygss.000webhostapp.com/datospuntocontrol.php?IDPC="+idpuntocontrol;
        jor=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq = Volley.newRequestQueue(this);
        rq.add(jor);

    }

    public void onClickLlgada(View view) {
        Intent SiguienteActividad = new Intent(ClassSeguirRuta.this, ClassIniciarPruebas.class);
        SiguienteActividad.putExtra("IDE",idequipo);
        SiguienteActividad.putExtra("IDR",idrally);
        startActivity(SiguienteActividad);
    }

    public void onClickPuntosExtra(View view) {
        Intent SiguienteActividad = new Intent(ClassSeguirRuta.this, ClassPuntosExtra.class);
        SiguienteActividad.putExtra("IDE",idequipo);
        SiguienteActividad.putExtra("IDR",idrally);
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
        try {
            JSONArray array = response.getJSONArray("Pcontrol");
            JSONObject j= array.getJSONObject(0);
            progreso.hide();
            txtArea.setText("Area "+j.getString("AreaPuntoControl")+", ruta a area "+(String.valueOf(Integer.parseInt(j.getString("SecuenciaPuntoControl"))+1)));
        } catch (JSONException e){

        }
    }
}
