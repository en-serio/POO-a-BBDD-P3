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
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, socio.getIdSocio());
            pst.setString(2, socio.getNif());
            pst.setInt(3, socio.getSeguro().getIdSeguro());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    public void modificar(SocioEstandar socio) {
        String sql = "UPDATE federado SET nif = ?, id_seguro = ? WHERE id_socio = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(3, socio.getIdSocio());
            pst.setString(1, socio.getNif());
            pst.setInt(2, socio.getSeguro().getIdSeguro());
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
        String sql = "SELECT e.*, seg.* " +
                "FROM estandar e " +
                "JOIN seguro seg ON e.id_seguro = seg.id_seguro " +
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
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socio;
    }

    public ArrayList<SocioEstandar> listar() {
        ArrayList<SocioEstandar> socios = new ArrayList<>();
        String sql = "SELECT e.id_socio, s.nombre, s.apellido, e.nif, e.id_seguro, seg.* \" +\n" +
                    "\"FROM estandar e \" +\n" +
                    "\"JOIN socio s ON e.id_socio = s.id_socio \" +\n" +
                    "\"JOIN seguro seg ON e.id_seguro = seg.id_seguro\"";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            ResultSet salida = pst.executeQuery();
            while(salida.next()) {
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
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socios;
    }
}
