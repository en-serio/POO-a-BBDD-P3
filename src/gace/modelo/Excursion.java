package gace.modelo;

import java.util.Date;

public class Excursion {
    private int id;
    private String codigo;
    private String descripcion;
    private Date fecha;
    private int noDias;
    private double precio;

    public Excursion(int id, String descripcion, Date fecha, int noDias, double precio) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.noDias = noDias;
        this.precio = precio;
    }

    public Excursion(String codigo, String descripcion, Date fecha, int noDias, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.noDias = noDias;
        this.precio = precio;
    }

    public Excursion() {
    }

    //getters

    public int getId() {
        return id;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public Date getFecha() {
        return fecha;
    }
    public int getNoDias() {
        return noDias;
    }
    public double getPrecio() {
        return precio;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNoDias(int noDias) {
        this.noDias = noDias;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Excursion{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", noDias=" + noDias +
                ", precio=" + precio +
                '}';
    }
}
