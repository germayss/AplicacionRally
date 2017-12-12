package cr.developersgss.rally.Objetos;

/**
 * Created by Krlos on 11/12/2017.
 */

public class TablaPersonaEquipo {

    private String IDPersonaEquipo;
    private String IDRally;
    private String IDEquipo;
    private String NombrePersonaEquipo;
    private String Lider;

    public TablaPersonaEquipo(String IDPersonaEquipo, String IDRally, String IDEquipo, String nombrePersonaEquipo, String lider) {
        this.IDPersonaEquipo = IDPersonaEquipo;
        this.IDRally = IDRally;
        this.IDEquipo = IDEquipo;
        NombrePersonaEquipo = nombrePersonaEquipo;
        Lider = lider;
    }

    public String getIDPersonaEquipo() {
        return IDPersonaEquipo;
    }

    public void setIDPersonaEquipo(String IDPersonaEquipo) {
        this.IDPersonaEquipo = IDPersonaEquipo;
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

    public String getNombrePersonaEquipo() {
        return NombrePersonaEquipo;
    }

    public void setNombrePersonaEquipo(String nombrePersonaEquipo) {
        NombrePersonaEquipo = nombrePersonaEquipo;
    }

    public String getLider() {
        return Lider;
    }

    public void setLider(String lider) {
        Lider = lider;
    }
}
