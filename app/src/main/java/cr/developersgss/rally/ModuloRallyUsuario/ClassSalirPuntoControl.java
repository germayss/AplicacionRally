package cr.developersgss.rally.ModuloRallyUsuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cr.developersgss.rally.ModuloAdministrador.ClassMenuPrincipal;
import cr.developersgss.rally.R;


public class ClassSalirPuntoControl extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_salir_punto_control);
    }

    public void onClickSalirPunto(View view) {
        Intent SiguienteActividad = new Intent(ClassSalirPuntoControl.this, ClassMenuPrincipal.class);
        startActivity(SiguienteActividad);
    }
}
