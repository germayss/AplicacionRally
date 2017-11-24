package cr.developersgss.rally.ModuloAdministrador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cr.developersgss.rally.ModuloEquipos.ClassMenuEquipos;
import cr.developersgss.rally.ModuloJueces.ClassMenuJueces;
import cr.developersgss.rally.ModuloPruebas.ClassMenuPruebas;
import cr.developersgss.rally.ModuloRuta.ClassMenuRuta;
import cr.developersgss.rally.R;

/**
 * Created by germay on 21/11/2017.
 */

public class ClassMenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_menuprincipal);
    }


    public void onClickJueces(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuPrincipal.this, ClassMenuJueces.class);
        startActivity(SiguienteActividad);
    }

    public void onClickAdministradores(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuPrincipal.this, ClassMenuAdministradores.class);
        startActivity(SiguienteActividad);
    }

    public void onClickEquipos(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuPrincipal.this, ClassMenuEquipos.class);
        startActivity(SiguienteActividad);
    }

    public void onClickRuta(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuPrincipal.this, ClassMenuRuta.class);
        startActivity(SiguienteActividad);
    }
    public void onClickPruebas(View view)
    {
        Intent SiguienteActividad = new Intent(ClassMenuPrincipal.this, ClassMenuPruebas.class);
        startActivity(SiguienteActividad);
    }
    public void onClickRallyUsuario(View view)
    {
        Intent SiguienteActividad = new Intent(ClassMenuPrincipal.this, ClassMenuPruebas.class);
        startActivity(SiguienteActividad);
    }
    public void onClickRallyJueces(View view)
    {
        Intent SiguienteActividad = new Intent(ClassMenuPrincipal.this, ClassMenuPruebas.class);
        startActivity(SiguienteActividad);
    }
}
