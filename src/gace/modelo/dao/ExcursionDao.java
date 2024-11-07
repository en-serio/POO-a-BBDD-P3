package gace.modelo.dao;

import gace.modelo.Excursion;
import gace.modelo.Socio;
import gace.modelo.utils.BBDDUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExcursionDao implements DAO<Excursion> {
    private Connection conexion;

    public ExcursionDao() {
        conexion = BBDDUtil.getConexion();
    }

    public void insertar(Excursion excursion) {
        String sql = "INSERT INTO excursion (codigo, descripcion, fecha, no_dias, precio) VALUES (?,?,?, ?, ?)";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            java.sql.Date fechaSQL = new java.sql.Date(excursion.getFecha().getTime());
            pst.setString(1, excursion.getCodigo());
            pst.setString(2, excursion.getDescripcion());
            pst.setDate(3, fechaSQL);
            pst.setInt(4, excursion.getNoDias());
            pst.setDouble(5, excursion.getPrecio());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    public void modificar(Excursion excursion) {
        String sql = "UPDATE excursion SET descripcion = ?, fecha = ?, no_dias = ?, precio = ? WHERE id_excursion = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            java.sql.Date fechaSQL = new java.sql.Date(excursion.getFecha().getTime());
            pst.setString(1, excursion.getDescripcion());
            pst.setDate(2, fechaSQL);
            pst.setInt(3, excursion.getNoDias());
            pst.setDouble(4, excursion.getPrecio());
            pst.setInt(5, excursion.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
    }

    public void eliminar(int id_excursion) {
        String sql = "DELETE FROM excursion WHERE id_excursion = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, id_excursion);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
    }

    public void eliminar(String codigo) {
        String sql = "DELETE FROM excursion WHERE codigo = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1,codigo);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
    }

    public Excursion buscar(int id_excursion) {
        String sql = "SELECT * FROM excursion WHERE id_excursion = ?";
        Excursion excursion = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, id_excursion);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                excursion = new Excursion();
                excursion.setId(salida.getInt("id_excursion"));
                excursion.setDescripcion(salida.getString("descripcion"));
                excursion.setCodigo(salida.getString("codigo"));
                excursion.setFecha(salida.getDate("fecha"));
                excursion.setNoDias(salida.getInt("no_dias"));
                excursion.setPrecio(salida.getDouble("precio"));
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return excursion;
    }


    public Excursion buscar(String codigo) {
        String sql = "SELECT * FROM excursion WHERE id_excursion = ?";
        Excursion excursion = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, codigo);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                excursion = new Excursion();
                excursion.setId(salida.getInt("id_excursion"));
                excursion.setDescripcion(salida.getString("descripcion"));
                excursion.setFecha(salida.getDate("fecha"));
                excursion.setNoDias(salida.getInt("no_dias"));
                excursion.setPrecio(salida.getDouble("precio"));
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return excursion;
    }


    public ArrayList<Excursion> listar() {
        ArrayList<Excursion> excursiones = new ArrayList<>();
        String sql = "SELECT * FROM excursion";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            ResultSet salida = pst.executeQuery();
            if(!salida.next()){
                return null;
            }
            do {
                Excursion excursion = new Excursion();
                excursion.setId(salida.getInt("id_excursion"));
                excursion.setDescripcion(salida.getString("descripcion"));
                excursion.setCodigo(salida.getString("codigo"));
                excursion.setFecha(salida.getDate("fecha"));
                excursion.setNoDias(salida.getInt("no_dias"));
                excursion.setPrecio(salida.getDouble("precio"));
                excursiones.add(excursion);
            } while(salida.next());
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return excursiones;
    }

    public int obtenerUltimoId() {
        String sql = "SELECT MAX(id_excursion) as id FROM excursion";
        int id = 0;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                id = salida.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return id;
    }
}
