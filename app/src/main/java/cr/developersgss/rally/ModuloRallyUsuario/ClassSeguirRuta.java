package cr.developersgss.rally.ModuloRallyUsuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cr.developersgss.rally.R;

public class ClassSeguirRuta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_seguir_ruta);
    }
    public void onClickLlgada(View view)
    {
        Intent SiguienteActividad = new Intent(ClassSeguirRuta.this,ClassIniciarPruebas.class);
        startActivity(SiguienteActividad);
    }
    public void onClickPuntosExtra(View view)
    {
        Intent SiguienteActividad = new Intent(ClassSeguirRuta.this,ClassPuntosExtra.class);
        startActivity(SiguienteActividad);
    }
}
