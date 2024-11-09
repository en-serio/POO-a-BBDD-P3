package gace.modelo.dao;

import gace.modelo.*;
import gace.modelo.utils.BBDDUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SocioFederadoDao implements DAO<SocioFederado>{
    private Connection conexion;

    public SocioFederadoDao() {
        conexion = BBDDUtil.getConexion();
    }
    public void insertar(SocioFederado socio) {
        String sql = "INSERT INTO federado (id_socio, nif, id_federacion) VALUES (?, ?, ?)";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, socio.getIdSocio());
            pst.setString(2, socio.getNif());
            pst.setInt(3, socio.getFederacion().getIdFederacion());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }

    }

    public void modificar(SocioFederado socio) {
        String sql = "UPDATE federado SET nif = ?, id_federacion = ? WHERE id_socio = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(3, socio.getIdSocio());
            pst.setString(1, socio.getNif());
            pst.setInt(2, socio.getFederacion().getIdFederacion());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    public void eliminar(int idSocio) {
        String sql = "DELETE FROM federado WHERE id_socio = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idSocio);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    public SocioFederado buscar(int idSocio) {
        String sql = "SELECT f.*, fed.*, s.* " +
                "FROM federado f " +
                "JOIN federacion fed ON f.id_federacion = fed.id_federacion " +
                "JOIN socio s ON f.id_socio = s.id_socio " +
                "WHERE f.id_socio = ?";
        SocioFederado socio = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idSocio);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                Federacion fed = new Federacion();
                socio = new SocioFederado();
                socio.setNombre(salida.getString("nombre"));
                socio.setApellido(salida.getString("apellido"));
                socio.setIdSocio(salida.getInt("id_socio"));
                socio.setNif(salida.getString("nif"));
                fed.setIdFederacion(salida.getInt("id_federacion"));
                fed.setCodigo(salida.getString("codigo"));
                fed.setNombre(salida.getString("nombre"));
                socio.setFederacion(fed);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socio;
    }

    public int comprobarFed(String nif) {
        String sql = "SELECT * FROM federado WHERE nif = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, nif);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                return salida.getInt("id_socio");
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
        return -1;
    }

    public SocioFederado buscar(String nif) {
        String sql = "SELECT f.*, fed.*, s.* " +
                "FROM federado f " +
                "JOIN federacion fed ON f.id_federacion = fed.id_federacion " +
                "JOIN socio s ON f.id_socio = s.id_socio " +
                "WHERE f.nif = ?";
        SocioFederado socio = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, nif);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                Federacion fed = new Federacion();
                socio = new SocioFederado();
                socio.setNombre(salida.getString("nombre"));
                socio.setApellido(salida.getString("apellido"));
                socio.setIdSocio(salida.getInt("id_socio"));
                socio.setNif(salida.getString("nif"));
                fed.setIdFederacion(salida.getInt("id_seguro"));
                fed.setCodigo(salida.getString("codigo"));
                fed.setNombre(salida.getString("nombre"));
                socio.setFederacion(fed);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socio;
    }

    public ArrayList<Socio> buscarLista(ArrayList<Integer> ids){
        ArrayList<Socio> socios = new ArrayList<>();
        String sql = "SELECT f.id_socio, s.nombre, s.apellido, f.nif, f.id_federacion, fed.* " +
                "FROM federado f " +
                "JOIN socio s ON f.id_socio = s.id_socio " +
                "JOIN federacion fed ON f.id_federacion = fed.id_federacion " +
                "WHERE f.id_socio IN (";
        for(int i = 0; i < ids.size(); i++) {
            sql += "?";
            if(i < ids.size()-1) {
                sql += ",";
            }
        }
        sql += ")";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            for(int i = 0; i < ids.size(); i++) {
                pst.setInt(i+1, ids.get(i));
            }
            ResultSet salida = pst.executeQuery();
            if(!salida.next()) {
                return null;
            }
            do{
                SocioFederado socio = new SocioFederado();
                Federacion fed = new Federacion();
                socio.setIdSocio(salida.getInt("id_socio"));
                socio.setNombre(salida.getString("nombre"));
                socio.setApellido(salida.getString("apellido"));
                socio.setNif(salida.getString("nif"));
                fed.setIdFederacion(salida.getInt("id_federacion"));
                fed.setNombre(salida.getString("nombre"));
                fed.setCodigo(salida.getString("codigo"));
                socio.setFederacion(fed);
                socios.add(socio);
            } while(salida.next());
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socios;
    }

    public ArrayList<Socio> listar() {
        ArrayList<Socio> socios = new ArrayList<>();
        String sql = "SELECT f.id_socio, s.nombre, s.apellido, f.nif, f.id_federacion, fed.* " +
                "FROM federado f " +
                "JOIN socio s ON f.id_socio = s.id_socio " +
                "JOIN federacion fed ON f.id_federacion = fed.id_federacion";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            ResultSet salida = pst.executeQuery();
            if (!salida.next()) {
                return null;
            }
            do{
                SocioFederado socio = new SocioFederado();
                Federacion fed = new Federacion();
                socio.setIdSocio(salida.getInt("id_socio"));
                socio.setNombre(salida.getString("nombre"));
                socio.setApellido(salida.getString("apellido"));
                socio.setNif(salida.getString("nif"));
                fed.setIdFederacion(salida.getInt("id_federacion"));
                fed.setNombre(salida.getString("nombre"));
                fed.setCodigo(salida.getString("codigo"));
                socio.setFederacion(fed);
                socios.add(socio);
            }while(salida.next());
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socios;
    }
}
