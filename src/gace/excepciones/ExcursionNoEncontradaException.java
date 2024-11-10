package gace.excepciones;

public class ExcursionNoEncontradaException extends GaceException {
    public ExcursionNoEncontradaException(String codigo) {
        super("La excursión con código " + codigo + " no fue encontrada.");
    }
}
