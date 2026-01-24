package model.dao;

import java.util.List;
import model.entidades.ItemPedido;

public interface ItemPedidoDao {

    void inserir(ItemPedido ip);

    void actualizar(ItemPedido ip);

    void remover(int id,int id2);

    ItemPedido pesquisar(int id,int id2);

    List<ItemPedido> listar();
}
