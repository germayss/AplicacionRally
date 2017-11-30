package cr.developersgss.rally.ModuloRallyUsuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cr.developersgss.rally.R;


public class ClassIniciarRally extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_iniciar_rally);
    }

    public void onClickIniciarRally(View view) {
        Intent SiguienteActividad = new Intent(ClassIniciarRally.this, ClassSeguirRuta.class);
        startActivity(SiguienteActividad);
    }
}
