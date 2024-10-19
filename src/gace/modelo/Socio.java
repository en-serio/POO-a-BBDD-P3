package gace.modelo;

public abstract class Socio {
    private String noSocio;
    private String nombre;

    //Todo añadir apellido
    private String apellido;

    protected Socio() {}

    public Socio(String noSocio, String nombre, String apellido) {
        this.noSocio = noSocio;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    //getters
    public String getNoSocio() {
        return noSocio;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {return apellido; }

    //setters
    public void setNoSocio(String noSocio) {
        this.noSocio = noSocio;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {this.apellido = apellido; }

    @Override
    public String toString() {
        return "Socio nº:" + noSocio + ' ' + ", nombre: " + nombre + ' ' + apellido + '.' ;
    }

    public abstract double calcularCuota();
    public abstract double costeExcursion(Excursion excursion);


}
