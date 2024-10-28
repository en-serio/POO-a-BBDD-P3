package gace.modelo.dao;

public class DAOFactory {
    // TODO aplicar el patrón Singleton
    public static ExcursionDao getExcursionDao() {
        return new ExcursionDao();
    }
    public InscripcionDao getInscripcionDao() {
        return new InscripcionDao();
    }

    public static SocioDao getSocioDao() {
        return new SocioDao();
    }
    public static SocioEstandarDao getSocioEstandarDao() {
        return new SocioEstandarDao();
    }
    public static SeguroDao getSeguroDao() {
        return new SeguroDao();
    }
    public static SocioFederadoDao getSocioFederadoDao() {
        return new SocioFederadoDao();
    }
    public static FederacionDao getFederacionDao() {
        return new FederacionDao();
    }
    public static SocioInfantilDao getSocioInfantilDao() {
        return new SocioInfantilDao();
    }
}
