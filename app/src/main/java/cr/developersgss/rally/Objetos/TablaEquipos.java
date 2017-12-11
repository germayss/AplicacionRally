package cr.developersgss.rally.Objetos;

/**
 * Created by Krlos on 10/12/2017.
 */

public class TablaEquipos {
    private String IDRally;
    private String IDEquipo;
    private String NombreEquipo;
    private String Usuarioquipo;
    private String PasswordEquipo;

    public TablaEquipos(String IDRally, String IDEquipo, String nombreEquipo, String usuarioquipo, String passwordEquipo) {
        this.IDRally = IDRally;
        this.IDEquipo = IDEquipo;
        NombreEquipo = nombreEquipo;
        Usuarioquipo = usuarioquipo;
        PasswordEquipo = passwordEquipo;
    }



    public String getIDRally() {
        return IDRally;
    }

    public void setIDRally(String IDRally) {
        this.IDRally = IDRally;
    }

    public String getIDEquipo() {
        return IDEquipo;
    }

    public void setIDEquipo(String IDEquipo) {
        this.IDEquipo = IDEquipo;
    }

    public String getNombreEquipo() {
        return NombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        NombreEquipo = nombreEquipo;
    }

    public String getUsuarioquipo() {
        return Usuarioquipo;
    }

    public void setUsuarioquipo(String usuarioquipo) {
        Usuarioquipo = usuarioquipo;
    }

    public String getPasswordEquipo() {
        return PasswordEquipo;
    }

    public void setPasswordEquipo(String passwordEquipo) {
        PasswordEquipo = passwordEquipo;
    }
}
