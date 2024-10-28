package gace.modelo.dao;

import java.util.ArrayList;

public interface DAO<T> {
    public void insertar(T t);
    public void modificar(T t);
    public void eliminar(int id_t);
    public T buscar(int id_t);
    public ArrayList<T> listar();
}
