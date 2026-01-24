package model.dao;

import java.util.List;
import model.entidades.Pedido;

public interface PedidoDao {

    void inserir(Pedido p);

    void actualizar(Pedido p);

    void remover(int id);

    Pedido pesquisar(int id);

    List<Pedido> listar();
}
