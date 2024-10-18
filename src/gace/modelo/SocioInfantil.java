package gace.modelo;

public class SocioInfantil extends Socio {
    private String noTutor;
    private final double cuotaBase = 100.0; // Ejemplo de cuota base

    public SocioInfantil(String noSocio, String nombre, String noTutor) {
        super(noSocio, nombre);
        this.noTutor = noTutor;
    }

    public SocioInfantil() {}
    public String getNoTutor() {
        return noTutor;
    }

    public void setNoTutor(String noTutor) {
        this.noTutor = noTutor;
    }

    @Override
    public String toString() {
        return "SocioInfantil{" +
                "Nombre: " + this.getNombre() +
                "No Socio: " + this.getNoSocio() +
                "noTutor='" + noTutor + '\'' +
                ", cuotaBase=" + cuotaBase +
                '}';
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
