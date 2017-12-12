package cr.developersgss.rally.ModuloRallyUsuario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cr.developersgss.rally.R;

public class ClassPuntosExtra extends AppCompatActivity {

    String idequipo = "", idrally = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        idequipo = bundle.getString("IDE");
        idrally = bundle.getString("IDR");
        setContentView(R.layout.interface_class_puntos_extra);
    }
}
