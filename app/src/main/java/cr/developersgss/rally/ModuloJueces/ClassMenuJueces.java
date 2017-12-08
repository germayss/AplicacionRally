package cr.developersgss.rally.ModuloJueces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cr.developersgss.rally.ModuloAdministrador.ClassMenuPrincipal;
import cr.developersgss.rally.R;

public class ClassMenuJueces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_menu_jueces);
    }

    public void onClickRegistrarJueces(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuJueces.this, ClassRegistrarJuez.class);
        startActivity(SiguienteActividad);
    }

    public void onClickConsultarJueces(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuJueces.this, ClassConsultaJuez.class);
        startActivity(SiguienteActividad);
    }
    public void onClickRegresarJueces(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuJueces.this, ClassMenuPrincipal.class);
        startActivity(SiguienteActividad);
    }


}



