package gace.excepciones;

public class SocioNoEncontradoException extends GaceException {
    public SocioNoEncontradoException(String noSocio) {
        super("El socio con número " + noSocio + " no fue encontrado.");
    }
}
