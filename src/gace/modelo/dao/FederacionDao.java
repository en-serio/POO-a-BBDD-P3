package gace.modelo.dao;

import gace.modelo.Federacion;
import gace.modelo.Socio;
import gace.modelo.utils.BBDDUtil;

import java.sql.*;
import java.util.ArrayList;

public class FederacionDao implements DAO<Federacion> {

    private Connection conexion;

    public FederacionDao() {
        conexion = BBDDUtil.getConexion();
    }

    public void insertar(Federacion federacion) {
        String sql = "INSERT INTO federacion (codigo, nombre) VALUES (?, ?)";
        try(PreparedStatement pst = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, federacion.getCodigo());
            pst.setString(2, federacion.getNombre());
            pst.executeUpdate();
            ResultSet salida = pst.getGeneratedKeys();
            if(salida.next()) {
                federacion.setIdFederacion(salida.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    public void modificar(Federacion federacion) {
        String sql = "UPDATE federacion SET nombre = ? WHERE id_federacion = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, federacion.getIdFederacion());
            pst.setString(2, federacion.getNombre());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    public void eliminar(int id_t) {
        String sql = "DELETE FROM federacion WHERE id_federacion = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, id_t);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    public void eliminar(String codigo) {
        String sql = "DELETE FROM federacion WHERE codigo = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, codigo);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    public Federacion buscar(int idFederacion) {
        Federacion fed = null;
        String sql = "SELECT * FROM federacion WHERE id_federacion = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1,idFederacion );
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                fed = new Federacion();
                fed.setIdFederacion(salida.getInt("id_federacion"));
                fed.setCodigo(salida.getString("codigo"));
                fed.setNombre(salida.getString("nombre"));
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return fed;
    }

    public Federacion buscar(String codigo) {
        Federacion fed = null;
        String sql = "SELECT * FROM federacion WHERE codigo = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, codigo );
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                fed = new Federacion();
                fed.setIdFederacion(salida.getInt("id_federacion"));
                fed.setCodigo(salida.getString("codigo"));
                fed.setNombre(salida.getString("nombre"));
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return fed;
    }

    public ArrayList<Federacion> listar() {
        ArrayList<Federacion> federacions = new ArrayList<>();
        String sql = "SELECT * FROM federacion";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            ResultSet salida = pst.executeQuery();
            while(salida.next()) {
                Federacion federacion = new Federacion();
                federacion.setIdFederacion(salida.getInt("id_federacion"));
                federacion.setCodigo(salida.getString("codigo"));
                federacion.setNombre(salida.getString("nombre"));
                federacions.add(federacion);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return federacions;
    }
}
