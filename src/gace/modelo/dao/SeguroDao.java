package gace.modelo.dao;

import gace.modelo.Excursion;
import gace.modelo.Inscripcion;
import gace.modelo.Seguro;
import gace.modelo.Socio;
import gace.modelo.utils.BBDDUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeguroDao implements DAO<Seguro>{
    private Connection conexion;
    public SeguroDao() {
        conexion = BBDDUtil.getConexion();
    }

    @Override
    public void insertar(Seguro seguro) {
        String sql = "INSERT INTO seguro (tipo, precio) VALUES (?, ?, ?)";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            if(seguro.isTipo()) {
                pst.setString(1, "COMPLETO");
            } else {
                pst.setString(1, "BASICO");
            }
            pst.setDouble(2, seguro.getPrecio());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    @Override
    public void modificar(Seguro seguro) {
        String sql = "UPDATE seguro SET tipo = ?, precio = ? WHERE id_seguro = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            if(seguro.isTipo()) {
                pst.setString(1, "COMPLETO");
            } else {
                pst.setString(1, "BASICO");
            }
            pst.setDouble(2, seguro.getPrecio());
            pst.setInt(3, seguro.getIdSeguro());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    @Override
    public void eliminar(int idSeguro) {
        String sql = "DELETE FROM seguro WHERE id_seguro = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idSeguro);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    @Override
    public Seguro buscar(int idSeguro) {
        String sql = "SELECT * FROM seguro WHERE codigo = ?";
        Seguro seg = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idSeguro);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                seg = new Seguro();
                seg.setIdSeguro(salida.getInt("id_seguro"));
                seg.setTipo(salida.getString("tipo").equals("COMPLETO"));
                seg.setPrecio(salida.getDouble("precio"));
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return seg;
    }

    @Override
    public ArrayList<Seguro> listar() {
        ArrayList<Seguro> seguros = new ArrayList<>();
        String sql = "SELECT * FROM seguro";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            ResultSet salida = pst.executeQuery();
            while(salida.next()) {
                Seguro seg = new Seguro();
                seg = new Seguro();
                seg.setIdSeguro(salida.getInt("id_seguro"));
                seg.setTipo(salida.getString("tipo").equals("COMPLETO"));
                seg.setPrecio(salida.getDouble("precio"));
                seguros.add(seg);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return seguros;
    }
}
