package gace.modelo;

public abstract class Socio {
    private String noSocio;
    private String nombre;

    protected Socio() {}

    public Socio(String noSocio, String nombre) {
        this.noSocio = noSocio;
        this.nombre = nombre;
    }

    public String getNoSocio() {
        return noSocio;
    }

    public void setNoSocio(String noSocio) {
        this.noSocio = noSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public abstract double calcularCuota();
    public abstract double costeExcursion(Excursion excursion);

    @Override
    public String toString() {
        return "Socio{" +
                "noSocio='" + noSocio + '\'' +
                ", nombre='" + nombre + '\''+
                '}';
    }
}
