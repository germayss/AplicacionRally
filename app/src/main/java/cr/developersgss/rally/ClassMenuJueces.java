package cr.developersgss.rally;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ClassMenuJueces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_menu_jueces);
    }
    public void onClickRegistrar(View view)
    {
        Intent SiguienteActividad = new Intent(ClassMenuJueces.this,ClassRegistrarJuez.class);
        startActivity(SiguienteActividad);
    }
}
