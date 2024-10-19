package gace.modelo;

public class SocioInfantil extends Socio {
    private String noTutor;
    private final double cuotaBase = 100.0; // Ejemplo de cuota base

    public SocioInfantil() {}

    public SocioInfantil(String noSocio, String nombre, String apellido, String noTutor) {
        super(noSocio, nombre, apellido);
        this.noTutor = noTutor;
    }

    //getters
    public String getNoTutor() {
        return noTutor;
    }

    //setters
    public void setNoTutor(String noTutor) {
        this.noTutor = noTutor;
    }

    @Override
    public String toString() {
        return "Socio nº:" + this.getNoSocio() +", Nombre: " + this.getNombre() +
                ", Apellido: " + this.getApellido() +
                ", Tipo: Infantil" +
                ", Nº Tutor: " + noTutor +
                '.';
    }

    @Override
    public double calcularCuota() {
        return cuotaBase * 0.50; // 50% de descuento
    }

    @Override
    public double costeExcursion(Excursion excursion) {
        return excursion.getPrecio(); // Precio completo
    }
}
