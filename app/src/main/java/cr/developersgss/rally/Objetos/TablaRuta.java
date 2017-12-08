package cr.developersgss.rally.Objetos;

/**
 * Created by Krlos on 07/12/2017.
 */

public class TablaRuta {

    private String IDRally;
    private String IDRuta;
    private String NombreRuta;
    private String FechaInicioRuta;
    private String HoraInicioRuta;
    private String EstadoRuta;

    public TablaRuta() {
    }

    public TablaRuta(String IDRally, String IDRuta, String nombreRuta, String fechaInicioRuta, String horaInicioRuta, String estadoRuta) {
        this.IDRally = IDRally;
        this.IDRuta = IDRuta;
        NombreRuta = nombreRuta;
        FechaInicioRuta = fechaInicioRuta;
        HoraInicioRuta = horaInicioRuta;
        EstadoRuta = estadoRuta;
    }

    public String getIDRally() {
        return IDRally;
    }

    public void setIDRally(String IDRally) {
        this.IDRally = IDRally;
    }

    public String getIDRuta() {
        return IDRuta;
    }

    public void setIDRuta(String IDRuta) {
        this.IDRuta = IDRuta;
    }

    public String getNombreRuta() {
        return NombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        NombreRuta = nombreRuta;
    }

    public String getFechaInicioRuta() {
        return FechaInicioRuta;
    }

    public void setFechaInicioRuta(String fechaInicioRuta) {
        FechaInicioRuta = fechaInicioRuta;
    }

    public String getHoraInicioRuta() {
        return HoraInicioRuta;
    }

    public void setHoraInicioRuta(String horaInicioRuta) {
        HoraInicioRuta = horaInicioRuta;
    }

    public String getEstadoRuta() {
        return EstadoRuta;
    }

    public void setEstadoRuta(String estadoRuta) {
        EstadoRuta = estadoRuta;
    }
}
