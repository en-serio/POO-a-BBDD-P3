package gace.modelo.dao;

import gace.modelo.*;
import gace.modelo.utils.BBDDUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InscripcionDao implements DAO<Inscripcion>{
    private Connection conexion;

    public InscripcionDao() {
        conexion = BBDDUtil.getConexion();
    }
    @Override
    public void insertar(Inscripcion inscripcion) {
        String sql = "INSERT INTO incripcion (codigo, id_socio, id_excursion, fecha) VALUES (?, ?, ?, ?)";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            java.sql.Date fechaSQL = new java.sql.Date(inscripcion.getFechaInscripcion().getTime());
            pst.setString(1, inscripcion.getCodigo());
            pst.setInt(2, inscripcion.getSocio().getIdSocio());
            pst.setInt(3, inscripcion.getExcursion().getId());
            pst.setDate(4, fechaSQL);

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    @Override
    public void modificar(Inscripcion inscripcion) {
        String sql = "UPDATE inscripcion SET id_socio = ?, id_excurion = ?,fecha = ? WHERE id_inscripcion = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            java.sql.Date fechaSQL = new java.sql.Date(inscripcion.getFechaInscripcion().getTime());
            pst.setInt(1, inscripcion.getSocio().getIdSocio());
            pst.setDate(3, fechaSQL);
            pst.setInt(2, inscripcion.getExcursion().getId());
            pst.setInt(4, inscripcion.getIdInscripcion());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
    }

    @Override
    public void eliminar(int idInscripcion) {
        String sql = "DELETE FROM inscripcion WHERE id_inscripcion = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idInscripcion);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
    }

    public void eliminar(String codigo) {
        String sql = "DELETE FROM inscripcion WHERE codigo = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1,codigo);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
    }

    @Override
    public Inscripcion buscar(int idInscripcion) {
        String sql = "SELECT * FROM inscripcion WHERE id_inscripcion = ?";
        Inscripcion insc = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idInscripcion);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                insc = new Inscripcion();
                insc.setIdInscripcion(salida.getInt("id_inscripcion"));
                insc.setCodigo(salida.getString("codigo"));
                Excursion excursion = DAOFactory.getExcursionDao().buscar(salida.getInt("id_excursion"));
                Socio socio = DAOFactory.getSocioDao().buscar(salida.getInt("id_socio"));
                insc.setExcursion(excursion);
                insc.setSocio(socio);
                insc.setFechaInscripcion(salida.getDate("fecha"));
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return insc;
    }

    public ArrayList<Inscripcion> listarXExc(Excursion excursion){
        String sql = "SELECT * FROM inscripcion WHERE id_excursion = ?";
        ArrayList<Inscripcion> inscripciones = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, excursion.getId());
            ResultSet salida = pst.executeQuery();
            while(salida.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(salida.getInt("id_inscripcion"));
                insc.setCodigo(salida.getString("codigo"));
                Socio socio = DAOFactory.getSocioDao().buscar(salida.getInt("id_socio"));
                insc.setSocio(socio);
                insc.setExcursion(excursion);
                insc.setFechaInscripcion(salida.getDate("fecha"));
                inscripciones.add(insc);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return inscripciones;
    }

    public Inscripcion buscar(String codigo) {
        String sql = "SELECT i.*, s.*, e.*," +
                "CASE" +
                "   WHEN s.tipo = 1 THEN se.*" +
                "   WHEN s.tipo = 2 THEN sf.*" +
                "   WHEN s.tipo = 3 THEN si.*" +
                "END as socio" +
                "FROM inscripcion i" +
                "JOIN socio s ON i.id_socio = s.id_socio " +
                "LEFT JOIN estandar se ON i.id_socio = se.id_socio " +
                "LEFT JOIN federado sf ON i.id_socio = sf.id_socio " +
                "LEFT JOIN infantil si ON i.id_socio = si.id_socio " +
                "JOIN excursion e ON i.id_excursion = e.id_excursion";;
        Inscripcion insc = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, codigo);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                insc = new Inscripcion();
                insc.setIdInscripcion(salida.getInt("id_inscripcion"));
                insc.setCodigo(salida.getString("codigo"));
                Excursion excursion = listExcursion(salida);
                if(salida.getInt("tipo") == 1) {
                    SocioEstandar socio = listSocioEst(salida);
                    insc.setSocio(socio);
                } else if(salida.getInt("tipo") == 2) {
                    SocioFederado socio = listSocioFed(salida);
                    insc.setSocio(socio);
                } else {
                    SocioInfantil socio = listSocioInf(salida);
                    insc.setSocio(socio);
                }
                insc.setExcursion(excursion);
                insc.setFechaInscripcion(salida.getDate("fecha"));
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return insc;
    }

    @Override
    public ArrayList<Inscripcion> listar() {
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT i.*, s.*, e.*," +
                "CASE" +
                "   WHEN s.tipo = 1 THEN se.*" +
                "   WHEN s.tipo = 2 THEN sf.*" +
                "   WHEN s.tipo = 3 THEN si.*" +
                "END as socio" +
                "FROM inscripcion i" +
                "JOIN socio s ON i.id_socio = s.id_socio " +
                "LEFT JOIN estandar se ON i.id_socio = se.id_socio " +
                "LEFT JOIN federado sf ON i.id_socio = sf.id_socio " +
                "LEFT JOIN infantil si ON i.id_socio = si.id_socio " +
                "JOIN excursion e ON i.id_excursion = e.id_excursion";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            ResultSet salida = pst.executeQuery();
            while(salida.next()) {
                Inscripcion insc = new Inscripcion();
                if(salida.getInt("tipo") == 1) {
                    SocioEstandar socio = listSocioEst(salida);
                    if(socio == null) continue;
                    insc.setSocio(socio);
                } else if(salida.getInt("tipo") == 2) {
                    SocioFederado socio = listSocioFed(salida);
                    if(socio == null) continue;
                    insc.setSocio(socio);
                } else {
                    SocioInfantil socio = listSocioInf(salida);
                    if (socio == null) continue;
                    insc.setSocio(socio);
                }
                Excursion excursion = listExcursion(salida);

                insc.setIdInscripcion(salida.getInt("id_inscripcion"));
                insc.setCodigo(salida.getString("codigo"));
                insc.setFechaInscripcion(salida.getDate("fecha"));
                inscripciones.add(insc);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return inscripciones;
    }

    public ArrayList<Inscripcion> ListarXSocioEst(Socio socio){
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT i.*, e.*, s.id_socio" +
                "FROM inscripcion i" +
                "JOIN socio s ON i.id_socio = s.id_socio" +
                "JOIN excursion e ON i.id_excursion = e.id_excursion" +
                "WHERE i.id_socio = ?" +
                "AND MONTH(i.fecha) = MONTH(CURRENT_DATE())" +
                "AND YEAR(i.fecha) = YEAR(CURRENT_DATE())";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, socio.getIdSocio());
            ResultSet salida = pst.executeQuery();
            while(salida.next()){
                Inscripcion insc = new Inscripcion();
                Excursion exc = listExcursion(salida);
                insc.setSocio(socio);
                insc.setExcursion(exc);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return inscripciones;
    }
    public ArrayList<Inscripcion> ListarXSocioInf(Socio socio){
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT i.*, e.*, s.id_socio" +
                "FROM inscripcion i" +
                "JOIN socio s ON i.id_socio = s.id_socio" +
                "JOIN excursion e ON i.id_excursion = e.id_excursion" +
                "WHERE i.id_socio = ?" +
                "AND MONTH(i.fecha) = MONTH(CURRENT_DATE())" +
                "AND YEAR(i.fecha) = YEAR(CURRENT_DATE())";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, socio.getIdSocio());
            ResultSet salida = pst.executeQuery();
            while(salida.next()){
                Inscripcion insc = new Inscripcion();
                Excursion exc = listExcursion(salida);
                insc.setSocio(socio);
                insc.setExcursion(exc);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return inscripciones;
    }
    public ArrayList<Inscripcion> ListarXSocioFed(Socio socio){
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT i.*, e.*, s.id_socio" +
                "FROM inscripcion i" +
                "JOIN socio s ON i.id_socio = s.id_socio" +
                "JOIN excursion e ON i.id_excursion = e.id_excursion" +
                "WHERE i.id_socio = ?" +
                "AND MONTH(i.fecha) = MONTH(CURRENT_DATE())" +
                "AND YEAR(i.fecha) = YEAR(CURRENT_DATE())";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, socio.getIdSocio());
            ResultSet salida = pst.executeQuery();
            while(salida.next()){
                Inscripcion insc = new Inscripcion();
                Excursion exc = listExcursion(salida);
                insc.setSocio(socio);
                insc.setExcursion(exc);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return inscripciones;
    }

    private SocioEstandar listSocioEst(ResultSet salida){
        SocioEstandar socio = new SocioEstandar();
        try{
            socio.setNombre(salida.getString("nombre"));
            socio.setApellido(salida.getString("apellido"));
            socio.setIdSocio(salida.getInt("id_socio"));
            socio.setNif(salida.getString("nif"));
            Seguro seg = new Seguro();
            seg.setIdSeguro(salida.getInt("id_seguro"));
            seg.setTipo(salida.getString("tipo").equals("COMPLETO"));
            seg.setPrecio(salida.getDouble("precio"));
            socio.setSeguro(seg);
            return socio;
        }catch(SQLException e){
            System.err.println(e.getErrorCode()+e.getMessage());
            socio = null;
        }
        return socio;
    }

    private SocioFederado listSocioFed(ResultSet salida){
        SocioFederado socio = new SocioFederado();
        try {
            socio.setNombre(salida.getString("nombre"));
            socio.setApellido(salida.getString("apellido"));
            socio.setIdSocio(salida.getInt("id_socio"));
            socio.setNif(salida.getString("nif"));
            Federacion fed = new Federacion();
            fed.setIdFederacion(salida.getInt("id_seguro"));
            fed.setCodigo(salida.getString("codigo"));
            fed.setNombre(salida.getString("nombre"));
            socio.setFederacion(fed);
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
            socio = null;
        }
        return socio;
    }

    private SocioInfantil listSocioInf(ResultSet salida) {
        SocioInfantil socio = new SocioInfantil();
        try {
            socio.setNombre(salida.getString("nombre"));
            socio.setApellido(salida.getString("apellido"));
            socio.setIdSocio(salida.getInt("id_socio"));
            socio.setNoTutor(salida.getInt("id_tutor"));
        }catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
            socio = null;
        }
        return socio;
    }

    private Excursion listExcursion(ResultSet salida) {
        Excursion excursion = new Excursion();
        try {
            excursion.setId(salida.getInt("id_excursion"));
            excursion.setDescripcion(salida.getString("nombre"));
            excursion.setCodigo(salida.getString("codigo"));
            excursion.setFecha(salida.getDate("fecha"));
            excursion.setNoDias(salida.getInt("dias"));
            excursion.setPrecio(salida.getDouble("precio"));
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
            excursion = null;
        }
        return excursion;
    }
}

