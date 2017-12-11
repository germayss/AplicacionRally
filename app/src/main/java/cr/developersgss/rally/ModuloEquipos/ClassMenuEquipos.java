package cr.developersgss.rally.ModuloEquipos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cr.developersgss.rally.ModuloAdministrador.ClassMenuPrincipal;
import cr.developersgss.rally.R;

/**
 * Created by germa on 21/11/2017.
 */

public class ClassMenuEquipos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_menuequipos);
    }

    public void onClickRegistrarEquipo(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuEquipos.this, ClassRegistroEquipo.class);
        startActivity(SiguienteActividad);
    }

    public void onClickConsultarEquipo(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuEquipos.this, ClassConsultarEquipo.class);
        startActivity(SiguienteActividad);
    }
    public void onClickRegresarEquipo(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuEquipos.this, ClassMenuPrincipal.class);
        startActivity(SiguienteActividad);
    }
}
