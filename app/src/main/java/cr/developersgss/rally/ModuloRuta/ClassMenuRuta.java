package cr.developersgss.rally.ModuloRuta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cr.developersgss.rally.ModuloAdministrador.ClassMenuPrincipal;
import cr.developersgss.rally.ModuloJueces.ClassRegistrarJuez;
import cr.developersgss.rally.R;

/**
 * Created by germa on 21/11/2017.
 */

public class ClassMenuRuta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_menuruta);
    }


    public void onClickRegistrarRuta1(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuRuta.this, ClassRegistroRuta.class);
        startActivity(SiguienteActividad);
    }

    public void onClickConsultarRuta(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuRuta.this, ClassConsultaRuta.class);
        startActivity(SiguienteActividad);
    }
    public void onClickRegresarRuta(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuRuta.this, ClassMenuPrincipal.class);
        startActivity(SiguienteActividad);
    }
}
