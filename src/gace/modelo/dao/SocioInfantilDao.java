package gace.modelo.dao;

import gace.modelo.Seguro;
import gace.modelo.Socio;
import gace.modelo.SocioEstandar;
import gace.modelo.SocioInfantil;
import gace.modelo.utils.BBDDUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SocioInfantilDao implements DAO<SocioInfantil> {
    private Connection conexion;

    public SocioInfantilDao() {
        conexion = BBDDUtil.getConexion();
    }
    public void insertar(SocioInfantil socio) {
        String sql = "INSERT INTO infantil (id_socio, id_tutor) VALUES (?, ?)";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, socio.getIdSocio());
            pst.setInt(2, socio.getNoTutor());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    public void modificar(SocioInfantil socio) {
        String sql = "UPDATE infantil SET id_tutor = ? WHERE id_socio = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(2, socio.getIdSocio());
            pst.setInt(1, socio.getNoTutor());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
    }

    public void eliminar(int id_socio) {
        String sql = "DELETE FROM infantil WHERE id_socio = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, id_socio);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
    }


    public SocioInfantil buscar(int idSocio) {
        String sql = "SELECT i.*, s.* " +
                "FROM infantil i " +
                "JOIN socio s ON i.id_socio = s.id_socio " +
                "WHERE i.id_socio = ?";
        SocioInfantil socio = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idSocio);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                socio = new SocioInfantil();
                socio.setNombre(salida.getString("nombre"));
                socio.setApellido(salida.getString("apellido"));
                socio.setIdSocio(salida.getInt("id_socio"));
                socio.setNoTutor(salida.getInt("id_tutor"));
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socio;
    }

    public ArrayList<Socio> buscarLista(ArrayList<Integer> ids){
        ArrayList<Socio> socios = new ArrayList<>();
        String sql = "SELECT i.id_socio, s.nombre, s.apellido, i.id_tutor " +
                "FROM infantil i " +
                "JOIN socio s ON i.id_socio = s.id_socio " +
                "WHERE i.id_socio IN (";
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
                SocioInfantil socio = new SocioInfantil();
                socio.setIdSocio(salida.getInt("id_socio"));
                socio.setNombre(salida.getString("nombre"));
                socio.setApellido(salida.getString("apellido"));
                socio.setNoTutor(salida.getInt("id_tutor"));
                socios.add(socio);
            } while(salida.next());
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socios;
    }

    public ArrayList<Socio> listar() {
        ArrayList<Socio> socios = new ArrayList<>();
        String sql = "SELECT i.*, s.* " +
                "FROM infantil i " +
                "JOIN socio s ON i.id_socio = s.id_socio";

        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            ResultSet salida = pst.executeQuery();
            if(!salida.next()){
                return null;
            }
            do{
                SocioInfantil socio = new SocioInfantil();
                socio.setIdSocio(salida.getInt("id_socio"));
                socio.setNombre(salida.getString("nombre"));
                socio.setApellido(salida.getString("apellido"));
                socio.setNoTutor(salida.getInt("id_tutor"));
                socios.add(socio);
            } while(salida.next());
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socios;
    }
}
