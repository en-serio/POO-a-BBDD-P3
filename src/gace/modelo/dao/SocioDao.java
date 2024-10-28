package gace.modelo.dao;

import gace.modelo.*;
import gace.modelo.utils.BBDDUtil;

import java.sql.*;
import java.util.ArrayList;

public class SocioDao implements DAO<Socio> {
    private Connection conexion;

    public SocioDao() {
        conexion = BBDDUtil.getConexion();
    }
    public void insertar(Socio socio) {
        String sql = "INSERT INTO socio (nombre, apellido, tipo, activo) VALUES (?, ?, ?, ?)";
        try(PreparedStatement pst = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, socio.getNombre());
            pst.setString(2, socio.getApellido());
            if(socio instanceof SocioEstandar) {
                pst.setInt(4, 1);
            } else if(socio instanceof SocioFederado) {
                pst.setInt(4, 2);
            }else{
                pst.setInt(4, 3);
            }
            pst.setInt(5, 1);
            pst.executeUpdate();
            ResultSet salida = pst.getGeneratedKeys();
            int idSocio = 0;
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
                if(salida.getInt("tipo") == 1) {
                    DAOFactory.getSocioEstandarDao().eliminar(idSocio);
                } else if(salida.getInt("tipo") == 2) {
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
                if(salida.getInt("tipo") == 1) {
                    return DAOFactory.getSocioEstandarDao().buscar(idSocio);
                } else if(salida.getInt("tipo") == 2) {
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

    public boolean hayNif(String nif) {
        String sql = "SELECT * FROM socio WHERE nif = ?";
        Socio socio = null;
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, nif);
            ResultSet salida = pst.executeQuery();
            if(salida.next()) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return false;
    }



    // todo seguro que esto se puede optimizar
    public ArrayList<Socio> listar() {
        ArrayList<Socio> socios = new ArrayList<>();
        String sql = "SELECT * FROM socio";
        try(PreparedStatement pst = conexion.prepareStatement(sql)) {
            ResultSet salida = pst.executeQuery();
            while(salida.next()) {
                if(salida.getInt("tipo") == 1) {
                    socios.add(DAOFactory.getSocioEstandarDao().buscar(salida.getInt("id_socio")));
                } else if(salida.getInt("tipo") == 2) {
                    socios.add(DAOFactory.getSocioFederadoDao().buscar(salida.getInt("id_socio")));
                } else {
                    socios.add(DAOFactory.getSocioInfantilDao().buscar(salida.getInt("id_socio")));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+e.getMessage());
        }
        return socios;
    }


}
