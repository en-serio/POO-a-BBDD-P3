package gace.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    public Date formatearFecha(String fechaString) {
        SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = dateSimple.parse(fechaString);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
        return fecha;
    }
}
