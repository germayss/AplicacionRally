package cr.developersgss.rally.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cr.developersgss.rally.ModuloAdministrador.ClassMenuPrincipal;
import cr.developersgss.rally.R;

public class ClassLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_login);
    }

    public void onClickLogin(View view) {
        Intent SiguienteActividad = new Intent(ClassLogin.this, ClassMenuPrincipal.class);
        startActivity(SiguienteActividad);
    }

    public void onClickContraseña(View view) {
        Intent SiguienteActividad = new Intent(ClassLogin.this, ClassCambioContrasena.class);
        startActivity(SiguienteActividad);
    }
}
