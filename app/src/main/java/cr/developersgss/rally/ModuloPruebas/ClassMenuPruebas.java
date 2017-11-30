package cr.developersgss.rally.ModuloPruebas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cr.developersgss.rally.R;

/**
 * Created by Afi on 22/11/2017.
 */

public class ClassMenuPruebas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_menupruebas);
    }

    public void onClickRegistrar(View view) {
        Intent SiguienteActividad = new Intent(ClassMenuPruebas.this, ClassRegistroPruebas.class);
        startActivity(SiguienteActividad);
    }
}
