package model.dao;

import java.util.List;
import model.entidades.Cliente;

public interface ClienteDao {

    void inserir(Cliente c);

    void actualizar(Cliente c);

    void remover(int id);

    Cliente pesquisar(int id);

    List<Cliente> listar();
}
