package gace.modelo;

public abstract class Socio {
    private int idSocio;
    //private String noSocio;
    private String nombre;
    private String apellido;

    protected Socio() {}

    public Socio(int idSocio, String nombre, String apellido) {
        this.idSocio = idSocio;
        //this.noSocio = noSocio;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public Socio(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    //getters
    public int getIdSocio() {
        return idSocio;
    }
   /* public String getNoSocio() {
        return noSocio;
    }*/
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {return apellido; }

    //setters
    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }
/*    public void setNoSocio(String noSocio) {
        this.noSocio = noSocio;
    }*/
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {this.apellido = apellido; }

    @Override
    public String toString() {
        return "Socio nº:" + idSocio + ' ' + ", nombre: " + nombre + ' ' + apellido + '.' ;
    }

    public abstract double calcularCuota();
    public abstract double costeExcursion(double precio);


}
