package gace.modelo;

import java.util.Date;

public class Inscripcion {
    private int idInscripcion;
    private String codigo;
    private Socio socio;
    private Excursion excursion;
    private Date fechaInscripcion;

    public Inscripcion(int idInscripcion, String codigo, Socio socio, Excursion excursion, Date fechaInscripcion) {
        this.idInscripcion = idInscripcion;
        this.codigo = codigo;
        this.socio = socio;
        this.excursion = excursion;
        this.fechaInscripcion = fechaInscripcion;
    }
    public Inscripcion( String codigo, Socio socio, Excursion excursion) {
        this.codigo = codigo;
        this.socio = socio;
        this.excursion = excursion;
        this.fechaInscripcion = new Date();
    }

    public Inscripcion() {}

    //getters
    public int getIdInscripcion() {
        return idInscripcion;
    }
    public String getCodigo() {
        return codigo;
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
    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        return  "Id Inscripción: " + idInscripcion + ", Codigo INSC: " + codigo + " " + socio + " " + excursion + ", Fecha de Inscripción: " + fechaInscripcion +".";
    }
}
