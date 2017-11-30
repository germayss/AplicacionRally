package cr.developersgss.rally.ModuloRallyUsuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cr.developersgss.rally.R;

public class ClassPrueba extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_prueba);
    }

    public void onClickEnviarRespuesta(View view) {
        Intent SiguienteActividad = new Intent(ClassPrueba.this, ClassCodigoControl.class);
        startActivity(SiguienteActividad);
    }
}
