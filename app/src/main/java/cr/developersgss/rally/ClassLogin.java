package cr.developersgss.rally;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ClassLogin extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_class_login);


    }
    //evento boton ingresar
    public void onClick(View view)
    {
        Intent intent  =new Intent(ClassLogin.this,ClassMenuPrincipal.class);
        startActivity(intent);
    }


}
