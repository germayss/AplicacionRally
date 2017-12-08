package cr.developersgss.rally.Objetos;

/**
 * Created by Krlos on 02/12/2017.
 */

public class TablaJuez {

      private  String IDJuez;
      private String IDRally;
      private String Puntocontrol;
      private String Nombre;
      private String Usuario;
      private String Contrasena;
      private  String Tipo;

    public TablaJuez() {
    }

    public TablaJuez(String IDJuez, String IDRally, String puntocontrol, String nombre, String usuario, String contrasena, String tipo) {
        this.IDJuez = IDJuez;
        this.IDRally = IDRally;
        Puntocontrol = puntocontrol;
        Nombre = nombre;
        Usuario = usuario;
        Contrasena = contrasena;
        Tipo = tipo;
    }

    public void setIDJuez(String IDJuez) {
        this.IDJuez = IDJuez;
    }

    public void setIDRally(String IDRally) {
        this.IDRally = IDRally;
    }

    public void setPuntocontrol(String puntocontrol) {
        Puntocontrol = puntocontrol;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getIDJuez() {
        return IDJuez;
    }

    public String getIDRally() {
        return IDRally;
    }

    public String getPuntocontrol() {
        return Puntocontrol;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public String getTipo() {
        return Tipo;
    }
}
