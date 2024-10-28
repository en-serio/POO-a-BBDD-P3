package gace.modelo;

public class Federacion {
    private int idFederacion;
    private String codigo;
    private String nombre;

    public Federacion(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Federacion() {}

    //getters
    public int getIdFederacion() {
        return idFederacion;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }

    //setters
    public void setIdFederacion(int idFederacion) {
        this.idFederacion = idFederacion;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Federacion{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
