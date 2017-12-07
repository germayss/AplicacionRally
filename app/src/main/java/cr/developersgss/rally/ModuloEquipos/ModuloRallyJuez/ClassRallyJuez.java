package cr.developersgss.rally.ModuloEquipos.ModuloRallyJuez;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cr.developersgss.rally.R;

/**
 * Created by Afi on 22/11/2017.
 */

public class ClassRallyJuez extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_rallyjuez);
    }

    public void onClickAsignaPrueba(View view) {
        Intent SiguienteActividad = new Intent(ClassRallyJuez.this, ClassJuezAsignaPrueba.class);
        startActivity(SiguienteActividad);
    }
}
