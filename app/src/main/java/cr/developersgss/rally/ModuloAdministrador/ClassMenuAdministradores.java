package cr.developersgss.rally.ModuloAdministrador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cr.developersgss.rally.ModuloPruebas.ClassMenuPruebas;
import cr.developersgss.rally.R;

/**
 * Created by germa on 21/11/2017.
 */

public class ClassMenuAdministradores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_menuadministradores);
    }
    public void onClickRegresarMenu(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuAdministradores.this, ClassMenuPrincipal.class);
        startActivity(SiguienteActividad);
    }
}
