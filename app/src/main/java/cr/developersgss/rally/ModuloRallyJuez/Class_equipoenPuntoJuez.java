package cr.developersgss.rally.ModuloRallyJuez;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cr.developersgss.rally.R;

/**
 * Created by Afi on 23/11/2017.
 */

public class Class_equipoenPuntoJuez extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_seleccion_equipo_juez);
    }

    public void onClickquipoPuntoJuez(View view) {
        Intent SiguienteActividad = new Intent(Class_equipoenPuntoJuez.this, ClassRallyJuez.class);
        startActivity(SiguienteActividad);
    }
}
