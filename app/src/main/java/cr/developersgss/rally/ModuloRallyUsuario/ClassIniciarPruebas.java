package cr.developersgss.rally.ModuloRallyUsuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cr.developersgss.rally.R;

public class ClassIniciarPruebas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_iniciar_pruebas);
    }

    public void onClickIniciarPruebas(View view)
    {
        Intent SiguienteActividad = new Intent(ClassIniciarPruebas.this,ClassPrueba.class);
        startActivity(SiguienteActividad);
    }
}
