package gace.modelo.dao;

import gace.modelo.*;
import gace.modelo.utils.BBDDUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SocioEstandarDao implements DAO<SocioEstandar> {
    private Connection conexion;

    public SocioEstandarDao() {
        conexion = BBDDUtil.getConexion();
    }
    public void insertar(SocioEstandar socio) {
        String sql = "INSERT INTO estandar (id_socio, nif, id_seguro) VALUES (?, ?, ?)";
        try(PreparedStatement pst = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, socio.getIdSocio());
            pst.setString(2, socio.getNif());
            pst.setInt(3, socio.getSeguro().getIdSeguro());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    public void modificar(SocioEstandar socio) {
        String sql = "UPDATE estandar SET nif = ?, id_seguro = ? WHERE id_socio = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, socio.getNif());
            pst.setInt(2, socio.getSeguro().getIdSeguro());
            pst.setInt(3, socio.getIdSocio());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    public void eliminar(int id_socio) {
        String sql = "DELETE FROM estandar WHERE id_socio = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, id_socio);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
    }

    public SocioEstandar buscar(int idSocio) {
        String sql = "SELECT e.*, seg.*, s.*" +
                "FROM estandar e " +
                "JOIN seguro seg ON e.id_seguro = seg.id_seguro " +
                "JOIN socio s ON e.id_socio = s.id_socio " +
                "WHERE e.id_socio = ?";
        SocioEstandar socio = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idSocio);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                Seguro seg = new Seguro();
                socio = new SocioEstandar();
                socio.setNombre(salida.getString("nombre"));
                socio.setApellido(salida.getString("apellido"));
                socio.setIdSocio(salida.getInt("id_socio"));
                socio.setNif(salida.getString("nif"));
                seg.setIdSeguro(salida.getInt("id_seguro"));
                seg.setTipo(salida.getString("tipo").equals("COMPLETO"));
                seg.setPrecio(salida.getDouble("precio"));
                socio.setSeguro(seg);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() +" - "+e.getMessage());
        }
        return socio;
    }

    public ArrayList<Socio> buscarLista(ArrayList<Integer> ids){
        ArrayList<Socio> socios = new ArrayList<>();
        String sql = "SELECT e.id_socio, s.nombre, s.apellido, e.nif, e.id_seguro, seg.* " +
                "FROM estandar e " +
                "JOIN socio s ON e.id_socio = s.id_socio " +
                "JOIN seguro seg ON e.id_seguro = seg.id_seguro " +
                "WHERE e.id_socio IN (";
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
                SocioEstandar socio = new SocioEstandar();
                Seguro seg = new Seguro();
                socio.setIdSocio(salida.getInt("id_socio"));
                socio.setNombre(salida.getString("nombre"));
                socio.setApellido(salida.getString("apellido"));
                socio.setNif(salida.getString("nif"));
                seg.setIdSeguro(salida.getInt("id_seguro"));
                seg.setTipo(salida.getString("tipo").equals("COMPLETO"));
                seg.setPrecio(salida.getDouble("precio"));
                socio.setSeguro(seg);
                socios.add(socio);
            } while(salida.next());
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socios;
    }

    public int comprobarEst(String nif) {
        String sql = "SELECT * FROM estandar WHERE nif = ?";
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

    public SocioEstandar buscar(String nif) {
        String sql = "SELECT e.*, seg.* ,s.*" +
                "FROM estandar e " +
                "JOIN seguro seg ON e.id_seguro = seg.id_seguro " +
                "JOIN socio s ON e.id_socio = s.id_socio " +
                "WHERE e.nif = ?";
        SocioEstandar socio = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, nif);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                Seguro seg = new Seguro();
                socio = new SocioEstandar();
                socio.setNombre(salida.getString("nombre"));
                socio.setApellido(salida.getString("apellido"));
                socio.setIdSocio(salida.getInt("id_socio"));
                socio.setNif(salida.getString("nif"));
                seg.setIdSeguro(salida.getInt("id_seguro"));
                seg.setTipo(salida.getString("tipo").equals("COMPLETO"));
                seg.setPrecio(salida.getDouble("precio"));
                socio.setSeguro(seg);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socio;
    }

    public ArrayList<Socio> listar() {
        ArrayList<Socio> socios = new ArrayList<>();
        String sql = "SELECT e.id_socio, s.nombre, s.apellido, e.nif, e.id_seguro, seg.* " +
                    "FROM estandar e " +
                    "JOIN socio s ON e.id_socio = s.id_socio " +
                    "JOIN seguro seg ON e.id_seguro = seg.id_seguro";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            ResultSet salida = pst.executeQuery();
            if(!salida.next()) {
                return null;
            }
            do{
                SocioEstandar socio = new SocioEstandar();
                Seguro seg = new Seguro();
                socio.setIdSocio(salida.getInt("id_socio"));
                socio.setNombre(salida.getString("nombre"));
                socio.setApellido(salida.getString("apellido"));
                socio.setNif(salida.getString("nif"));
                seg.setIdSeguro(salida.getInt("id_seguro"));
                seg.setTipo(salida.getString("tipo").equals("COMPLETO"));
                seg.setPrecio(salida.getDouble("precio"));
                socio.setSeguro(seg);
                socios.add(socio);
            } while(salida.next());
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socios;
    }
}
