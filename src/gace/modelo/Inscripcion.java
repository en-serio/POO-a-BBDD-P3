package gace.modelo;

import java.util.Date;

public class Inscripcion {
    private String idInscripcion;
    private Socio socio;
    private Excursion excursion;
    private Date fechaInscripcion;

    public Inscripcion(String idInscripcion, Socio socio, Excursion excursion, Date fechaInscripcion) {
        this.idInscripcion = idInscripcion;
        this.socio = socio;
        this.excursion = excursion;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Inscripcion() {}

    //getters
    public String getIdInscripcion() {
        return idInscripcion;
    }
    public Socio getSocio() {
        return socio;
    }
    public Excursion getExcursion() {
        return excursion;
    }
    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    //setters
    public void setIdInscripcion(String idInscripcion) {
        this.idInscripcion = idInscripcion;
    }
    public void setSocio(Socio socio) {
        this.socio = socio;
    }
    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }
    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }


    @Override
    public String toString() {
        return "Inscripcion{" +
                "idInscripcion='" + idInscripcion + '\'' +
                ", socio=" + socio +
                ", excursion=" + excursion +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }
}
