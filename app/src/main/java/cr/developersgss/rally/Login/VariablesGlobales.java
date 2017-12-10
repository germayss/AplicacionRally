package cr.developersgss.rally.Login;

import android.app.Application;

/**
 * Created by Krlos on 09/12/2017.
 */

public class VariablesGlobales extends Application {

     private String IDRallyActual;

    public String getIDRallyActual() {
        return IDRallyActual;
    }

    public void setIDRallyActual(String IDRallyActual) {
        this.IDRallyActual = IDRallyActual;
    }
}
