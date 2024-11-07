package gace.modelo.dao;

import gace.modelo.*;
import gace.modelo.utils.BBDDUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class SocioDao {
    private Connection conexion;

    public SocioDao() {
        conexion = BBDDUtil.getConexion();
    }
    public int insertar(Socio socio) {
        String sql = "INSERT INTO socio (nombre, apellido, tipo, activo) VALUES (?, ?, ?, ?)";
        int idSocio = 0;
        try(PreparedStatement pst = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, socio.getNombre());
            pst.setString(2, socio.getApellido());
            if(socio instanceof SocioEstandar) {
                pst.setInt(3, 1);
            } else if(socio instanceof SocioFederado) {
                pst.setInt(3, 2);
            }else{
                pst.setInt(3, 3);
            }
            pst.setInt(4, 1);
            pst.executeUpdate();
            ResultSet salida = pst.getGeneratedKeys();
            if(salida.next()) {
                idSocio = salida.getInt(1);
            }
            socio.setIdSocio(idSocio);
            if(socio instanceof SocioEstandar) {
                DAOFactory.getSocioEstandarDao().insertar((SocioEstandar) socio);
            } else if(socio instanceof SocioFederado) {
                DAOFactory.getSocioFederadoDao().insertar((SocioFederado) socio);
            }else{
                DAOFactory.getSocioInfantilDao().insertar((SocioInfantil) socio);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
        return idSocio;
    }

    public void modificar(Socio socio) {
        String sql = "UPDATE socio SET nombre = ?, apellido = ?, tipo = ? WHERE id_seguro = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            if(socio instanceof SocioEstandar) {
                pst.setInt(3, 1);
                DAOFactory.getSocioEstandarDao().modificar((SocioEstandar) socio);
            } else if(socio instanceof SocioFederado) {
                pst.setInt(3, 2);
                DAOFactory.getSocioFederadoDao().modificar((SocioFederado) socio);
            } else {
                pst.setInt(3, 3);
                DAOFactory.getSocioInfantilDao().modificar((SocioInfantil) socio);
            }
            pst.setString(1, socio.getNombre());
            pst.setString(2, socio.getApellido());
            pst.setInt(4, socio.getIdSocio());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }

    public void eliminar(int idSocio) {
        String sqlSelect = "SELECT tipo FROM socio WHERE id_socio = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sqlSelect)) {
            pst.setInt(1, idSocio);
            ResultSet salida = pst.executeQuery();

            if(salida.next()) {
                if(Objects.equals(salida.getString("tipo"), "ESTANDAR")) {
                    DAOFactory.getSocioEstandarDao().eliminar(idSocio);
                } else if(Objects.equals(salida.getString("tipo"), "FEDERADO")) {
                    DAOFactory.getSocioFederadoDao().eliminar(idSocio);
                } else {
                    DAOFactory.getSocioInfantilDao().eliminar(idSocio);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
        String sql = "DELETE FROM socio WHERE id_socio = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idSocio);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getErrorCode() + e.getMessage());
        }
    }


    public Socio buscar(int idSocio) {
        String sql = "SELECT * FROM socio WHERE id_socio = ?";
        Socio socio = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idSocio);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                if(Objects.equals(salida.getString("tipo"), "ESTANDAR")) {
                    return DAOFactory.getSocioEstandarDao().buscar(idSocio);
                } else if(Objects.equals(salida.getString("tipo"), "FEDERADO")) {
                    return DAOFactory.getSocioFederadoDao().buscar(idSocio);
                } else {
                    return DAOFactory.getSocioInfantilDao().buscar(idSocio);
                }
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socio;
    }

    public Socio buscar(String nif) {
        int id = DAOFactory.getSocioEstandarDao().comprobarEst(nif);
        if(id != -1) {
            return DAOFactory.getSocioEstandarDao().buscar(id);
        }
        id = DAOFactory.getSocioFederadoDao().comprobarFed(nif);
        if(id != -1) {
            return DAOFactory.getSocioFederadoDao().buscar(id);
        }
        return null;
    }

    public boolean hayNif(String nif) {
        Socio socio = null;
        socio = DAOFactory.getSocioEstandarDao().buscar(nif);
        if(socio != null) {
            return true;
        }
        socio = DAOFactory.getSocioFederadoDao().buscar(nif);
        if(socio != null) {
            return true;
        }
        return false;
    }




    public ArrayList<Socio> listar() {
        ArrayList<Socio> socios = new ArrayList<>();
        ArrayList<Socio> aux = DAOFactory.getSocioEstandarDao().listar();
        if(aux != null) {
            socios.addAll(aux);
        }
        aux = null;
        aux = DAOFactory.getSocioFederadoDao().listar();
        if(aux != null) {
            socios.addAll(aux);
        }
        aux = null;
        aux = DAOFactory.getSocioInfantilDao().listar();
        if(aux != null) {
            socios.addAll(aux);
        }
        if(socios.isEmpty()) {
            return null;
        }
        socios.sort(Comparator.comparingInt(Socio::getIdSocio));
        return socios;
    }


}
