package cr.developersgss.rally;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by germay on 21/11/2017.
 */

public class ClassMenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_menuprincipal);
    }
    public void clickJueces(View view)
    {
        Intent intent =new Intent(ClassMenuPrincipal.this,ClassMenuJueces.class);
        startActivity(intent);

    }
}
